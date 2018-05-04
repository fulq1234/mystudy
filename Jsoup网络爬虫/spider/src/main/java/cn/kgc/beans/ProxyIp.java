package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   代理IP
*/
public class ProxyIp implements Serializable {
        //
        private Integer id;
        //ip
        private String ip;
        //端口
        private Integer port;
        //网站
        private String website;
        //创建时间
        private Date creatdTime;
        //更新时间
        private Date updatedTime;
        //状态;checkbox;0:启用,1:禁用
        private String testCheck;
        //状态;select;0:选择0,1:选择1,2:选择2,3:选择3
        private String testSelect;
        //状态;radio;0:启用,1:禁用
        private Integer status;
        
        public ProxyIp() {
			super();
		}
		//get set 方法
            public void setId (Integer  id){
                this.id=id;
            }
            public  Integer getId(){
                return this.id;
            }
            public void setIp (String  ip){
                this.ip=ip;
            }
            public  String getIp(){
                return this.ip;
            }
            public void setPort (Integer  port){
                this.port=port;
            }
            public  Integer getPort(){
                return this.port;
            }
            public void setWebsite (String  website){
                this.website=website;
            }
            public  String getWebsite(){
                return this.website;
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
            public void setTestCheck (String  testCheck){
                this.testCheck=testCheck;
            }
            public  String getTestCheck(){
                return this.testCheck;
            }
            public void setTestSelect (String  testSelect){
                this.testSelect=testSelect;
            }
            public  String getTestSelect(){
                return this.testSelect;
            }
            public void setStatus (Integer  status){
                this.status=status;
            }
            public  Integer getStatus(){
                return this.status;
            }
}
