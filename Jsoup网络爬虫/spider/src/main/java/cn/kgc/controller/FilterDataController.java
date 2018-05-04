package cn.kgc.controller;
import cn.kgc.beans.*;
import cn.kgc.common.Constants;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerDataType.CrawlerDataTypeService;
import cn.kgc.service.crawlerTask.CrawlerTaskService;
import cn.kgc.service.keyword.KeywordService;
import cn.kgc.service.recruit.RecruitService;
import cn.kgc.service.systemDic.SystemDicService;
import cn.kgc.util.DealDataUtil;
import cn.kgc.util.ReturnResultUtil;
import cn.kgc.vo.RecruitVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by zezhong.shang on 17-9-12.
 */
@Controller
public class FilterDataController {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private CrawlerTaskService crawlerTaskService;

    @Autowired
    private SystemDicService systemDicService;

    @Autowired
    private CrawlerDataTypeService crawlerDataTypeService;

    Logger logger = Logger.getLogger(FilterDataController.class);

    public static Map<Long,Integer> counts=new HashMap<Long,Integer>();

    static Integer count=0;

    public static Map<Long,Integer> keywordCountsMap=new HashMap<Long,Integer>();


    /***
     * 1、删除无效数据
     * @throws Exception
     */
    @RequestMapping("/deleteInvalidData")
    @ResponseBody
    public ReturnResult deleteInvalidData() {
        logger.info("开始删除");
        ReturnResult result= ReturnResultUtil.returnSuccess();
        try {
            Map<String,Object> param=new HashMap<>();
            param.put("dicKey","invalid_word");
            List<SystemDic> systemDicList = systemDicService.getSystemDicListByMap(param);
            String words=systemDicList.get(0).getDicValue();
            String wordArray[]=words.split(",");
            List<String> wordList=new ArrayList<String>();
            for (String word:wordArray){
                wordList.add(word);
            }
            recruitService.deleteInvalidData(wordList);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }
    /***
     * 2.数据清洗和筛选关键词
     */
    @RequestMapping("/filterData")
    @ResponseBody
    public ReturnResult filterData(final Long taskId) throws Exception {
        ReturnResult result= ReturnResultUtil.returnSuccess();
        final CrawlerTask crawlerTask=crawlerTaskService.getCrawlerTaskById(taskId);
        if(!crawlerTask.getStatus().equals(Constants.TaskStatus.wait)){
            return ReturnResultUtil.returnFail("任务正在执行，请稍后再试.");
        }
        crawlerTask.setStatus(Constants.TaskStatus.keywordRun);
        crawlerTask.setKeywordProgress("0%");
        crawlerTaskService.itriptxModifyCrawlerTask(crawlerTask);

        //1.删除task原来数据
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("taskId",taskId);
        keywordService.deleteKeywordByMap(param);
        //2.计数器归零
        counts.put(taskId,0);
        Integer dataType=crawlerTask.getDataType();
        //3.根据记录找到对应的拓展词
        final String extWords=getWord(dataType,1);
        //4.根据记录找到对应的停止词
        final String stopWords=getWord(dataType,2);
        final RecruitService newRecruitService = recruitService;
        final List<RecruitVo> recruitList = newRecruitService.getRecruitListByMap(param);
        final String uuid = UUID.randomUUID().toString();
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
        for (int i = 0; i < recruitList.size(); i++) {
            final Recruit recruit = recruitList.get(i);
            //处理一个统计一个
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Set<String> keywordSet = new HashSet<String>();
                        List<String> keywords = DealDataUtil.getKeyword(recruit, extWords,stopWords);
                        for (String temp : keywords) {
                            keywordSet.add(temp);
                        }
                        for (String str : keywordSet) {
                            Keyword keyword = new Keyword();
                            keyword.setStatus(Constants.Status.ACTIVE);
                            keyword.setName(str);
                            keyword.setDataType(recruit.getDataType());
                            keyword.setWebsite(recruit.getWebsite());
                            keyword.setUuid(uuid);
                            keyword.setRid(recruit.getId());
                            keyword.setTaskId(taskId);
                            keywordService.itriptxAddKeyword(keyword);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            crawlerTaskService.addKeywordCount(crawlerTask,recruitList.size());
                            crawlerTaskService.releaseKeywordTask(crawlerTask,recruitList.size(),fixedThreadPool);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return result;
    }

    public String getWord(Integer dataType,Integer type){
        CrawlerDataType crawlerDataType=null;
        try {
            crawlerDataType=crawlerDataTypeService.getCrawlerDataTypeById(new Long(dataType));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (type==1)?crawlerDataType.getExtWord():crawlerDataType.getStopWord();
    }
}
