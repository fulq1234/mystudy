package cn.kgc.vo;
import java.io.Serializable;

//规则配置
public class CrawlerRule implements Serializable{
    //是否需要代理
    private Integer isNeedProxy;
    //规则名称
    private String ruleName;
    //规则描述
    private String description;
    //开始URL
    private String url;
    //规则Node
    private Node root;
    //是否需要分页
    private Integer isNeedPage;
    //开发分页
    private Integer startPageNo;
    //结束分页
    private Integer endPageNo;
    //请求方式
    private Integer reqMethod;

    public Integer getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(Integer reqMethod) {
        this.reqMethod = reqMethod;
    }

    public Integer getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(Integer startPageNo) {
        this.startPageNo = startPageNo;
    }

    public Integer getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(Integer endPageNo) {
        this.endPageNo = endPageNo;
    }

    public Integer getIsNeedProxy() {
        return isNeedProxy;
    }

    public void setIsNeedProxy(Integer isNeedProxy) {
        this.isNeedProxy = isNeedProxy;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Integer getIsNeedPage() {
        return isNeedPage;
    }

    public void setIsNeedPage(Integer isNeedPage) {
        this.isNeedPage = isNeedPage;
    }
}
