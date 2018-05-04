package cn.kgc.controller;

import cn.kgc.beans.Keyword;
import cn.kgc.common.Constants;
import cn.kgc.common.Page;
import cn.kgc.common.ReturnResult;
import cn.kgc.service.keyword.KeywordService;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.ExportExcel;
import cn.kgc.util.ReturnResultUtil;
import cn.kgc.vo.KeywordSortVo;
import cn.kgc.vo.KeywordVo;
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
@RequestMapping("/client/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @RequestMapping("/search")
    @ResponseBody
    public Page<KeywordVo> searchKeyword(@RequestParam Map<String, Object> param) {
        Page<KeywordVo> keywordPage = null;
        MapUtil.paramExecute(param);
        try {
            Integer pageNo = EmptyUtils.isEmpty(param.get("pageNo")) ? Constants.DEFAULT_PAGE_NO : Integer.parseInt((String) param.get("pageNo"));
            Integer pageSize = EmptyUtils.isEmpty(param.get("pageSize")) ? Constants.DEFAULT_PAGE_SIZE : Integer.parseInt((String) param.get("pageSize"));
            keywordPage = keywordService.queryKeywordPageByMap(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywordPage;
    }

    @RequestMapping("/saveOrUpdateKeyword")
    @ResponseBody
    public ReturnResult saveOrUpdateKeyword(Keyword keyword) {
        Integer flag = 0;
        try {
            if (EmptyUtils.isEmpty(keyword.getId())) {
                flag = keywordService.itriptxAddKeyword(keyword);
            } else {
                flag = keywordService.itriptxModifyKeyword(keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag >= 1 ? ReturnResultUtil.returnSuccess() : ReturnResultUtil.returnFail();
    }

    @RequestMapping("/getKeywordById")
    @ResponseBody
    public ReturnResult getKeywordById(Long id) {
        ReturnResult result = ReturnResultUtil.returnSuccess();
        Keyword keyword = null;
        try {
            keyword = keywordService.getKeywordById(id);
            result.setData(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/batchRemoveKeyword")
    @ResponseBody
    public ReturnResult batchRemoveKeyword(String ids) {
        Integer flag = 0;
        try {
            flag = keywordService.itriptxBatchDeleteKeyword(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/removeKeywordById")
    @ResponseBody
    public ReturnResult removeKeywordById(Long id) {
        Integer flag = 0;
        try {
            flag = keywordService.itriptxDeleteKeywordById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResultUtil.returnSuccess();
    }

    @RequestMapping("/statisticsByParam")
    @ResponseBody
    public Page<KeywordSortVo> statisticsByParam(@RequestParam Map<String, Object> param) {
        Page<KeywordSortVo> keywordSortVoPage = null;
        MapUtil.paramExecute(param);
        //参数包括数据类型和任务id
        Integer pageNo = EmptyUtils.isEmpty(param.get("pageNo")) ? Constants.DEFAULT_PAGE_NO : Integer.parseInt((String) param.get("pageNo"));
        Integer pageSize = EmptyUtils.isEmpty(param.get("pageSize")) ? Constants.DEFAULT_PAGE_SIZE : Integer.parseInt((String) param.get("pageSize"));
        try {
            keywordSortVoPage = keywordService.statisticsByParam(param, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywordSortVoPage;
    }


    /**
     * 导入excel
     */
    @RequestMapping("/exportDataExcel")
    public void exportHotConnectionExcel(HttpServletResponse response, @RequestParam Map<String, Object> param) {
        // 测试导出
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
            List<KeywordSortVo> keywordSortVoList=keywordService.statisticsByParam(param);
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


}
