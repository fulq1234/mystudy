package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   模块
*/
public class Module implements Serializable {
        //主键
        private Long id;
        //名称
        private String name;
        //访问地址
        private String url;
        //父模块
        private Long parent;
        //创建时间
        private Date creatdTime;
        //更新时间
        private Date updatedTime;
        //状态;radio;0:未删除,1:已删除
        private Integer status;
        //get set 方法
            public void setId (Long  id){
                this.id=id;
            }
            public  Long getId(){
                return this.id;
            }
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setUrl (String  url){
                this.url=url;
            }
            public  String getUrl(){
                return this.url;
            }
            public void setParent (Long  parent){
                this.parent=parent;
            }
            public  Long getParent(){
                return this.parent;
            }
            public void setCreatdTime (Date  creatdTime){
                this.creatdTime=creatdTime;
            }
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            public  Date getCreatdTime(){
                return this.creatdTime;
            }
            public void setUpdatedTime (Date  updatedTime){
                this.updatedTime=updatedTime;
            }
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            public  Date getUpdatedTime(){
                return this.updatedTime;
            }
            public void setStatus (Integer  status){
                this.status=status;
            }
            public  Integer getStatus(){
                return this.status;
            }
}
