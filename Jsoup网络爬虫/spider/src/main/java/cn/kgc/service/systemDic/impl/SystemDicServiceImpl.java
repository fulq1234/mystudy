package cn.kgc.service.systemDic.impl;
import cn.kgc.dao.systemDic.SystemDicMapper;
import cn.kgc.beans.SystemDic;
import org.springframework.stereotype.Service;
import cn.kgc.service.systemDic.SystemDicService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SystemDicServiceImpl implements SystemDicService {

    @Resource
    private SystemDicMapper systemDicMapper;

    public SystemDic getSystemDicById(Long id)throws Exception{
        return systemDicMapper.getSystemDicById(id);
    }

    public List<SystemDic>	getSystemDicListByMap(Map<String,Object> param)throws Exception{
        return systemDicMapper.getSystemDicListByMap(param);
    }

    public Integer getSystemDicCountByMap(Map<String,Object> param)throws Exception{
        return systemDicMapper.getSystemDicCountByMap(param);
    }

    public Integer itriptxAddSystemDic(SystemDic systemDic)throws Exception{
            //systemDic.setCreatedTime(new Date());
            return systemDicMapper.insertSystemDic(systemDic);
    }

    public Integer itriptxModifySystemDic(SystemDic systemDic)throws Exception{
        //systemDic.setUpdatedTime(new Date());
        return systemDicMapper.updateSystemDic(systemDic);
    }

    public Integer itriptxDeleteSystemDicById(Long id)throws Exception{
        return systemDicMapper.deleteSystemDicById(id);
    }

    public Page<SystemDic> querySystemDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = systemDicMapper.getSystemDicCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<SystemDic> systemDicList = systemDicMapper.getSystemDicListByMap(param);
        page.setRows(systemDicList);
        return page;
    }

    public Integer itriptxBatchDeleteSystemDic(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return systemDicMapper.batchDeleteSystemDic(param);
    }

    @Override
    public SystemDic getSystemDicByKey(String key)throws Exception {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("dicKey",key);
        List<SystemDic> systemDicList=systemDicMapper.getSystemDicListByMap(param);
        if(EmptyUtils.isNotEmpty(systemDicList)){
            return systemDicList.get(0);
        }
        return null;
    }

}
