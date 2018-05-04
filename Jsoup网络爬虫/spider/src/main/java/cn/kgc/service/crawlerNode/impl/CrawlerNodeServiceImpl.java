package cn.kgc.service.crawlerNode.impl;
import cn.kgc.dao.crawlerNode.CrawlerNodeMapper;
import cn.kgc.beans.CrawlerNode;
import org.springframework.stereotype.Service;
import cn.kgc.service.crawlerNode.CrawlerNodeService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CrawlerNodeServiceImpl implements CrawlerNodeService {

    @Resource
    private CrawlerNodeMapper crawlerNodeMapper;

    public CrawlerNode getCrawlerNodeById(Long id)throws Exception{
        return crawlerNodeMapper.getCrawlerNodeById(id);
    }

    public List<CrawlerNode>	getCrawlerNodeListByMap(Map<String,Object> param)throws Exception{
        return crawlerNodeMapper.getCrawlerNodeListByMap(param);
    }

    public Integer getCrawlerNodeCountByMap(Map<String,Object> param)throws Exception{
        return crawlerNodeMapper.getCrawlerNodeCountByMap(param);
    }

    public Integer itriptxAddCrawlerNode(CrawlerNode crawlerNode)throws Exception{
            //crawlerNode.setCreatedTime(new Date());
            return crawlerNodeMapper.insertCrawlerNode(crawlerNode);
    }

    public Integer itriptxModifyCrawlerNode(CrawlerNode crawlerNode)throws Exception{
        //crawlerNode.setUpdatedTime(new Date());
        return crawlerNodeMapper.updateCrawlerNode(crawlerNode);
    }

    public Integer itriptxDeleteCrawlerNodeById(Long id)throws Exception{
        return crawlerNodeMapper.deleteCrawlerNodeById(id);
    }

    public Page<CrawlerNode> queryCrawlerNodePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = crawlerNodeMapper.getCrawlerNodeCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<CrawlerNode> crawlerNodeList = crawlerNodeMapper.getCrawlerNodeListByMap(param);
        page.setRows(crawlerNodeList);
        return page;
    }

    public Integer itriptxBatchDeleteCrawlerNode(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return crawlerNodeMapper.batchDeleteCrawlerNode(param);
    }


}
