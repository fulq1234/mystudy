package cn.kgc.vo;

/**
 * Created by zezhong.shang on 17-9-21.
 */
public class ThreadMonitor {

    public static boolean flag=false;

    private WebNode webNode;

    private Integer pageNo;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public WebNode getWebNode() {
        return webNode;
    }

    public void setWebNode(WebNode webNode) {
        this.webNode = webNode;
    }
}
