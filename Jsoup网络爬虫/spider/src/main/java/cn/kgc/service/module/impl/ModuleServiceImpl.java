package cn.kgc.service.module.impl;
import cn.kgc.dao.module.ModuleMapper;
import cn.kgc.beans.Module;
import org.springframework.stereotype.Service;
import cn.kgc.service.module.ModuleService;
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
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;

    public Module getModuleById(Long id)throws Exception{
        return moduleMapper.getModuleById(id);
    }

    public List<Module>	getModuleListByMap(Map<String,Object> param)throws Exception{
        return moduleMapper.getModuleListByMap(param);
    }

    public Integer getModuleCountByMap(Map<String,Object> param)throws Exception{
        return moduleMapper.getModuleCountByMap(param);
    }

    public Integer itriptxAddModule(Module module)throws Exception{
            module.setStatus(Constants.Status.ACTIVE);
            module.setCreatdTime(new Date());
            return moduleMapper.insertModule(module);
    }

    public Integer itriptxModifyModule(Module module)throws Exception{
        module.setUpdatedTime(new Date());
        return moduleMapper.updateModule(module);
    }

    public Integer itriptxDeleteModuleById(Long id)throws Exception{
        return moduleMapper.deleteModuleById(id);
    }

    public Page<Module> queryModulePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = moduleMapper.getModuleCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<Module> moduleList = moduleMapper.getModuleListByMap(param);
        page.setRows(moduleList);
        return page;
    }

    public Integer itriptxBatchDeleteModule(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return moduleMapper.batchDeleteModule(param);
    }


}
