package cn.kgc.dao.crawlerTask;
import cn.kgc.beans.CrawlerTask;
import cn.kgc.vo.CrawlerTaskVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface CrawlerTaskMapper {

	public CrawlerTask getCrawlerTaskById(@Param(value = "id") Long id);

	public List<CrawlerTaskVo>	getCrawlerTaskListByMap(Map<String,Object> param)throws Exception;

	public Integer getCrawlerTaskCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCrawlerTask(CrawlerTask crawlerTask)throws Exception;

	public Integer updateCrawlerTask(CrawlerTask crawlerTask)throws Exception;

	public Integer deleteCrawlerTaskById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteCrawlerTask(Map<String,List<String>> params);

}
