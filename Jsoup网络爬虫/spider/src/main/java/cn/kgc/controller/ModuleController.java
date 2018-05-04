package cn.kgc.controller;
import cn.kgc.beans.Module;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.module.ModuleService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ReturnResultUtil;
import cn.kgc.vo.ModuleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.kgc.util.MapUtil;
/**
 * Created by zezhong.shang on 17-10-16.
 */
@Controller
@RequestMapping("/client/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<ModuleVo> searchModule(@RequestParam Map<String,Object> param) {
        Page<ModuleVo> moduleVoPage=new Page<ModuleVo>();
        List<ModuleVo> moduleVoList=new ArrayList<ModuleVo>();
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            Page<Module> modulePage  = moduleService.queryModulePageByMap(param, pageNo, pageSize);
            BeanUtils.copyProperties(modulePage,moduleVoPage);
            List<Module> moduleList=modulePage.getRows();
            for (Module module:moduleList){
                ModuleVo moduleVo=new ModuleVo();
                BeanUtils.copyProperties(module,moduleVo);
                param.put("parent",module.getId());
                List<Module> childList=moduleService.getModuleListByMap(param);
                moduleVo.setChildren(childList);
                moduleVo.setParentName(module.getName());
                moduleVoList.add(moduleVo);
            }
            moduleVoPage.setRows(moduleVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moduleVoPage;
    }

    @RequestMapping("/saveOrUpdateModule")
    @ResponseBody
    public ReturnResult saveOrUpdateModule(Module module){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(module.getId())){
                flag=moduleService.itriptxAddModule(module);
            }else{
                flag=moduleService.itriptxModifyModule(module);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getModuleById")
    @ResponseBody
    public ReturnResult getModuleById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        Module module=null;
        try {
            module=moduleService.getModuleById(id);
            result.setData(module);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveModule")
    @ResponseBody
    public ReturnResult batchRemoveModule(String ids){
        Integer flag=0;
        try {
            flag=moduleService.itriptxBatchDeleteModule(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeModuleById")
    @ResponseBody
    public ReturnResult removeModuleById(Long id){
        Integer flag=0;
        try {
            flag=moduleService.itriptxDeleteModuleById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/queryModuleByParent")
    @ResponseBody
    public ReturnResult queryModuleByParent(Integer parent){
        ReturnResult returnResult=ReturnResultUtil.returnSuccess();
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("parent",parent);
        List<Module> moduleList=null;
        try {
            moduleList=moduleService.getModuleListByMap(param);
            returnResult.setData(moduleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnResult;
    }

    @RequestMapping("/queryModuleList")
    @ResponseBody
    public ReturnResult queryModuleList(Long id){
        ReturnResult returnResult=ReturnResultUtil.returnSuccess();
        List<ModuleVo> moduleVoList=new ArrayList<ModuleVo>();
        Map<String,Object> param=new HashMap<String,Object>();
        try {
            param.put("parent","0");
            List<Module> moduleList=moduleService.getModuleListByMap(param);
            for (Module module:moduleList){
                ModuleVo moduleVo=new ModuleVo();
                BeanUtils.copyProperties(module,moduleVo);
                param.put("parent",module.getId());
                List<Module> childList=moduleService.getModuleListByMap(param);
                moduleVo.setChildren(childList);
                moduleVo.setParentName(module.getName());
                moduleVoList.add(moduleVo);
            }
            returnResult.setData(moduleVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnResult;
    }
}
