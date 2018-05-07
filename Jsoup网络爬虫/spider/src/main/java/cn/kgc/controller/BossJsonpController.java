package cn.kgc.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kgc.beans.Recruit;
import cn.kgc.service.recruit.RecruitService;
import cn.kgc.util.DocumentUtilFree;

/**
 * 使用爬虫jsonp，boss直聘网址有反爬机制，所以使用“反反爬”破解，
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/boss")
public class BossJsonpController {
	@Autowired
	private RecruitService recruitService;
	
	@ResponseBody
	@RequestMapping("/spider")
	public String spider(){	
		String url = "https://www.zhipin.com/c101010100/h_101010100/?query=java&page=temp&ka=page-temp";
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);//创建一个定长线程池，可控制线程最大并发数，超过的线程会在队列中等待。
		for(int i = 0;i<2;i++){//爬几页
			final String listUrl = url.replaceAll("temp", (i+1) + "");
			fixedThreadPool.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//线程内代码
					try {
						Document document = DocumentUtilFree.getDocument(listUrl);//Jsoup.connect(listUrl).get();
						String selector = "h3[class=name]>a";
						Elements elements = document.select(selector);
						/*for(int i = 0;i<elements.size();i++){
							
						}*/
						Element e = elements.get(0);//职位标题
						String title = e.text();
						
						
						
						//插入数据
						Recruit recruit = new Recruit();
						recruit.setTitle(title);
						
						recruit.setTaskId(1);
						recruit.setDataType(1);
						try {
							recruitService.itriptxAddRecruit(recruit);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
		return "success";
		
	}
}
