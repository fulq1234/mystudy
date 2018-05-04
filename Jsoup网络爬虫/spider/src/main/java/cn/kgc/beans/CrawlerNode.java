package cn.kgc.beans;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
/***
*   
*/
public class CrawlerNode implements Serializable {
    //
    private Long id;
    //节点名称
    private String name;
    //节点类型;select;1:jsoup选择,2:字符串截取,3:正则表达式
    private Integer type;
    //父节点
    private Long parent;
    //节点级别
    private Integer level;
    //是否有子节点;radio;1:有,0:无
    private Integer hasNext;
    //保存对应字段
    private String saveCloumn;
    //jsuop选择表达式
    private String classReg;
    //开始字符串
    private String startStr;
    //结束字符串
    private String endStr;
    //结果类型
    private Integer resultType;
    //元素位置;select;0:全部元素,1:首元素,2:末元素,3:指定位置元素
    private Integer position;
    //是否是超链接;radio;0:否,1:是
    private Integer selfPosition;
    private Integer isHref;
    //正则表达式
    private String reg;
    //
    private Long ruleId;
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
    public void setName (String  name){
        this.name=name;
    }
    public  String getName(){
        return this.name;
    }
    public void setType (Integer  type){
        this.type=type;
    }
    public  Integer getType(){
        return this.type;
    }
    public void setParent (Long  parent){
        this.parent=parent;
    }
    public  Long getParent(){
        return this.parent;
    }
    public void setLevel (Integer  level){
        this.level=level;
    }
    public  Integer getLevel(){
        return this.level;
    }
    public void setHasNext (Integer  hasNext){
        this.hasNext=hasNext;
    }
    public  Integer getHasNext(){
        return this.hasNext;
    }
    public void setSaveCloumn (String  saveCloumn){
        this.saveCloumn=saveCloumn;
    }
    public  String getSaveCloumn(){
        return this.saveCloumn;
    }
    public void setClassReg (String  classReg){
        this.classReg=classReg;
    }
    public  String getClassReg(){
        return this.classReg;
    }
    public void setStartStr (String  startStr){
        this.startStr=startStr;
    }
    public  String getStartStr(){
        return this.startStr;
    }
    public void setEndStr (String  endStr){
        this.endStr=endStr;
    }
    public  String getEndStr(){
        return this.endStr;
    }
    public void setResultType (Integer  resultType){
        this.resultType=resultType;
    }
    public  Integer getResultType(){
        return this.resultType;
    }
    public void setPosition (Integer  position){
        this.position=position;
    }
    public  Integer getPosition(){
        return this.position;
    }
    public void setIsHref (Integer  isHref){
        this.isHref=isHref;
    }
    public  Integer getIsHref(){
        return this.isHref;
    }
    public void setReg (String  reg){
        this.reg=reg;
    }
    public  String getReg(){
        return this.reg;
    }
    public void setRuleId (Long  ruleId){
        this.ruleId=ruleId;
    }
    public  Long getRuleId(){
        return this.ruleId;
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

    public Integer getSelfPosition() {
        return selfPosition;
    }

    public void setSelfPosition(Integer selfPosition) {
        this.selfPosition = selfPosition;
    }
}
