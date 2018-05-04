package cn.kgc.dao.crawlerDataType;
import cn.kgc.beans.CrawlerDataType;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface CrawlerDataTypeMapper {

	public CrawlerDataType getCrawlerDataTypeById(@Param(value = "id") Long id)throws Exception;

	public List<CrawlerDataType>	getCrawlerDataTypeListByMap(Map<String,Object> param)throws Exception;

	public Integer getCrawlerDataTypeCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception;

	public Integer updateCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception;

	public Integer deleteCrawlerDataTypeById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteCrawlerDataType(Map<String,List<String>> params);

}
