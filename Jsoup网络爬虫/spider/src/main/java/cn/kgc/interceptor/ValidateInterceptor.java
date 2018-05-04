package cn.kgc.interceptor;
import cn.kgc.common.BaseException;
import cn.kgc.common.Constants;
import cn.kgc.common.ReturnResult;
import cn.kgc.util.EmptyUtils;
import cn.kgc.util.PrintUtil;
import cn.kgc.util.PropCache;
import cn.kgc.util.ReturnResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 *
 * @author zzshang
 * @version v1.0
 * @since 2015/5/19
 */
public class ValidateInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(ValidateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintUtil print = new PrintUtil(response);
        String servletPath = request.getServletPath();
        //获取URL
        String requestKey = servletPath.substring(1, servletPath.length()).replaceAll("/", ".");
        //先判断空值
        String validateParams = PropCache.getValue("prop/validateNull", requestKey);
        Map<String, String[]> params = request.getParameterMap();
        if (!EmptyUtils.isEmpty(validateParams)) {
            //如果参数为空
            if (EmptyUtils.isEmpty(params)) {
                String message = PropCache.getValue("prop/nullResp", "common.null");
                print.print(ReturnResultUtil.returnFail(message).toJson());
                return false;
            }
            String validateParamsArray[] = validateParams.split(",");
            for (String param : validateParamsArray) {
                if (EmptyUtils.isEmpty(params.get(param))) {
                    String message = PropCache.getValue("prop/nullResp", requestKey + "." + param);
                    print.print(ReturnResultUtil.returnFail(message).toJson());
                    return false;
                }
            }
        }
        //如果参数需要验证是否符合正则表达式
        if (!EmptyUtils.isEmpty(params)) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String key = entry.getKey();
                String values[] = entry.getValue();
                if (!EmptyUtils.isEmpty(values)) {
                    String validateReg = PropCache.getValue("prop/validateReg", requestKey + "." + key);
                    //如果需要验证
                    if (!EmptyUtils.isEmpty(validateReg)) {
                        Pattern pattern = Pattern.compile(validateReg);
                        for (String value : values) {
                            Matcher matcher = pattern.matcher(value.toString());
                            if (!matcher.matches()) {
                                String message = PropCache.getValue("prop/regResp", requestKey + "." + key);
                                print.print(ReturnResultUtil.returnFail(message).toJson());
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 客户端返回异常处理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws java.io.IOException
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintUtil print = new PrintUtil(response);
        ReturnResult returnResult =new ReturnResult();
        logger.info(request.getRequestURI() + ">>>>>>");
        logger.info(request.getRequestURL() + ">>>>>>");
        //拦截异常信息
        if (EmptyUtils.isNotEmpty(ex)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try {
                if (ex instanceof BaseException) {
                    BaseException se = (BaseException) ex;
                    String status = se.getExceptionCode();
                    String message = PropCache.getValue("prop/exception", status);
                    returnResult.setStatus(status);
                    returnResult.setMessage(message);
                } else {
                    returnResult.setStatus(Constants.ReturnResult.STATUS.FAIL);
                    returnResult.setMessage(PropCache.getValue("prop/exception", "common.exception"));
                }
                print.print(JSONObject.toJSONString(returnResult));
            } catch (Exception e) {
                if (!(ex instanceof BaseException)) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 验证签名
     *
     * @return
     */
    private boolean validateSignature() {
        return true;
    }
}
