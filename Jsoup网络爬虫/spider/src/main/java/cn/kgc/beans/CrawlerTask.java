package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class CrawlerTask implements Serializable {
    //主键
    private Long id;
    //任务名称
    private String taskName;
    //规则Id
    private Long ruleId;
    //采集URL
    private String url;
    //开始页数
    private Integer startPage;
    //结束页数
    private Integer endPage;
    //线程数目
    private Integer threadCount;
    //爬取进度
    private String dataProgress;
    //抽取进度
    private String keywordProgress;
    //任务描述
    private String description;
    //创建时间
    private Date createdTime;
    //更新时间
    private Date updatedTime;
    //数据类型
    private Integer dataType;
    //get set 方法
    //是否需要分页
    //0:不需要 1:需要
    private Integer isNeedPage;
    //关键词
    private String keyword;
    //状态
    private Integer status;
    //爬取数据次数
    private Integer dataCounts;
    //关键词抽取次数
    private Integer keywordCounts;
    //爬虫数据总数
    private Integer totalCount;
    public void setId (Long  id){
        this.id=id;
    }
    public  Long getId(){
        return this.id;
    }
    public void setTaskName (String  taskName){
        this.taskName=taskName;
    }
    public  String getTaskName(){
        return this.taskName;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public void setUrl (String  url){
        this.url=url;
    }
    public  String getUrl(){
        return this.url;
    }
    public void setStartPage (Integer  startPage){
        this.startPage=startPage;
    }
    public  Integer getStartPage(){
        return this.startPage;
    }
    public void setEndPage (Integer  endPage){
        this.endPage=endPage;
    }
    public  Integer getEndPage(){
        return this.endPage;
    }
    public void setThreadCount (Integer  threadCount){
        this.threadCount=threadCount;
    }
    public  Integer getThreadCount(){
        return this.threadCount;
    }
    public void setDescription (String  description){
        this.description=description;
    }
    public  String getDescription(){
        return this.description;
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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getIsNeedPage() {
        return isNeedPage;
    }

    public void setIsNeedPage(Integer isNeedPage) {
        this.isNeedPage = isNeedPage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDataProgress() {
        return dataProgress;
    }

    public void setDataProgress(String dataProgress) {
        this.dataProgress = dataProgress;
    }

    public String getKeywordProgress() {
        return keywordProgress;
    }

    public void setKeywordProgress(String keywordProgress) {
        this.keywordProgress = keywordProgress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDataCounts() {
        return dataCounts;
    }

    public void setDataCounts(Integer dataCounts) {
        this.dataCounts = dataCounts;
    }

    public Integer getKeywordCounts() {
        return keywordCounts;
    }

    public void setKeywordCounts(Integer keywordCounts) {
        this.keywordCounts = keywordCounts;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
