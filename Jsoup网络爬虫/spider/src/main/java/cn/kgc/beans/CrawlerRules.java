package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class CrawlerRules implements Serializable {
        //主键
        private Long id;
        //规则名称
        private String ruleName;
        //请求方式;select;1:GET,2:POST
        private Integer isNeedProxy;
        //规则描述
        private String description;
        //状态;radio;0:启用,1:禁用
        private Integer status;
        //创建时间
        private Date creatdTime;
        //更新时间
        private Date updatedTime;

        //get set 方法
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public  Date getCreatdTime(){
            return this.creatdTime;
        }

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public  Date getUpdatedTime(){
            return this.updatedTime;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRuleName() {
            return ruleName;
        }

        public void setRuleName(String ruleName) {
            this.ruleName = ruleName;
        }

        public Integer getIsNeedProxy() {
            return isNeedProxy;
        }

        public void setIsNeedProxy(Integer isNeedProxy) {
            this.isNeedProxy = isNeedProxy;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setCreatdTime(Date creatdTime) {
            this.creatdTime = creatdTime;
        }

        public void setUpdatedTime(Date updatedTime) {
            this.updatedTime = updatedTime;
        }
}
