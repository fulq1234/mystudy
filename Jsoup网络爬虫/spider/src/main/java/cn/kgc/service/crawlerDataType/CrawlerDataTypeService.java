package cn.kgc.service.crawlerDataType;
import cn.kgc.beans.CrawlerDataType;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface CrawlerDataTypeService {

    public CrawlerDataType getCrawlerDataTypeById(Long id)throws Exception;

    public List<CrawlerDataType>	getCrawlerDataTypeListByMap(Map<String,Object> param)throws Exception;

    public Integer getCrawlerDataTypeCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception;

    public Integer itriptxModifyCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception;

    public Integer itriptxDeleteCrawlerDataTypeById(Long id)throws Exception;

    public Page<CrawlerDataType> queryCrawlerDataTypePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteCrawlerDataType(String ids)throws Exception;

}
