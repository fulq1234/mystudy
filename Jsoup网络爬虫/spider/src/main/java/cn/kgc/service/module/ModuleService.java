package cn.kgc.service.module;
import cn.kgc.beans.Module;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ModuleService {

    public Module getModuleById(Long id)throws Exception;

    public List<Module>	getModuleListByMap(Map<String,Object> param)throws Exception;

    public Integer getModuleCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddModule(Module module)throws Exception;

    public Integer itriptxModifyModule(Module module)throws Exception;

    public Integer itriptxDeleteModuleById(Long id)throws Exception;

    public Page<Module> queryModulePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteModule(String ids)throws Exception;

}
