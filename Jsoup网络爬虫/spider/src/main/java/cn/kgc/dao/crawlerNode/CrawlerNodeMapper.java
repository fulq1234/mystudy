package cn.kgc.dao.crawlerNode;
import cn.kgc.beans.CrawlerNode;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface CrawlerNodeMapper {

	public CrawlerNode getCrawlerNodeById(@Param(value = "id") Long id)throws Exception;

	public List<CrawlerNode>	getCrawlerNodeListByMap(Map<String,Object> param)throws Exception;

	public Integer getCrawlerNodeCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCrawlerNode(CrawlerNode crawlerNode)throws Exception;

	public Integer updateCrawlerNode(CrawlerNode crawlerNode)throws Exception;

	public Integer deleteCrawlerNodeById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteCrawlerNode(Map<String,List<String>> params);

}
