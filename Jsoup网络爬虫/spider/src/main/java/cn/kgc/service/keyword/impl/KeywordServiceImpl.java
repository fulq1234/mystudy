package cn.kgc.service.keyword.impl;
import cn.kgc.dao.keyword.KeywordMapper;
import cn.kgc.beans.Keyword;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.KeywordVo;
import org.springframework.stereotype.Service;
import cn.kgc.service.keyword.KeywordService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import cn.kgc.common.Page;
import cn.kgc.common.Constants;
import cn.kgc.util.EmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Resource
    private KeywordMapper keywordMapper;

    public Keyword getKeywordById(Long id)throws Exception{
        return keywordMapper.getKeywordById(id);
    }

    public List<KeywordVo>	getKeywordListByMap(Map<String,Object> param)throws Exception{
        return keywordMapper.getKeywordListByMap(param);
    }

    public Integer getKeywordCountByMap(Map<String,Object> param)throws Exception{
        return keywordMapper.getKeywordCountByMap(param);
    }

    public Integer itriptxAddKeyword(Keyword keyword)throws Exception{
            //keyword.setCreatedTime(new Date());
            return keywordMapper.insertKeyword(keyword);
    }

    public Integer itriptxModifyKeyword(Keyword keyword)throws Exception{
        //keyword.setUpdatedTime(new Date());
        return keywordMapper.updateKeyword(keyword);
    }

    public Integer itriptxDeleteKeywordById(Long id)throws Exception{
        return keywordMapper.deleteKeywordById(id);
    }

    public Page<KeywordVo> queryKeywordPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = keywordMapper.getKeywordCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<KeywordVo> keywordList = keywordMapper.getKeywordListByMap(param);
        page.setRows(keywordList);
        return page;
    }

    public Integer itriptxBatchDeleteKeyword(String ids)throws Exception{
        Map<String,List<String>> param=new HashMap<String,List<String>>();
        String[] paramArrays=ids.split(",");
        List<String> idList=new ArrayList<String>();
            for (String temp:paramArrays){
                idList.add(temp);
            }
        param.put("ids",idList);
        return keywordMapper.batchDeleteKeyword(param);
    }

    @Override
    public Integer deleteKeywordByMap(Map<String, Object> param) throws Exception{
       return keywordMapper.deleteKeywordByMap(param);
    }

    @Override
    public Page<KeywordSortVo> statisticsByParam(Map<String, Object> param,Integer pageNo,Integer pageSize) throws Exception{
        Integer total = keywordMapper.statisticCountByParam(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<KeywordSortVo> KeywordSortVoList = keywordMapper.statisticListByParam(param);
        page.setRows(KeywordSortVoList);
        return page;
    }

    @Override
    public List<KeywordSortVo> statisticsByParam(Map<String, Object> param) throws Exception {
        return keywordMapper.statisticListByParam(param);
    }
}
