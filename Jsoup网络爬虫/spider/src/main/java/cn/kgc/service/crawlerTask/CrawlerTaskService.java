package cn.kgc.service.crawlerTask;
import cn.kgc.beans.CrawlerTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import cn.kgc.common.Page;
import cn.kgc.vo.CrawlerTaskVo;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface CrawlerTaskService {

    public CrawlerTask getCrawlerTaskById(Long id)throws Exception;

    public List<CrawlerTaskVo>	getCrawlerTaskListByMap(Map<String,Object> param)throws Exception;

    public Integer getCrawlerTaskCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddCrawlerTask(CrawlerTask crawlerTask)throws Exception;

    public Integer itriptxModifyCrawlerTask(CrawlerTask crawlerTask)throws Exception;

    public Integer itriptxDeleteCrawlerTaskById(Long id)throws Exception;

    public Page<CrawlerTaskVo> queryCrawlerTaskPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteCrawlerTask(String ids)throws Exception;

    public void addDataCount(CrawlerTask crawlerTask,int total) throws Exception;

    public void addKeywordCount(CrawlerTask crawlerTask,int total) throws Exception;

    public void releaseDataTask(CrawlerTask task,int total,ExecutorService fixedThreadPool) throws Exception;

    public void releaseKeywordTask(CrawlerTask task,int total,ExecutorService fixedThreadPool) throws Exception;
}