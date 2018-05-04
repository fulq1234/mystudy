package cn.kgc.vo;

import cn.kgc.beans.Keyword;

public class KeywordVo extends Keyword{

    private String taskName;

    private String typeName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
