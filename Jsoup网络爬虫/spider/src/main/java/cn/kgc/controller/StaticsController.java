package cn.kgc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kgc.service.keyword.KeywordService;
import cn.kgc.vo.KeywordSortVo;

@Controller
@RequestMapping
public class StaticsController {
	
	@Autowired
	private KeywordService keywordService;
	
	
	@ResponseBody
	@RequestMapping(value="/tongji")
	public List<KeywordSortVo> tongji(){
		List<KeywordSortVo> p;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("beginPos", 0);
			param.put("pageSize", 11);
			p = (List<KeywordSortVo>) keywordService.statisticsByParam(param);
			return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
