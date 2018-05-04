package cn.kgc.vo;

import cn.kgc.beans.CrawlerNode;

import java.util.List;

public class CrawlerNodeVo extends CrawlerNode{

    private List<CrawlerNode> children;

    public List<CrawlerNode> getChildren() {
        return children;
    }

    public void setChildren(List<CrawlerNode> children) {
        this.children = children;
    }
}
