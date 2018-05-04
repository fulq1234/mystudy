package cn.kgc.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kgc.beans.Recruit;
import cn.kgc.service.recruit.RecruitService;

/**
 * 智联招聘
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/zhilian")
public class ZhiLianController {
	
	@Autowired
	private RecruitService recruitService;
	
	@ResponseBody
	@RequestMapping("/spider")
	public String spider(){	
		String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E5%94%90%E5%B1%B1&kw=java&sm=0&sg=e9e2888d52b44db4bb74ef46398ffd25&p=temp";
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);//创建一个定长线程池，可控制线程最大并发数，超过的线程会在队列中等待。
		for(int i = 0;i<2;i++){//爬几页
			final String listUrl = url.replaceAll("temp", (i+1) + "");
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
							String ss = e.text();
							String zwmc = e.select("td[class=zwmc]").text();//职位名称
							String gsmc = e.select("td[class=gsmc]").text();//公司名称
							String zwyx = e.select("td[class=zwyx]").text();//职位月薪
							String gzdd = e.select("td[class=gzdd]").text();//工作地点
							
							//插入数据
							Recruit recruit = new Recruit();
							recruit.setTitle(zwmc);
							recruit.setCompanyName(gsmc);
							recruit.setMonthlySalary(zwyx);
							recruit.setCompanyAddress(gzdd);
							recruit.setTaskId(1);
							recruit.setDataType(1);
							try {
								recruitService.itriptxAddRecruit(recruit);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//System.out.println(zwmc + "/t" + gsmc + "/t" + zwyx +"/t" + gzdd);
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
		return "success";
		
	}
}
