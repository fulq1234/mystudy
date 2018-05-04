package cn.kgc.dao.module;
import cn.kgc.beans.Module;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ModuleMapper {

	public Module getModuleById(@Param(value = "id") Long id)throws Exception;

	public List<Module>	getModuleListByMap(Map<String,Object> param)throws Exception;

	public Integer getModuleCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertModule(Module module)throws Exception;

	public Integer updateModule(Module module)throws Exception;

	public Integer deleteModuleById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteModule(Map<String,List<String>> params);

}
