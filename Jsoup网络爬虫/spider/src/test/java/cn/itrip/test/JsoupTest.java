package cn.itrip.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import cn.kgc.beans.Recruit;

public class JsoupTest {

	public static void main(String[] args) {
		zhiLianTest();//智联招聘
	}
	
	/**
	 * 智联招聘
	 */
	public static void zhiLianTest(){
		String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E9%80%89%E6%8B%A9%E5%9C%B0%E5%8C%BA&kw=java&isadv=0&sg=172c6d7dcafe4755ad7cec36bd1d3683&p=temp";
		
		//创建一个定长线程池，可控制线程最大并发数，超过的线程会在队列中等待。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		for(int pageii = 0;pageii<50;pageii++){//爬几页
			final String listUrl = url.replaceAll("temp", (pageii+1) + "");
			final int page = pageii + 1;
			fixedThreadPool.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//线程内代码
					try {
						Document document = Jsoup.connect(listUrl).get();
						String selector = "table[class=newlist]";
						Elements elements = document.select(selector);
						for(int i = 1;i<elements.size();i++){
							Element e = elements.get(i);
							//String ss = e.text();
							String zwmc = e.select("td[class=zwmc]").text();//职位名称
							String gsmc = e.select("td[class=gsmc]").text();//公司名称
							String zwyx = e.select("td[class=zwyx]").text();//职位月薪
							String gzdd = e.select("td[class=gzdd]").text();//工作地点
							
							String detailurl = e.select("td[class=zwmc]").select("a").attr("href");//详情页
							Document detailD = Jsoup.connect(detailurl).get();
							
							
							String jobDescription = "";
							Elements detailEs = detailD.select("div .terminalpage-main .tab-cont-box .tab-inner-cont");
							if(detailEs.size() > 0){
								Element de = detailEs.get(0);//第一个是岗位职责
								jobDescription = de.text();//岗位职责
							}
							
							//公司的详情页
							String companyurl = e.select("td[class=gsmc]").select("a").attr("href");
							Document companyD = Jsoup.connect(companyurl).get();
							Elements cElements = companyD.select(".comTinyDes").select("tr");
							
							//公司类型
							String companyType = "";//cElements.get(0).select("td").eq(1).text();
							//公司网址
							String companyUrl = "";//cElements.get(2).select("td").eq(1).text();
							
							for(Element ce : cElements){
								String td1 = ce.select("td").eq(0).text();
								String td2 = ce.select("td").eq(1).text();
								if(td1.contains("公司性质")){
									companyType = td2;
								}else if(td1.contains("公司网站")){
									companyUrl = td2;
								}
							}
							//插入数据
							System.out.println("page"+page+"i"+i+"title:" + zwmc);
							System.out.println("page"+page+"i"+i+"companyname:"+gsmc);
							System.out.println("page"+page+"i"+i+"monthsalary:"+zwyx);
							System.out.println("page"+page+"i"+i+"companyaddress:"+gzdd);
							System.out.println("page"+page+"i"+i+"companyType:"+companyType);
							System.out.println("page"+page+"i"+i+"companyUrl:"+companyUrl);
							System.out.println("page"+page+"i"+i+"detailurl:"+detailurl);
							System.out.println("page"+page+"i"+i+"jobDescription:"+jobDescription);
							
							
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
		
	}
	
	
	
}
