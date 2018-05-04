package cn.kgc.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupUtil {
    public static void main(String[] args) {
      //爬虫地址
      String url="http://jobs.zhaopin.com/209732211321304.htm";
      //创建连接
      Connection connection=Jsoup.connect(url);
      //请求URL获取文档对象
      Document document=null;
      try {
          document=connection.get();
          System.out.println(document.html());
          String selector="a";
          Elements elements=document.select(selector);
          for (Element element:elements){
              System.out.println(element.html());
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
