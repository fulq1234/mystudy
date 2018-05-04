package cn.kgc.dao.proxyIp;
import cn.kgc.beans.ProxyIp;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ProxyIpMapper {

	public ProxyIp getProxyIpById(@Param(value = "id") Long id)throws Exception;

	public List<ProxyIp>	getProxyIpListByMap(Map<String,Object> param)throws Exception;

	public Integer getProxyIpCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertProxyIp(ProxyIp proxyIp)throws Exception;

	public Integer updateProxyIp(ProxyIp proxyIp)throws Exception;

	public Integer deleteProxyIpById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteProxyIp(Map<String,List<String>> params);

}
