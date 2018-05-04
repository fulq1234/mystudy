package cn.kgc.util;

import cn.kgc.common.Constants;
import cn.kgc.common.ReturnResult;

/**
 * <p></p>
 * <p/>
 * Created by zzshang on 2015/9/25.
 */
public class ReturnResultUtil {
    /**
     * 返回失败的信息
     * @param message
     * @return
     */
    public static ReturnResult returnFail(String message){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setStatus(Constants.ReturnResult.STATUS.FAIL);
        returnResult.setMessage(message);
        return returnResult;
    }
    /**
     * 返回成功的信息
     * @param message
     * @return
     */
    public static ReturnResult returnSuccess(String message){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setStatus(Constants.ReturnResult.STATUS.SUCCESS);
        returnResult.setMessage(message);
        return returnResult;
    }
    /**
     * 返回失败信息
     * @return
     */
    public static ReturnResult returnFail(){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setStatus(Constants.ReturnResult.STATUS.FAIL);
        returnResult.setMessage("操作失败");
        return returnResult;
    }
    /**
     * 返回成功信息
     * @return
     */
    public static ReturnResult returnSuccess(){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setStatus(Constants.ReturnResult.STATUS.SUCCESS);
        returnResult.setMessage("操作成功");
        return returnResult;
    }
}
