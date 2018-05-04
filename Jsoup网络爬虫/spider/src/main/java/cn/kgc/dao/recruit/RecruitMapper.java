package cn.kgc.dao.recruit;
import cn.kgc.beans.Recruit;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.RecruitVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface RecruitMapper {

	public Recruit getRecruitById(@Param(value = "id") Long id)throws Exception;

	public List<RecruitVo> getRecruitListByMap(Map<String,Object> param)throws Exception;

	public Integer getRecruitCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertRecruit(Recruit recruit)throws Exception;

	public Integer updateRecruit(Recruit recruit)throws Exception;

	public Integer deleteRecruitById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteRecruit(Map<String,List<String>> params);

	public Integer deleteInvalidData(Map<String,Object> params);

	public Integer deleteByCondition(Map<String,Object> params);

	public Integer updateRecruitByCondition(Map<String,Object> params);

	public Integer rollBackByCondition(Map<String,Object> params);

	public List<KeywordSortVo> statisticListByGroup(Map<String,Object> params);

	public Integer statisticCountByGroup(Map<String,Object> params);
}
