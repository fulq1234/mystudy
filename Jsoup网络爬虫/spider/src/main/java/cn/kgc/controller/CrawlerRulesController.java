package cn.kgc.controller;
import cn.kgc.beans.CrawlerRules;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerRules.CrawlerRulesService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ReturnResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.kgc.util.MapUtil;
/**
 * Created by zezhong.shang on 17-10-16.
 */
@Controller
@RequestMapping("/client/crawlerRules")
public class CrawlerRulesController {

    @Autowired
    private CrawlerRulesService crawlerRulesService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<CrawlerRules> searchCrawlerRules(@RequestParam Map<String,Object> param) {
        Page<CrawlerRules> crawlerRulesPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            crawlerRulesPage  = crawlerRulesService.queryCrawlerRulesPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerRulesPage;
    }

    @RequestMapping("/saveOrUpdateCrawlerRules")
    @ResponseBody
    public ReturnResult saveOrUpdateCrawlerRules(CrawlerRules crawlerRules){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(crawlerRules.getId())){
                crawlerRules.setCreatdTime(new Date());
                crawlerRules.setStatus(0);
                flag=crawlerRulesService.itriptxAddCrawlerRules(crawlerRules);
            }else{
                crawlerRules.setUpdatedTime(new Date());
                flag=crawlerRulesService.itriptxModifyCrawlerRules(crawlerRules);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getCrawlerRulesById")
    @ResponseBody
    public ReturnResult getCrawlerRulesById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        CrawlerRules crawlerRules=null;
        try {
            crawlerRules=crawlerRulesService.getCrawlerRulesById(id);
            result.setData(crawlerRules);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveCrawlerRules")
    @ResponseBody
    public ReturnResult batchRemoveCrawlerRules(String ids){
        Integer flag=0;
        try {
            flag=crawlerRulesService.itriptxBatchDeleteCrawlerRules(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeCrawlerRulesById")
    @ResponseBody
    public ReturnResult removeCrawlerRulesById(Long id){
        Integer flag=0;
        try {
            flag=crawlerRulesService.itriptxDeleteCrawlerRulesById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<CrawlerRules> listCrawlerRules(@RequestParam Map<String,Object> param) {
        List<CrawlerRules> crawlerRulesList = null;
        MapUtil.paramExecute(param);
        try {
            crawlerRulesList  = crawlerRulesService.getCrawlerRulesListByMap(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerRulesList;
    }

}
