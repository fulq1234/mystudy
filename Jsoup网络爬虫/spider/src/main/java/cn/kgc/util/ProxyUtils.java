package cn.kgc.util;
import cn.kgc.beans.ProxyIp;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import java.io.IOException;
/**
 * Created by zezhong.shang on 17-9-19.
 */
public class ProxyUtils {

    static Logger logger=Logger.getLogger(ProxyUtils.class);

    public static ProxyIp getProxyIp() {
        String ipUrl = "http://dynamic.goubanjia.com/dynamic/get/32ea0e44b1497118f5fda8b5e5e22748.html";
        ProxyIp proxyIp = new ProxyIp();
        try {
            String ipstr = Jsoup.connect(ipUrl).get().text();
            proxyIp.setIp(ipstr.split(":")[0]);
            proxyIp.setPort(Integer.parseInt(ipstr.split(":")[1]));
        } catch (IOException e) {
            logger.info(e.getCause()+"：获取动态IP异常,暂休2秒");
            try {
                Thread.sleep(2000);
                return getProxyIp();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        return  proxyIp;
    }

    public static void main(String[] args) {

    }
}
