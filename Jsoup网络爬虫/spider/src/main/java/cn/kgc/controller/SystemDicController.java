package cn.kgc.controller;
import cn.kgc.beans.SystemDic;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.systemDic.SystemDicService;
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
@RequestMapping("/client/systemDic")
public class SystemDicController {

    @Autowired
    private SystemDicService systemDicService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<SystemDic> searchSystemDic(@RequestParam Map<String,Object> param) {
        Page<SystemDic> systemDicPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            systemDicPage  = systemDicService.querySystemDicPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemDicPage;
    }

    @RequestMapping("/saveOrUpdateSystemDic")
    @ResponseBody
    public ReturnResult saveOrUpdateSystemDic(SystemDic systemDic){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(systemDic.getId())){
                flag=systemDicService.itriptxAddSystemDic(systemDic);
            }else{
                flag=systemDicService.itriptxModifySystemDic(systemDic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getSystemDicById")
    @ResponseBody
    public ReturnResult getSystemDicById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        SystemDic systemDic=null;
        try {
            systemDic=systemDicService.getSystemDicById(id);
            result.setData(systemDic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveSystemDic")
    @ResponseBody
    public ReturnResult batchRemoveSystemDic(String ids){
        Integer flag=0;
        try {
            flag=systemDicService.itriptxBatchDeleteSystemDic(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeSystemDicById")
    @ResponseBody
    public ReturnResult removeSystemDicById(Long id){
        Integer flag=0;
        try {
            flag=systemDicService.itriptxDeleteSystemDicById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
}
