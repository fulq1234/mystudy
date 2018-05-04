package cn.kgc.controller;
import cn.kgc.beans.*;
import cn.kgc.common.Constants;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerDataType.CrawlerDataTypeService;
import cn.kgc.service.crawlerNode.CrawlerNodeService;
import cn.kgc.service.crawlerRules.CrawlerRulesService;
import cn.kgc.service.crawlerTask.CrawlerTaskService;
import cn.kgc.service.recruit.RecruitService;
import cn.kgc.util.*;
import cn.kgc.vo.RecruitVo;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class CrawlerController {

    @Autowired
    private CrawlerRulesService crawlerRulesService;

    @Autowired
    private CrawlerNodeService crawlerNodeService;

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private CrawlerTaskService crawlerTaskService;

    @Autowired
    private CrawlerDataTypeService crawlerDataTypeService;

    static Logger logger = Logger.getLogger(CrawlerController.class);

    static Map<Long,Set<String>> urlSetMap = new HashMap<Long, Set<String>>();

    @RequestMapping("/execute")
    @ResponseBody
    public ReturnResult crawler(final Long taskId){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        CrawlerTask crawlerTask=null;
        //查询任务配置
        try {
            crawlerTask=crawlerTaskService.getCrawlerTaskById(taskId);
            if(!crawlerTask.getStatus().equals(Constants.TaskStatus.wait)){
                return ReturnResultUtil.returnFail("任务正在执行，请稍后再试.");
            }
            //开启任务锁定状态
            crawlerTask.setStatus(Constants.TaskStatus.dataRun);
            crawlerTask.setDataProgress("0%");
            crawlerTaskService.itriptxModifyCrawlerTask(crawlerTask);
            //新建URL池
            Set<String> urlSet=new HashSet<String>();
            urlSetMap.put(taskId,urlSet);
            //增加已爬取URL防止重复爬取
            Map queryUrlParam=new HashMap();
            queryUrlParam.put("taskId",taskId);
            List<RecruitVo> recruitVoList=recruitService.getRecruitListByMap(queryUrlParam);
            for (RecruitVo recruitVo:recruitVoList){
                urlSet.add(recruitVo.getDetailUrl());
            }
            urlSetMap.put(taskId,urlSet);
            //生成事物Id
            final String uuid = UUID.randomUUID().toString();
            final CrawlerRules crawlerRules=crawlerRulesService.getCrawlerRulesById(crawlerTask.getRuleId());
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("ruleId",crawlerRules.getId());
            param.put("level",0);
            //找出一级节点下属的子节点
            List<CrawlerNode> crawlerNodes=crawlerNodeService.getCrawlerNodeListByMap(param);
            final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(crawlerTask.getThreadCount());
            for (final CrawlerNode crawlerNode:crawlerNodes){
                Map nodeParam=new HashMap();
                nodeParam.put("parent",crawlerNode.getId());
                final List<CrawlerNode> children=crawlerNodeService.getCrawlerNodeListByMap(nodeParam);
                if (crawlerTask.getIsNeedPage().equals(1)) {
                    for (int i = crawlerTask.getStartPage(); i <= crawlerTask.getEndPage(); i++) {
                        final int index=i;
                        final CrawlerTask task=crawlerTask;
                        fixedThreadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    final String sourceUrl = task.getUrl().replaceAll(Constants.Crawler.placeholder, index + "");
                                    getDetailDocumentByUrl(sourceUrl,crawlerRules,crawlerNode,task,uuid,children);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }finally {
                                    try {
                                        crawlerTaskService.addDataCount(task,task.getEndPage());
                                        crawlerTaskService.releaseDataTask(task,task.getEndPage(),fixedThreadPool);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void getDetailDocumentByUrl(String sourceUrl,CrawlerRules crawlerRules,CrawlerNode crawlerNode,CrawlerTask crawlerTask,String uuid,List<CrawlerNode> children){
        Document document =null;
        //判断是否需要代理
        try{
            if(!crawlerRules.getIsNeedProxy().equals(1)){
                document=DocumentUtil.againCommonDocument(sourceUrl);
            }else{
                document= DocumentUtil.againDocument(sourceUrl);
            }
            Elements elements=document.select(crawlerNode.getClassReg());
            for (Element element:elements){
                final String href=element.absUrl("href");
                Document content = null;
                if(isExists(crawlerTask.getId(),href)){
                    logger.info(">>>>>>>>>>>>URL："+href+">>>>>>>已经存在，直接跳过");
                    continue;
                }
                if(!crawlerRules.getIsNeedProxy().equals(1)){
                    content=DocumentUtil.againCommonDocument(href);
                }else{
                    content= DocumentUtil.againDocument(href);
                }
                //获取内容页
                switchRecruit(children,content,sourceUrl,href,crawlerTask,uuid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /***
     * 将内容页转化为爬虫数据
     * @param children
     * @param content
     * @param sourceUrl
     * @param href
     * @param crawlerTask
     * @param uuid
     */
    public void switchRecruit(List<CrawlerNode> children,Document content,String sourceUrl,String href,CrawlerTask crawlerTask,String uuid){
        Recruit recruit=new Recruit();
        for (CrawlerNode temp:children){
            getElementsByType(temp,content,recruit);
        }
        recruit.setListUrl(sourceUrl);
        recruit.setDetailUrl(href);
        //状态
        recruit.setStatus(Constants.Status.ACTIVE);

        recruit.setCity(crawlerTask.getKeyword());
        recruit.setDataType(crawlerTask.getDataType());
        recruit.setUuid(uuid);
        recruit.setTaskId(crawlerTask.getId().intValue());
        try {
            recruitService.itriptxAddRecruit(recruit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getElementsByType(CrawlerNode node, Document document,Recruit recruit) {
        String value=null;
        try{
            switch (node.getType()){
                case 1:
                    Elements elements=document.select(node.getClassReg());
                    if(node.getPosition()==0){
                        if(node.getIsHref()==1){
                            value=elements.attr("href");
                        }else{
                            value=elements.text();
                        }
                    }else if(node.getPosition()==1){
                        if(node.getIsHref()==1){
                            value=elements.first().absUrl("href");
                        }else{
                            value=elements.first().text();
                        }
                    }else if(node.getPosition()==2){
                        if(node.getIsHref()==1){
                            value=elements.last().absUrl("href");
                        }else{
                            value=elements.last().text();
                        }
                    }else if(node.getPosition()==3){
                        if(node.getIsHref()==1){
                            value=elements.get(node.getSelfPosition()-1).absUrl("href");
                        }else{
                            value=elements.get(node.getSelfPosition()-1).text();
                        }
                    }
                    break;
                case 2:
                    int startIndex=document.html().indexOf(node.getStartStr())+node.getStartStr().length();
                    int endIndex=document.html().indexOf(node.getEndStr());
                    value=document.html().substring(startIndex,endIndex);
                    break;
                case 3:
                    String html=document.html();
                    value= RegUtil.regTest(html,node.getReg());
                    break;
            }
            setCloumn(node.getSaveCloumn(),value,recruit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setCloumn(String cloumn,String value,Recruit recruit){
        try {
            Method mothod = Recruit.class.getMethod("set"+ StringUtils.captureName(cloumn),String.class);
            value=StringUtils.delHTMLTag(value);
            mothod.invoke(recruit,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /***
     * 字段类型
     * @return
     */
    @RequestMapping("/queryFieldType")
    @ResponseBody
    public ReturnResult queryFieldType(){
        ReturnResult returnResult=ReturnResultUtil.returnSuccess();
        List<Map<String,String>> list=Constants.saveCloums();
        returnResult.setData(list);
        return returnResult;
    }

    @RequestMapping("/queryDataType")
    @ResponseBody
    public ReturnResult queryDataType() {
        ReturnResult returnResult=ReturnResultUtil.returnSuccess();
        List<CrawlerDataType> crawlerDataTypeList=new ArrayList<CrawlerDataType>();
        try {
            Map<String,Object> param=new HashMap<String,Object>();
            crawlerDataTypeList  = crawlerDataTypeService.getCrawlerDataTypeListByMap(param);
            returnResult.setData(crawlerDataTypeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnResult;
    }

    public static boolean isExists(Long taskId,String url) {
        Set<String> urlSet=urlSetMap.get(taskId);
        if(urlSet.contains(url)){
            return true;
        }else {
            urlSet.add(url);
            urlSetMap.put(taskId,urlSet);
            return false;
        }
    }
}
