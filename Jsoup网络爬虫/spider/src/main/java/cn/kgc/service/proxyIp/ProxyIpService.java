package cn.kgc.service.proxyIp;
import cn.kgc.beans.ProxyIp;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface ProxyIpService {

    public ProxyIp getProxyIpById(Long id)throws Exception;

    public List<ProxyIp>	getProxyIpListByMap(Map<String,Object> param)throws Exception;

    public Integer getProxyIpCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddProxyIp(ProxyIp proxyIp)throws Exception;

    public Integer itriptxModifyProxyIp(ProxyIp proxyIp)throws Exception;

    public Integer itriptxDeleteProxyIpById(Long id)throws Exception;

    public Page<ProxyIp> queryProxyIpPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteProxyIp(String ids)throws Exception;

}
