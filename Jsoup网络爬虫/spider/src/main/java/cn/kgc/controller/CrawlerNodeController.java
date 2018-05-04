package cn.kgc.controller;
import cn.kgc.beans.CrawlerNode;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerNode.CrawlerNodeService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ReturnResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import cn.kgc.util.MapUtil;
/**
 * Created by zezhong.shang on 17-10-16.
 */
@Controller
@RequestMapping("/client/crawlerNode")
public class CrawlerNodeController {

    @Autowired
    private CrawlerNodeService crawlerNodeService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<CrawlerNode> searchCrawlerNode(@RequestParam Map<String,Object> param) {
        Page<CrawlerNode> crawlerNodePage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            crawlerNodePage  = crawlerNodeService.queryCrawlerNodePageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerNodePage;
    }

    @RequestMapping("/saveOrUpdateCrawlerNode")
    @ResponseBody
    public ReturnResult saveOrUpdateCrawlerNode(CrawlerNode crawlerNode){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(crawlerNode.getId())){
                flag=crawlerNodeService.itriptxAddCrawlerNode(crawlerNode);
            }else{
                flag=crawlerNodeService.itriptxModifyCrawlerNode(crawlerNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getCrawlerNodeById")
    @ResponseBody
    public ReturnResult getCrawlerNodeById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        CrawlerNode crawlerNode=null;
        try {
            crawlerNode=crawlerNodeService.getCrawlerNodeById(id);
            result.setData(crawlerNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveCrawlerNode")
    @ResponseBody
    public ReturnResult batchRemoveCrawlerNode(String ids){
        Integer flag=0;
        try {
            flag=crawlerNodeService.itriptxBatchDeleteCrawlerNode(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeCrawlerNodeById")
    @ResponseBody
    public ReturnResult removeCrawlerNodeById(Long id){
        Integer flag=0;
        try {
            flag=crawlerNodeService.itriptxDeleteCrawlerNodeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
}
