package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class CrawlerDataType implements Serializable {
        //主键自增
        private Long id;
        //类型名称
        private String typeName;
        //停止词
        private String stopWord;
        //拓展词
        private String extWord;
        //创建时间
        private Date createdTime;
        //更新时间
        private Date updatedTime;
        //get set 方法
            public void setId (Long  id){
                this.id=id;
            }
            public  Long getId(){
                return this.id;
            }
            public void setTypeName (String  typeName){
                this.typeName=typeName;
            }
            public  String getTypeName(){
                return this.typeName;
            }
            public void setStopWord (String  stopWord){
                this.stopWord=stopWord;
            }
            public  String getStopWord(){
                return this.stopWord;
            }
            public void setExtWord (String  extWord){
                this.extWord=extWord;
            }
            public  String getExtWord(){
                return this.extWord;
            }
            public void setCreatedTime (Date  createdTime){
                this.createdTime=createdTime;
            }
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            public  Date getCreatedTime(){
                return this.createdTime;
            }
            public void setUpdatedTime (Date  updatedTime){
                this.updatedTime=updatedTime;
            }
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            public  Date getUpdatedTime(){
                return this.updatedTime;
            }
}
