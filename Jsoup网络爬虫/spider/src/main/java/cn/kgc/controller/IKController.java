package cn.kgc.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kgc.beans.Keyword;
import cn.kgc.service.keyword.KeywordService;
import cn.kgc.service.recruit.RecruitService;
import cn.kgc.util.IKUtils;
import cn.kgc.vo.RecruitVo;

@Controller
@RequestMapping("/ik")
public class IKController {
	@Resource  
    private RecruitService rService;  
  
    @Resource  
    private KeywordService kservice;  
      
    @RequestMapping("/ikDataDeal")  
    @ResponseBody  
    public String ikDataDeal(){  
    	//遍历招聘信息
        Map<String,Object> map = new HashMap<String,Object>();  
        map.put("datatype",1);//数据类型,1:后端人员。如果是2就是前段人员  
        List<RecruitVo> list;
		try {
			list = rService.getRecruitListByMap(map);
			//遍历招聘信息，将所有的招聘信息进行分词处理  
	        for(int i = 0;i<list.size();i++){  
	            String jobDesc = list.get(i).getJobDescription();  
	            Integer rid = list.get(i).getId();  
	            //再用刚才写的类进行分词处理  
	            Set<String> set = new HashSet<String>();  
	            set = IKUtils.getKeyWord(jobDesc);  
	              
	            for(String word: set){  
	                Keyword keyword = new Keyword();//实体类和keyword表进行映射  
	                keyword.setRid(rid);  
	                keyword.setName(word);//分词出的每一个词  
	                keyword.setStatus(0);//0:没有删除;1:已经删除  
	                keyword.setDataType(1);  
	                kservice.itriptxAddKeyword(keyword);//入库操作  
	            }  
	        }  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		return "success";
    }  
}
