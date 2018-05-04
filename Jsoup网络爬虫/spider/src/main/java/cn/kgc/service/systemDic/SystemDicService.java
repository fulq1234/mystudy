package cn.kgc.service.systemDic;
import cn.kgc.beans.SystemDic;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface SystemDicService {

    public SystemDic getSystemDicById(Long id)throws Exception;

    public List<SystemDic>	getSystemDicListByMap(Map<String,Object> param)throws Exception;

    public Integer getSystemDicCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddSystemDic(SystemDic systemDic)throws Exception;

    public Integer itriptxModifySystemDic(SystemDic systemDic)throws Exception;

    public Integer itriptxDeleteSystemDicById(Long id)throws Exception;

    public Page<SystemDic> querySystemDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteSystemDic(String ids)throws Exception;

    public SystemDic getSystemDicByKey(String key)throws Exception;

}
