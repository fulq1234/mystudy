package cn.kgc.controller;
import cn.kgc.beans.ProxyIp;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.proxyIp.ProxyIpService;
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
@RequestMapping("/client/proxyIp")
public class ProxyIpController {

    @Autowired
    private ProxyIpService proxyIpService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<ProxyIp> searchProxyIp(@RequestParam Map<String,Object> param) {
        Page<ProxyIp> proxyIpPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            proxyIpPage  = proxyIpService.queryProxyIpPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyIpPage;
    }

    @RequestMapping("/saveOrUpdateProxyIp")
    @ResponseBody
    public ReturnResult saveOrUpdateProxyIp(ProxyIp proxyIp){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(proxyIp.getId())){
                flag=proxyIpService.itriptxAddProxyIp(proxyIp);
            }else{
                flag=proxyIpService.itriptxModifyProxyIp(proxyIp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getProxyIpById")
    @ResponseBody
    public ReturnResult getProxyIpById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        ProxyIp proxyIp=null;
        try {
            proxyIp=proxyIpService.getProxyIpById(id);
            result.setData(proxyIp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveProxyIp")
    @ResponseBody
    public ReturnResult batchRemoveProxyIp(String ids){
        Integer flag=0;
        try {
            flag=proxyIpService.itriptxBatchDeleteProxyIp(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeProxyIpById")
    @ResponseBody
    public ReturnResult removeProxyIpById(Long id){
        Integer flag=0;
        try {
            flag=proxyIpService.itriptxDeleteProxyIpById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
}
