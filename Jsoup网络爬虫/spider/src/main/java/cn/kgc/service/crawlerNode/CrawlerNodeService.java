package cn.kgc.service.crawlerNode;
import cn.kgc.beans.CrawlerNode;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface CrawlerNodeService {

    public CrawlerNode getCrawlerNodeById(Long id)throws Exception;

    public List<CrawlerNode>	getCrawlerNodeListByMap(Map<String,Object> param)throws Exception;

    public Integer getCrawlerNodeCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddCrawlerNode(CrawlerNode crawlerNode)throws Exception;

    public Integer itriptxModifyCrawlerNode(CrawlerNode crawlerNode)throws Exception;

    public Integer itriptxDeleteCrawlerNodeById(Long id)throws Exception;

    public Page<CrawlerNode> queryCrawlerNodePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteCrawlerNode(String ids)throws Exception;

}
