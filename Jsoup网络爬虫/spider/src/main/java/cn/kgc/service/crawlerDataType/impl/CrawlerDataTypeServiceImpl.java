package cn.kgc.service.crawlerDataType.impl;
import cn.kgc.dao.crawlerDataType.CrawlerDataTypeMapper;
import cn.kgc.beans.CrawlerDataType;
import org.springframework.stereotype.Service;
import cn.kgc.service.crawlerDataType.CrawlerDataTypeService;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CrawlerDataTypeServiceImpl implements CrawlerDataTypeService {

    @Resource
    private CrawlerDataTypeMapper crawlerDataTypeMapper;

    public CrawlerDataType getCrawlerDataTypeById(Long id)throws Exception{
        return crawlerDataTypeMapper.getCrawlerDataTypeById(id);
    }

    public List<CrawlerDataType>	getCrawlerDataTypeListByMap(Map<String,Object> param)throws Exception{
        return crawlerDataTypeMapper.getCrawlerDataTypeListByMap(param);
    }

    public Integer getCrawlerDataTypeCountByMap(Map<String,Object> param)throws Exception{
        return crawlerDataTypeMapper.getCrawlerDataTypeCountByMap(param);
    }

    public Integer itriptxAddCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception{
            crawlerDataType.setCreatedTime(new Date());
            return crawlerDataTypeMapper.insertCrawlerDataType(crawlerDataType);
    }

    public Integer itriptxModifyCrawlerDataType(CrawlerDataType crawlerDataType)throws Exception{
        crawlerDataType.setUpdatedTime(new Date());
        return crawlerDataTypeMapper.updateCrawlerDataType(crawlerDataType);
    }

    public Integer itriptxDeleteCrawlerDataTypeById(Long id)throws Exception{
        return crawlerDataTypeMapper.deleteCrawlerDataTypeById(id);
    }

    public Page<CrawlerDataType> queryCrawlerDataTypePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = crawlerDataTypeMapper.getCrawlerDataTypeCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<CrawlerDataType> crawlerDataTypeList = crawlerDataTypeMapper.getCrawlerDataTypeListByMap(param);
        page.setRows(crawlerDataTypeList);
        return page;
    }

    public Integer itriptxBatchDeleteCrawlerDataType(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return crawlerDataTypeMapper.batchDeleteCrawlerDataType(param);
    }


}
