package cn.kgc.common;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p/>
 * Created by zzshang on 2015/9/25.
 */
public class ReturnResult implements Serializable {

    private String status;

    private String message;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson(){
        Map map=new HashMap();
        map.put("status",this.status);
        map.put("message",this.message);
        map.put("data",this.data);
        return JSONObject.toJSONString(map);
    }
}
