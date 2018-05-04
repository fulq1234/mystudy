package cn.kgc.controller;
import cn.kgc.beans.CrawlerDataType;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.crawlerDataType.CrawlerDataTypeService;
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
@RequestMapping("/client/crawlerDataType")
public class CrawlerDataTypeController {

    @Autowired
    private CrawlerDataTypeService crawlerDataTypeService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<CrawlerDataType> searchCrawlerDataType(@RequestParam Map<String,Object> param) {
        Page<CrawlerDataType> crawlerDataTypePage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            crawlerDataTypePage  = crawlerDataTypeService.queryCrawlerDataTypePageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerDataTypePage;
    }

    @RequestMapping("/saveOrUpdateCrawlerDataType")
    @ResponseBody
    public ReturnResult saveOrUpdateCrawlerDataType(CrawlerDataType crawlerDataType){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(crawlerDataType.getId())){
                flag=crawlerDataTypeService.itriptxAddCrawlerDataType(crawlerDataType);
            }else{
                flag=crawlerDataTypeService.itriptxModifyCrawlerDataType(crawlerDataType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getCrawlerDataTypeById")
    @ResponseBody
    public ReturnResult getCrawlerDataTypeById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        CrawlerDataType crawlerDataType=null;
        try {
            crawlerDataType=crawlerDataTypeService.getCrawlerDataTypeById(id);
            result.setData(crawlerDataType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveCrawlerDataType")
    @ResponseBody
    public ReturnResult batchRemoveCrawlerDataType(String ids){
        Integer flag=0;
        try {
            flag=crawlerDataTypeService.itriptxBatchDeleteCrawlerDataType(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeCrawlerDataTypeById")
    @ResponseBody
    public ReturnResult removeCrawlerDataTypeById(Long id){
        Integer flag=0;
        try {
            flag=crawlerDataTypeService.itriptxDeleteCrawlerDataTypeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
}
