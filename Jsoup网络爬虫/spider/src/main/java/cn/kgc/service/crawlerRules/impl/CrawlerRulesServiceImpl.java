package cn.kgc.service.crawlerRules.impl;
import cn.kgc.dao.crawlerRules.CrawlerRulesMapper;
import cn.kgc.beans.CrawlerRules;
import org.springframework.stereotype.Service;
import cn.kgc.service.crawlerRules.CrawlerRulesService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CrawlerRulesServiceImpl implements CrawlerRulesService {

    @Resource
    private CrawlerRulesMapper crawlerRulesMapper;

    public CrawlerRules getCrawlerRulesById(Long id)throws Exception{
        return crawlerRulesMapper.getCrawlerRulesById(id);
    }

    public List<CrawlerRules>	getCrawlerRulesListByMap(Map<String,Object> param)throws Exception{
        return crawlerRulesMapper.getCrawlerRulesListByMap(param);
    }

    public Integer getCrawlerRulesCountByMap(Map<String,Object> param)throws Exception{
        return crawlerRulesMapper.getCrawlerRulesCountByMap(param);
    }

    public Integer itriptxAddCrawlerRules(CrawlerRules crawlerRules)throws Exception{
            //crawlerRules.setCreatedTime(new Date());
            return crawlerRulesMapper.insertCrawlerRules(crawlerRules);
    }

    public Integer itriptxModifyCrawlerRules(CrawlerRules crawlerRules)throws Exception{
        //crawlerRules.setUpdatedTime(new Date());
        return crawlerRulesMapper.updateCrawlerRules(crawlerRules);
    }

    public Integer itriptxDeleteCrawlerRulesById(Long id)throws Exception{
        return crawlerRulesMapper.deleteCrawlerRulesById(id);
    }

    public Page<CrawlerRules> queryCrawlerRulesPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = crawlerRulesMapper.getCrawlerRulesCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<CrawlerRules> crawlerRulesList = crawlerRulesMapper.getCrawlerRulesListByMap(param);
        page.setRows(crawlerRulesList);
        return page;
    }

    public Integer itriptxBatchDeleteCrawlerRules(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return crawlerRulesMapper.batchDeleteCrawlerRules(param);
    }


}
