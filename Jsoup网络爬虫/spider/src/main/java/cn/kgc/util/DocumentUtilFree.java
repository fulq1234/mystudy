package cn.kgc.util;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.kgc.beans.ProxyIp;
import cn.kgc.common.Constants;

/**
 * Created by zezhong.shang on 17-9-19.
 */
public class DocumentUtilFree {

    static Logger logger = Logger.getLogger(DocumentUtilFree.class);

    /** 
    url:要爬取的地址 
**/  
public static Document getDocument(String url){  
   Connection connection = Jsoup.connect(url);  
   String usergent = Constants.UserAgent.Chrome;//设置合法的useragent  
   connection.userAgent(usergent);//不同浏览器有不同的userAgent  
   //connection.header();  

   //设置动态代理ip  
   Document document = setProxyIp(connection);  
   return document;  

}  

    public static Document setProxyIp(Connection connection){
    	Document document = null;
    	ProxyIp proxyIp = new ProxyIp();
    	try{
    		proxyIp = ProxyUtilsFree.getProxyIp();
    		if(proxyIp == null){
    			return setProxyIp(connection);
    		}
    		connection.proxy(proxyIp.getIp(),proxyIp.getPort());
    		document = connection.get();
    	}catch(IOException e){
           // e.printStackTrace();
    		return setProxyIp(connection);
        }
        return document;  
    	
    	
    }
    
}
