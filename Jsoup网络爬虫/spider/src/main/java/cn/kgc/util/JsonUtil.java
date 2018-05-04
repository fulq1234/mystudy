package cn.kgc.util;
import cn.kgc.common.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zezhong.shang on 17-9-19.
 */
public class JsonUtil {

    static Logger logger = Logger.getLogger(JsonUtil.class);

    public static String getUserAgent() {
        return Constants.UserAgent.Chrome;
    }

    public static void setConnection(Connection connection) {
        //设置connection参数
        connection.timeout(2000);
        connection.userAgent(getUserAgent());
    }

    public static void addHeader(Connection connection, String headers) {
        String headerArray[] = headers.split("\n");
        for (String header : headerArray) {
            String key = header.split(":")[0];
            String value = header.split(":")[1];
            logger.info("设置header>>>>" + key + ":" + value);
            connection.header(key, value);
        }
    }

    public static JSONObject getJsonByProxy(String url) {
        Document doc = null;
        JSONObject json = null;
        try {
            Connection connection = Jsoup
                    .connect(url)
                    .timeout(10000).ignoreContentType(true);
            String headers = "Accept:*/*\n" +
                    "Accept-Encoding:gzip, deflate, sdch\n" +
                    "Accept-Language:zh-CN,zh;q=0.8\n" +
                    "Cache-Control:no-cache\n" +
                    "Host:zhaopin.baidu.com\n" +
                    "Pragma:no-cache\n" +
                    "Proxy-Connection:keep-alive\n" +
                    "Referer:http://zhaopin.baidu.com/quanzhi?detailidx=0&city=%E5%8C%97%E4%BA%AC&id=http%3A%2F%2Fzhaopin.baidu.com%2F107232&query=java\n" +
                    "User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36\n" +
                    "X-Requested-With:XMLHttpRequest";
            addHeader(connection, headers);
            setCookie("http://zhaopin.baidu.com/quanzhi?detailidx=0&city=%E5%8C%97%E4%BA%AC&id=http%3A%2F%2Fzhaopin.baidu.com%2F107232&query=java", connection);
            connection.data("query", "java");
            connection.data("sort_type", "1");
            connection.data("city", "北京");
            connection.data("detailmode", "close");
            connection.data("rn", "20");
            connection.data("pn", "580");
            doc = connection.post();
            Element body = doc.body();
            ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
            JSONObject jsonObject= JSON.parseObject(body.text());
            JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONObject("main").getJSONObject("data").getJSONArray("disp_data");
            for (int i=0;i<jsonArray.size();i++){
                JSONObject temp2=jsonArray.getJSONObject(i);
                System.out.println(temp2.get("city"));
                System.out.println(temp2.get("commonname"));
                System.out.println(temp2.get("education"));
                System.out.println(temp2.get("employertype"));
                System.out.println(temp2.get("joblink"));
                System.out.println(temp2.get("city"));
                System.out.println(temp2.get("city"));
                System.out.println(temp2.get("city"));
                System.out.println(temp2.get("city"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void setCookie(String url, Connection conn) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Connection connection = Jsoup
                .connect(url)
                .timeout(10000).ignoreContentType(true);

        String headers = "Accept:*/*\n" +
                "Accept-Encoding:gzip, deflate, sdch\n" +
                "Accept-Language:zh-CN,zh;q=0.8\n" +
                "Cache-Control:no-cache\n" +
                "Host:zhaopin.baidu.com\n" +
                "Pragma:no-cache\n" +
                "Proxy-Connection:keep-alive\n" +
                "Referer:http://zhaopin.baidu.com/quanzhi?detailidx=0&city=%E5%8C%97%E4%BA%AC&id=http%3A%2F%2Fzhaopin.baidu.com%2F107232&query=java\n" +
                "User-Agent:Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36\n" +
                "X-Requested-With:XMLHttpRequest";
        addHeader(connection, headers);
        Connection.Response response = null;
        try {
            response = connection.execute();
            cookieMap = response.cookies();
            for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
                conn.cookie(entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.cookies());
    }

    public static void main(String[] args) throws IOException {
        getJsonByProxy("http://zhaopin.baidu.com/api/quanzhiasync?query=java&sort_type=1&city=%E5%8C%97%E4%BA%AC&detailmode=close&rn=20&pn=580");
    }

}
