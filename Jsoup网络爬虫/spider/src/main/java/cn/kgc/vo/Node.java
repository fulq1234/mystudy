package cn.kgc.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/***
 * 节点
 */
public class Node implements Serializable {
    private String name;
    private Integer type;
    private Integer level;
    private Integer hasNext;
    private String saveCloumn;
    private String classReg;
    private String startStr;
    private String endStr;
    private Integer resultType;
    private Integer position;
    //0：不是href 1:是href
    private Integer isHref;
    //正则表达式
    private String reg;
    //子节点
    private List<Node> children=new ArrayList<Node>();

    public Integer getIsHref() {
        return isHref;
    }

    public void setIsHref(Integer isHref) {
        this.isHref = isHref;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClassReg() {
        return classReg;
    }

    public void setClassReg(String classReg) {
        this.classReg = classReg;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public String getSaveCloumn() {
        return saveCloumn;
    }

    public void setSaveCloumn(String saveCloumn) {
        this.saveCloumn = saveCloumn;
    }

    public Integer getResultType() {
        return resultType;
    }

    public Integer getHasNext() {
        return hasNext;
    }

    public void setHasNext(Integer hasNext) {
        this.hasNext = hasNext;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public boolean next(){
        return this.getHasNext()==1?true:false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
