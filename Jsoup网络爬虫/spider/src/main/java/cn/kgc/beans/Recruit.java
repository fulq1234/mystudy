package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class Recruit implements Serializable {
        //
        private Integer id;
        //招聘标题
        private String title;
        //来源城市
        private String city;
        //公司名称
        private String companyName;
        //公司类型
        private String companyType;
        //公司网址
        private String companyUrl;
        //福利
        private String welfare;
        //公司地址
        private String companyAddress;
        //职位月薪
        private String monthlySalary;
        //最低月薪
        private String minSalary;
        //最高月薪
        private String maxSalary;
        //发布日期
        private String releaseDate;
        //工作性质
        private String workNature;
        //工作经验
        private String experience;
        //学历
        private String eucation;
        //招聘人数
        private String rNum;
        //职位分类
        private String positionCategory;
        //工作描述
        private String jobDescription;
        //来源网站
        private String listUrl;
        //来源URL
        private String detailUrl;
        //随机字符串
        private String uuid;
        //职位分类;select;pd:前端,bd:大数据,java:java
        private Integer dataType;
        //公司网址
        private String website;
        //状态;radio;0:未删除,1:已删除
        private Integer status;
        //创建时间
        private Date createdTime;
        //更新时间
        private Date updatedTime;
        //公司规模
        private String companySize;
        //任务Id
        private Integer taskId;
        //get set 方法
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public  Date getCreatedTime(){
            return this.createdTime;
        }
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        public  Date getUpdatedTime(){
            return this.updatedTime;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyType() {
            return companyType;
        }

        public void setCompanyType(String companyType) {
            this.companyType = companyType;
        }

        public String getCompanyUrl() {
            return companyUrl;
        }

        public void setCompanyUrl(String companyUrl) {
            this.companyUrl = companyUrl;
        }

        public String getWelfare() {
            return welfare;
        }

        public void setWelfare(String welfare) {
            this.welfare = welfare;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getMonthlySalary() {
            return monthlySalary;
        }

        public void setMonthlySalary(String monthlySalary) {
            this.monthlySalary = monthlySalary;
        }

        public String getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(String minSalary) {
            this.minSalary = minSalary;
        }

        public String getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(String maxSalary) {
            this.maxSalary = maxSalary;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getWorkNature() {
            return workNature;
        }

        public void setWorkNature(String workNature) {
            this.workNature = workNature;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getEucation() {
            return eucation;
        }

        public void setEucation(String eucation) {
            this.eucation = eucation;
        }

        public String getrNum() {
            return rNum;
        }

        public void setrNum(String rNum) {
            this.rNum = rNum;
        }

        public String getRNum() {
            return rNum;
        }

        public void setRNum(String rNum) {
            this.rNum = rNum;
        }

    public String getPositionCategory() {
            return positionCategory;
        }

        public void setPositionCategory(String positionCategory) {
            this.positionCategory = positionCategory;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
        }

        public String getListUrl() {
            return listUrl;
        }

        public void setListUrl(String listUrl) {
            this.listUrl = listUrl;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Integer getDataType() {
            return dataType;
        }

        public void setDataType(Integer dataType) {
            this.dataType = dataType;
        }

    public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setCreatedTime(Date createdTime) {
            this.createdTime = createdTime;
        }

        public void setUpdatedTime(Date updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getCompanySize() {
            return companySize;
        }

        public void setCompanySize(String companySize) {
            this.companySize = companySize;
        }

        public Integer getTaskId() {
            return taskId;
        }

        public void setTaskId(Integer taskId) {
            this.taskId = taskId;
        }
}
