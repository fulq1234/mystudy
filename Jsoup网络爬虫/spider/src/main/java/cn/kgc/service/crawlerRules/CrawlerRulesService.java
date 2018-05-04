package cn.kgc.service.crawlerRules;
import cn.kgc.beans.CrawlerRules;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface CrawlerRulesService {

    public CrawlerRules getCrawlerRulesById(Long id)throws Exception;

    public List<CrawlerRules>	getCrawlerRulesListByMap(Map<String,Object> param)throws Exception;

    public Integer getCrawlerRulesCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddCrawlerRules(CrawlerRules crawlerRules)throws Exception;

    public Integer itriptxModifyCrawlerRules(CrawlerRules crawlerRules)throws Exception;

    public Integer itriptxDeleteCrawlerRulesById(Long id)throws Exception;

    public Page<CrawlerRules> queryCrawlerRulesPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteCrawlerRules(String ids)throws Exception;

}
