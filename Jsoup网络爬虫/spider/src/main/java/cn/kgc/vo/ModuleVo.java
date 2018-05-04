package cn.kgc.vo;

import cn.kgc.beans.Module;

import java.util.List;


public class ModuleVo extends Module{

    private String parentName;

    private List<Module> children;

    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
