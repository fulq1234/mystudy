package cn.kgc.service.crawlerTask.impl;
import cn.kgc.dao.crawlerTask.CrawlerTaskMapper;
import cn.kgc.beans.CrawlerTask;
import cn.kgc.dao.keyword.KeywordMapper;
import cn.kgc.dao.recruit.RecruitMapper;
import cn.kgc.vo.CrawlerTaskVo;
import org.springframework.stereotype.Service;
import cn.kgc.service.crawlerTask.CrawlerTaskService;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

@Service
public class CrawlerTaskServiceImpl implements CrawlerTaskService {

    @Resource
    private CrawlerTaskMapper crawlerTaskMapper;

    @Resource
    private RecruitMapper recruitMapper;

    @Resource
    private KeywordMapper keywordMapper;

    public static Map<Long,Integer> dataCountsMap=new HashMap<Long,Integer>();

    public static Map<Long,Integer> keywordCountsMap=new HashMap<Long,Integer>();

    public CrawlerTask getCrawlerTaskById(Long id)throws Exception{
        return crawlerTaskMapper.getCrawlerTaskById(id);
    }

    public List<CrawlerTaskVo>	getCrawlerTaskListByMap(Map<String,Object> param)throws Exception{
        return crawlerTaskMapper.getCrawlerTaskListByMap(param);
    }

    public Integer getCrawlerTaskCountByMap(Map<String,Object> param)throws Exception{
        return crawlerTaskMapper.getCrawlerTaskCountByMap(param);
    }

    public Integer itriptxAddCrawlerTask(CrawlerTask crawlerTask)throws Exception{
         crawlerTask.setCreatedTime(new Date());
         return crawlerTaskMapper.insertCrawlerTask(crawlerTask);
    }

    public Integer itriptxModifyCrawlerTask(CrawlerTask crawlerTask)throws Exception{
        crawlerTask.setUpdatedTime(new Date());
        return crawlerTaskMapper.updateCrawlerTask(crawlerTask);
    }

    public Integer itriptxDeleteCrawlerTaskById(Long id)throws Exception{
        return crawlerTaskMapper.deleteCrawlerTaskById(id);
    }

    public Page<CrawlerTaskVo> queryCrawlerTaskPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = crawlerTaskMapper.getCrawlerTaskCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<CrawlerTaskVo> crawlerTaskList = crawlerTaskMapper.getCrawlerTaskListByMap(param);
        page.setRows(crawlerTaskList);
        return page;
    }

    public Integer itriptxBatchDeleteCrawlerTask(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return crawlerTaskMapper.batchDeleteCrawlerTask(param);
    }

    @Override
    public synchronized void addDataCount(CrawlerTask crawlerTask, int total)throws Exception{
        Integer count=dataCountsMap.get(crawlerTask.getId());
        count=(count==null?0:count)+1;
        dataCountsMap.put(crawlerTask.getId(),count);
        int percent=(count*100/total);
        crawlerTask.setDataProgress(percent+"%");
        try {
            crawlerTask.setStatus(Constants.TaskStatus.dataRun);
            crawlerTaskMapper.updateCrawlerTask(crawlerTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void addKeywordCount(CrawlerTask crawlerTask, int total) throws Exception{
        Integer count=keywordCountsMap.get(crawlerTask.getId());
        count=(count==null?0:count)+1;
        keywordCountsMap.put(crawlerTask.getId(),count);
        int percent=(count*100/total);
        crawlerTask.setKeywordProgress(percent+"%");
        try {
            crawlerTask.setStatus(Constants.TaskStatus.keywordRun);
            crawlerTaskMapper.updateCrawlerTask(crawlerTask);
            System.out.println(count+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void releaseDataTask(CrawlerTask task,int total,ExecutorService fixedThreadPool) throws Exception {
        Integer count=dataCountsMap.get(task.getId());
        if(count>=total){
            task.setStatus(Constants.TaskStatus.wait);
            task.setKeywordProgress("100%");
            //计算采集数据数目
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("taskId",task.getId());
            Integer totalCount=recruitMapper.getRecruitCountByMap(param);
            task.setTotalCount(totalCount);
            //计算采集数据次数
            Integer dataCounts=task.getDataCounts()==null?1:task.getDataCounts()+1;
            task.setDataCounts(dataCounts);
            task.setDataProgress("0%");
            crawlerTaskMapper.updateCrawlerTask(task);
            dataCountsMap.put(task.getId(),0);
            //退出程序
            return;
        }
    }

    public synchronized void releaseKeywordTask(CrawlerTask task,int total,ExecutorService fixedThreadPool) throws Exception {
        Integer count=keywordCountsMap.get(task.getId());
        if(count>=total){
            task.setStatus(Constants.TaskStatus.wait);
            task.setKeywordProgress("100%");
            //计算采集数据数目
            //计算采集数据次数
            Integer keywordCounts=task.getKeywordCounts()==null?1:task.getKeywordCounts()+1;
            task.setKeywordCounts(keywordCounts);
            task.setKeywordProgress("0%");
            crawlerTaskMapper.updateCrawlerTask(task);
            //退出程序
            keywordCountsMap.put(task.getId(),0);
            return;
        }
    }
}
