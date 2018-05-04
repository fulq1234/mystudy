package cn.kgc.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintUtil {
	public String contentType;
	public HttpServletResponse response;
	public PrintUtil(HttpServletResponse response,String contentType){
		this.response=response;
		this.response.setContentType(contentType);
	}
	public PrintUtil(HttpServletResponse response){
		this.response=response;
	}
	public void print(Object msg){
        PrintWriter writer=null;
		try {
            if(null != response){
                writer=response.getWriter();
                writer.print(msg);
                writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            writer.close();
        }
    }
	public void write(Object obj){
		String json = JSONObject.toJSONString(obj);
		print(json);
	}

}
