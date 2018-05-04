package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class SystemDic implements Serializable {
        //主键
        private Long id;
        //父节点
        private String parent;
        //名称
        private String name;
        //主键
        private String dicKey;
        //数据字典值
        private String dicValue;
        //
        private Date createdTime;
        //
        private Date updatedTime;
        //状态;radio;0:启用,1:禁用
        private Integer status;
        //get set 方法
            public void setId (Long  id){
                this.id=id;
            }
            public  Long getId(){
                return this.id;
            }
            public void setParent (String  parent){
                this.parent=parent;
            }
            public  String getParent(){
                return this.parent;
            }
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setDicKey (String  dicKey){
                this.dicKey=dicKey;
            }
            public  String getDicKey(){
                return this.dicKey;
            }
            public void setDicValue (String  dicValue){
                this.dicValue=dicValue;
            }
            public  String getDicValue(){
                return this.dicValue;
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
            public void setStatus (Integer  status){
                this.status=status;
            }
            public  Integer getStatus(){
                return this.status;
            }
}
