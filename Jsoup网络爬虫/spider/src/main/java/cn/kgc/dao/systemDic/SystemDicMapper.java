package cn.kgc.dao.systemDic;
import cn.kgc.beans.SystemDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface SystemDicMapper {

	public SystemDic getSystemDicById(@Param(value = "id") Long id)throws Exception;

	public List<SystemDic>	getSystemDicListByMap(Map<String,Object> param)throws Exception;

	public Integer getSystemDicCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertSystemDic(SystemDic systemDic)throws Exception;

	public Integer updateSystemDic(SystemDic systemDic)throws Exception;

	public Integer deleteSystemDicById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteSystemDic(Map<String,List<String>> params);

}
