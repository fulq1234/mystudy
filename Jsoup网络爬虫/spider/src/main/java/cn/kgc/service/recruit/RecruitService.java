package cn.kgc.service.recruit;
import cn.kgc.beans.Recruit;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.RecruitVo;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface RecruitService {

    public Recruit getRecruitById(Long id)throws Exception;

    public List<RecruitVo>	getRecruitListByMap(Map<String,Object> param)throws Exception;

    public Integer getRecruitCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddRecruit(Recruit recruit)throws Exception;

    public Integer itriptxModifyRecruit(Recruit recruit)throws Exception;

    public Integer itriptxDeleteRecruitById(Long id)throws Exception;

    public Page<RecruitVo> queryRecruitPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteRecruit(String ids)throws Exception;

    public Integer deleteInvalidData(List<String> keywordList);

    public Integer removeRecruitByCondition(Map<String,Object> param);

    public Integer backByCondition(Map<String,Object> param);

    public Integer rollBackByCondition(Map<String,Object> param);

    public Page<KeywordSortVo> statisticsByGroup(Map<String, Object> param,Integer pageNo,Integer pageSize) throws Exception;

    public List<KeywordSortVo> statisticsByGroup(Map<String, Object> param)throws Exception;
}
