package cn.kgc.dao.crawlerRules;
import cn.kgc.beans.CrawlerRules;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface CrawlerRulesMapper {

	public CrawlerRules getCrawlerRulesById(@Param(value = "id") Long id)throws Exception;

	public List<CrawlerRules>	getCrawlerRulesListByMap(Map<String,Object> param)throws Exception;

	public Integer getCrawlerRulesCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCrawlerRules(CrawlerRules crawlerRules)throws Exception;

	public Integer updateCrawlerRules(CrawlerRules crawlerRules)throws Exception;

	public Integer deleteCrawlerRulesById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteCrawlerRules(Map<String,List<String>> params);

}
