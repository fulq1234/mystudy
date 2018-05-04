package cn.kgc.vo;

import cn.kgc.beans.Recruit;

public class RecruitVo extends Recruit {

    private String taskName;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
