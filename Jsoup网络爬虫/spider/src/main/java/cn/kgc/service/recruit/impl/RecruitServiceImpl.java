package cn.kgc.service.recruit.impl;
import cn.kgc.dao.recruit.RecruitMapper;
import cn.kgc.beans.Recruit;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.RecruitVo;
import org.springframework.stereotype.Service;
import cn.kgc.service.recruit.RecruitService;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Resource
    private RecruitMapper recruitMapper;

    public Recruit getRecruitById(Long id)throws Exception{
        return recruitMapper.getRecruitById(id);
    }

    public List<RecruitVo> getRecruitListByMap(Map<String,Object> param)throws Exception{
        return recruitMapper.getRecruitListByMap(param);
    }

    public Integer getRecruitCountByMap(Map<String,Object> param)throws Exception{
        return recruitMapper.getRecruitCountByMap(param);
    }

    public Integer itriptxAddRecruit(Recruit recruit)throws Exception{
            recruit.setCreatedTime(new Date());
            return recruitMapper.insertRecruit(recruit);
    }

    public Integer itriptxModifyRecruit(Recruit recruit)throws Exception{
        recruit.setUpdatedTime(new Date());
        return recruitMapper.updateRecruit(recruit);
    }

    public Integer itriptxDeleteRecruitById(Long id)throws Exception{
        return recruitMapper.deleteRecruitById(id);
    }

    public Page<RecruitVo> queryRecruitPageByMap(Map<String,Object> param, Integer pageNo, Integer pageSize)throws Exception{
        Integer total = recruitMapper.getRecruitCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<RecruitVo> recruitList = recruitMapper.getRecruitListByMap(param);
        page.setRows(recruitList);
        return page;
    }

    public Integer itriptxBatchDeleteRecruit(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return recruitMapper.batchDeleteRecruit(param);
    }

    @Override
    public Integer deleteInvalidData(List<String> keywordList) {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("words",keywordList);
        return recruitMapper.deleteInvalidData(param);
    }

    @Override
    public Integer removeRecruitByCondition(Map<String, Object> param) {
        return recruitMapper.deleteByCondition(param);
    }

    @Override
    public Integer backByCondition(Map<String, Object> param) {
        return recruitMapper.updateRecruitByCondition(param);
    }

    @Override
    public Integer rollBackByCondition(Map<String, Object> param) {
        return recruitMapper.rollBackByCondition(param);
    }

    @Override
    public Page<KeywordSortVo> statisticsByGroup(Map<String, Object> param,Integer pageNo,Integer pageSize) throws Exception{
        Integer total = recruitMapper.statisticCountByGroup(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<KeywordSortVo> keywordSortVoList = recruitMapper.statisticListByGroup(param);
        page.setRows(keywordSortVoList);
        return page;
    }

    public List<KeywordSortVo> statisticsByGroup(Map<String, Object> param)throws Exception{
        List<KeywordSortVo> keywordSortVoList = recruitMapper.statisticListByGroup(param);
        return keywordSortVoList;
    }

}
