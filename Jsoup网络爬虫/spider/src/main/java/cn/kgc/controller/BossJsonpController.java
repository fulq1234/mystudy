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
		//for(int pageii = 0;pageii<50;pageii++){//爬几页
		for(int pageii = 0;pageii<1;pageii++){//爬一页
			final String listUrl = url.replaceAll("temp", (pageii+1) + "");
			fixedThreadPool.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//线程内代码
					try {
						Document document = DocumentUtilFree.getDocument(listUrl);//Jsoup.connect(listUrl).get();
						String selector = "h3[class=name]>a";
						Elements elements = document.select(selector);
						for(int i = 0;i<elements.size();i++){
							Element e = elements.get(0);//职位标题
							String title = e.text();
							
							String detailstr = e.absUrl("href");
							
							Document detailD = DocumentUtilFree.getDocument(detailstr);
							
							//城市：石家庄经验：5-10年学历：本科
							String threestr = detailD.select("div .info-primary").select("p").html();
							String[] arr = threestr.split("<em class=\"vline\"></em>");
							String companyAddress = "";
							String experience = "";
							if(arr.length >=2){
								companyAddress = arr[0];//公司地址
								experience =arr[1];//工作经验
								
							}
							
							//公司名称
							String companyName = detailD.select("div .info-company").select(".name").text();
							
							//公司性质
							String companyss = detailD.select("div .info-company").select("p").html();
							String[] carr = companyss.split("<em class=\"vline\"></em>");
							String companyType = carr[0];
							
							//工作描述
							String jobDescription = detailD.select(".job-sec").select(".text").text();
							
							System.out.println(jobDescription);
							
							
							//插入数据
							Recruit recruit = new Recruit();
							recruit.setTitle(title);//标题
							recruit.setDetailUrl(detailstr);//招聘详情页
							
							//公司地址
							recruit.setCompanyAddress(companyAddress);
							//工作经验
							recruit.setExperience(experience);
							//公司名称
							recruit.setCompanyName(companyName);
							//工作详情
							recruit.setJobDescription(jobDescription);
							
							recruit.setTaskId(1);
							recruit.setDataType(1);
							recruit.setStatus(0);
							recruit.setCompanyType(companyType);//不能为空
							try {
								recruitService.itriptxAddRecruit(recruit);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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
