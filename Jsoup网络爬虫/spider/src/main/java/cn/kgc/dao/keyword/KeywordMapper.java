package cn.kgc.dao.keyword;
import cn.kgc.beans.Keyword;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.KeywordVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface KeywordMapper {

	public Keyword getKeywordById(@Param(value = "id") Long id)throws Exception;

	public List<KeywordVo>	getKeywordListByMap(Map<String,Object> param)throws Exception;

	public Integer getKeywordCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertKeyword(Keyword keyword)throws Exception;

	public Integer updateKeyword(Keyword keyword)throws Exception;

	public Integer deleteKeywordById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteKeyword(Map<String,List<String>> params);

	public Integer deleteKeywordByMap(Map<String,Object> params);

	public List<KeywordSortVo> statisticListByParam(Map<String,Object> params);

	public Integer statisticCountByParam(Map<String,Object> params);
}
