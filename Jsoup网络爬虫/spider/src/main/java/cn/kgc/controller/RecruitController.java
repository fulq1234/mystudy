package cn.kgc.controller;
import cn.kgc.beans.Recruit;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.recruit.RecruitService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ExportExcel;
import cn.kgc.util.ReturnResultUtil;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.RecruitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.kgc.util.MapUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zezhong.shang on 17-10-16.
 */
@Controller
@RequestMapping("/client/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<RecruitVo> searchRecruit(@RequestParam Map<String,Object> param) {
        Page<RecruitVo> recruitPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo= EmptyUtils.isEmpty(param.get("pageNo"))? Constants.DEFAULT_PAGE_NO:Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize= EmptyUtils.isEmpty(param.get("pageSize"))? Constants.DEFAULT_PAGE_SIZE:Integer.parseInt((String) param.get("pageSize"));
            recruitPage  = recruitService.queryRecruitPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recruitPage;
    }

    @RequestMapping("/saveOrUpdateRecruit")
    @ResponseBody
    public ReturnResult saveOrUpdateRecruit(Recruit recruit){
        Integer flag=0;
        try {
            if(EmptyUtils.isEmpty(recruit.getId())){
                flag=recruitService.itriptxAddRecruit(recruit);
            }else{
                flag=recruitService.itriptxModifyRecruit(recruit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag>=1?ReturnResultUtil.returnSuccess():ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getRecruitById")
    @ResponseBody
    public ReturnResult getRecruitById(Long id){
        ReturnResult result= ReturnResultUtil.returnSuccess();
        Recruit recruit=null;
        try {
            recruit=recruitService.getRecruitById(id);
            result.setData(recruit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveRecruit")
    @ResponseBody
    public ReturnResult batchRemoveRecruit(String ids){
        Integer flag=0;
        try {
            flag=recruitService.itriptxBatchDeleteRecruit(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeRecruitById")
    @ResponseBody
    public ReturnResult removeRecruitById(Long id){
        Integer flag=0;
        try {
            flag=recruitService.itriptxDeleteRecruitById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
    /***
     * 按照条件删除
     * @return
     */
    @RequestMapping("/removeRecruitByCondition")
    @ResponseBody
    public ReturnResult removeRecruitByCondition(@RequestParam Map<String,Object> param) {
        Integer flag=0;
        try {
            MapUtil.paramExecute(param);
            flag=recruitService.removeRecruitByCondition(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }
    //按条件备份
    @RequestMapping("/backByCondition")
    @ResponseBody
    public ReturnResult backByCondition(@RequestParam Map<String,Object> param) {
        Integer flag=0;
        try {
            MapUtil.paramExecute(param);
            flag=recruitService.backByCondition(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    //按条件还原
    @RequestMapping("/rollBackByCondition")
    @ResponseBody
    public ReturnResult rollBackByCondition(@RequestParam Map<String,Object> param) {
        Integer flag=0;
        try {
            MapUtil.paramExecute(param);
            flag=recruitService.rollBackByCondition(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/statisticsByGroup")
    @ResponseBody
    public Page<KeywordSortVo> statisticsByParam(@RequestParam Map<String, Object> param) {
        Page<KeywordSortVo> keywordSortVoPage = null;
        MapUtil.paramExecute(param);
        //参数包括数据类型和任务id
        Integer pageNo = EmptyUtils.isEmpty(param.get("pageNo")) ? Constants.DEFAULT_PAGE_NO : Integer.parseInt((String) param.get("pageNo"));
        Integer pageSize = EmptyUtils.isEmpty(param.get("pageSize")) ? Constants.DEFAULT_PAGE_SIZE : Integer.parseInt((String) param.get("pageSize"));
        try {
            keywordSortVoPage = recruitService.statisticsByGroup(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywordSortVoPage;
    }
    /**
     * 导入excel
     */
    @RequestMapping("/exportDataExcel")
    public void exportHotConnectionExcel(HttpServletResponse response,@RequestParam Map<String, Object> param) {
        // 测试学生
        OutputStream out = null;
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
            response.setHeader("Content-disposition", "attachment;filename=collectionData" + sdf.format(new Date()) + ".xls");
            out = response.getOutputStream();
            ExportExcel<KeywordSortVo> ex = new ExportExcel<KeywordSortVo>();
            String[] headers = {"统计维度", "统计数", "占比"};
            String[] cloumns = {"name", "count", "percent"};
            MapUtil.paramExecute(param);
            List<KeywordSortVo> keywordSortVoList=recruitService.statisticsByGroup(param);
            ex.exportExcel(headers, cloumns, keywordSortVoList, out);
            System.out.println("excel导出成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导入excel
     */
    @RequestMapping("/exportRecuitDataExcel")
    public void exportRecuitDataExcel(HttpServletResponse response,@RequestParam Map<String, Object> param) {
        // 测试学生
        OutputStream out = null;
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
            response.setHeader("Content-disposition", "attachment;filename=collectionData" + sdf.format(new Date()) + ".xls");
            out = response.getOutputStream();
            ExportExcel<RecruitVo> ex = new ExportExcel<RecruitVo>();
            String[] headers = {"主键", "招聘标题", "关键词","公司名称","公司类型","福利","公司地址","薪酬","发布日期","工作经验","学历要求","招聘要求","招聘链接","任务名称"};
            String[] cloumns = {"id", "title", "city","companyName","companyType","welfare","companyAddress","monthlySalary","releaseDate","experience","eucation","jobDescription","detailUrl","taskName"};
            MapUtil.paramExecute(param);
            List<RecruitVo> recruitVoList=recruitService.getRecruitListByMap(param);
            ex.exportExcel(headers, cloumns, recruitVoList, out);
            System.out.println("excel导出成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
