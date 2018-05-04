package cn.kgc.service.proxyIp.impl;
import cn.kgc.dao.proxyIp.ProxyIpMapper;
import cn.kgc.beans.ProxyIp;
import org.springframework.stereotype.Service;
import cn.kgc.service.proxyIp.ProxyIpService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ProxyIpServiceImpl implements ProxyIpService {

    @Resource
    private ProxyIpMapper proxyIpMapper;

    public ProxyIp getProxyIpById(Long id)throws Exception{
        return proxyIpMapper.getProxyIpById(id);
    }

    public List<ProxyIp>	getProxyIpListByMap(Map<String,Object> param)throws Exception{
        return proxyIpMapper.getProxyIpListByMap(param);
    }

    public Integer getProxyIpCountByMap(Map<String,Object> param)throws Exception{
        return proxyIpMapper.getProxyIpCountByMap(param);
    }

    public Integer itriptxAddProxyIp(ProxyIp proxyIp)throws Exception{
            //proxyIp.setCreatedTime(new Date());
            return proxyIpMapper.insertProxyIp(proxyIp);
    }

    public Integer itriptxModifyProxyIp(ProxyIp proxyIp)throws Exception{
        //proxyIp.setUpdatedTime(new Date());
        return proxyIpMapper.updateProxyIp(proxyIp);
    }

    public Integer itriptxDeleteProxyIpById(Long id)throws Exception{
        return proxyIpMapper.deleteProxyIpById(id);
    }

    public Page<ProxyIp> queryProxyIpPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = proxyIpMapper.getProxyIpCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ProxyIp> proxyIpList = proxyIpMapper.getProxyIpListByMap(param);
        page.setRows(proxyIpList);
        return page;
    }

    public Integer itriptxBatchDeleteProxyIp(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return proxyIpMapper.batchDeleteProxyIp(param);
    }


}
