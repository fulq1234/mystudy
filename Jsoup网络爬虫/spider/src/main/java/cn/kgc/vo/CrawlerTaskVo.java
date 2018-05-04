package cn.kgc.vo;

import cn.kgc.beans.CrawlerTask;

public class CrawlerTaskVo extends CrawlerTask {

    private String ruleName;

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
