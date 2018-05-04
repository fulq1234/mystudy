package cn.kgc.controller;
import cn.kgc.beans.CrawlerTask;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerTask.CrawlerTaskService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ReturnResultUtil;
import cn.kgc.vo.CrawlerTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.kgc.util.MapUtil;
/**
 * Created by zezhong.shang on 17-10-16.
 */
@Controller
@RequestMapping("/client/crawlerTask")
public class CrawlerTaskController {

    @Autowired
    private CrawlerTaskService crawlerTaskService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<CrawlerTaskVo> searchCrawlerTask(@RequestParam Map<String,Object> param) {
        Page<CrawlerTaskVo> crawlerTaskPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            crawlerTaskPage  = crawlerTaskService.queryCrawlerTaskPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerTaskPage;
    }

    @RequestMapping("/saveOrUpdateCrawlerTask")
    @ResponseBody
    public ReturnResult saveOrUpdateCrawlerTask(CrawlerTask crawlerTask){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(crawlerTask.getId())){
                crawlerTask.setDataCounts(0);
                crawlerTask.setKeywordCounts(0);
                crawlerTask.setTotalCount(0);
                flag=crawlerTaskService.itriptxAddCrawlerTask(crawlerTask);
            }else{
                flag=crawlerTaskService.itriptxModifyCrawlerTask(crawlerTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getCrawlerTaskById")
    @ResponseBody
    public ReturnResult getCrawlerTaskById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        CrawlerTask crawlerTask=null;
        try {
            crawlerTask=crawlerTaskService.getCrawlerTaskById(id);
            result.setData(crawlerTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveCrawlerTask")
    @ResponseBody
    public ReturnResult batchRemoveCrawlerTask(String ids){
        Integer flag=0;
        try {
            flag=crawlerTaskService.itriptxBatchDeleteCrawlerTask(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeCrawlerTaskById")
    @ResponseBody
    public ReturnResult removeCrawlerTaskById(Long id){
        Integer flag=0;
        try {
            flag=crawlerTaskService.itriptxDeleteCrawlerTaskById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/queryCrawlerTaskList")
    @ResponseBody
    public ReturnResult queryCrawlerTaskList(){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        Map<String,Object> param=new HashMap<String,Object>();
        List<CrawlerTaskVo> crawlerTaskList=null;
        try {
            crawlerTaskList=crawlerTaskService.getCrawlerTaskListByMap(param);
            result.setData(crawlerTaskList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
