package cn.kgc.service.keyword;
import cn.kgc.beans.Keyword;

import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.KeywordVo;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface KeywordService {

    public Keyword getKeywordById(Long id)throws Exception;

    public List<KeywordVo> getKeywordListByMap(Map<String,Object> param)throws Exception;

    public Integer getKeywordCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddKeyword(Keyword keyword)throws Exception;

    public Integer itriptxModifyKeyword(Keyword keyword)throws Exception;

    public Integer itriptxDeleteKeywordById(Long id)throws Exception;

    public Page<KeywordVo> queryKeywordPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    public Integer itriptxBatchDeleteKeyword(String ids)throws Exception;

    public Integer deleteKeywordByMap(Map<String,Object> param)throws Exception;

    public Page<KeywordSortVo> statisticsByParam(Map<String, Object> param,Integer pageNo,Integer pageSize) throws Exception;

    public List<KeywordSortVo> statisticsByParam(Map<String, Object> param) throws Exception;

}
