package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class Keyword implements Serializable {
        //
        private Integer id;
        //
        private Integer rid;
        //
        private String name;
        //
        private Integer dataType;
        //
        private String uuid;
        //
        private Long taskId;
        //
        private String website;
        //
        private Integer status;
        //
        private String jobDescription;
        //创建时间
        private Date createdTime;
        //更新时间
        private Date updatedTime;
        //get set 方法
            public void setId (Integer  id){
                this.id=id;
            }
            public  Integer getId(){
                return this.id;
            }
            public void setRid (Integer  rid){
                this.rid=rid;
            }
            public  Integer getRid(){
                return this.rid;
            }
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setDataType (Integer  dataType){
                this.dataType=dataType;
            }
            public  Integer getDataType(){
                return this.dataType;
            }
            public void setUuid (String  uuid){
                this.uuid=uuid;
            }
            public  String getUuid(){
                return this.uuid;
            }
            public void setTaskId (Long  taskId){
                this.taskId=taskId;
            }
            public  Long getTaskId(){
                return this.taskId;
            }
            public void setWebsite (String  website){
                this.website=website;
            }
            public  String getWebsite(){
                return this.website;
            }
            public void setStatus (Integer  status){
                this.status=status;
            }
            public  Integer getStatus(){
                return this.status;
            }
            public void setJobDescription (String  jobDescription){
                this.jobDescription=jobDescription;
            }
            public  String getJobDescription(){
                return this.jobDescription;
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
