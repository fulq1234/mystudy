package cn.kgc.common;
/**
 * <p>通用异常的处理</p>
 *
 * @author zzshang
 * @version v1.0
 * @since 2015/5/19
 */
public class BaseException extends Exception {
    //异常码
    private String exceptionCode;
    //异常信息
    private String message;


    public BaseException(String exceptionCode) {
        super();
        this.exceptionCode=exceptionCode;
    }

    public BaseException(String exceptionCode, String message) {
        super();
        this.exceptionCode=exceptionCode;
        this.message=message;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
