package cn.kgc.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.kgc.beans.ProxyIp;

public class ProxyUtilsFree {
	public static int currentIpIndex = 1;//取第几个，每次取，都取下一个
	public static ProxyIp getProxyIp(){		
		return getProxyIp(currentIpIndex);
	}
	
	/**
	 * 在这个地址取出的ip是很多很多的。所以每次都取下一个。
	 * @param index
	 * @return
	 */
	public static ProxyIp getProxyIp(int index){
		String url = "http://www.xicidaili.com/nn/pagetemp";
		//一页显示100
		int page = index/100;
		url = url.replaceAll("pagetemp", (page + 1) + "");//第几页
		int limit = index%100;//第几行
		
		Document document;
		try {
			document = Jsoup.connect(url).get();
			Elements trs = document.select("tr[class=odd]");
			if(limit > trs.size()){//如果取完了ip，重新再取一遍
				currentIpIndex = 1;
			}
			//for(int i = 0;i<trs.size();i++){
				Element e = trs.get(limit - 1);
				
				//第二列是ip
				String ip = e.select("td:eq(1)").text();
				
				//第三列是端口号
				String port = e.select("td:eq(2)").text(); 
				ProxyIp pp = new ProxyIp();  
	            pp.setPort(Integer.parseInt(port));  
	            pp.setIp(ip);  
	            System.out.println(ip + "\t" +port);
	            
	            currentIpIndex ++;
				return pp;
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			currentIpIndex ++;
		}
		
		return null;
		
	}
	
}
