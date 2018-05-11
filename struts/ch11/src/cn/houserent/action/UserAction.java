package cn.houserent.action;

import cn.houserent.service.UserBiz;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("userAction")
public class UserAction extends ActionSupport {
    private String username;
    private String password;

    @Autowired
    @Qualifier("userBiz")
    private UserBiz userBiz;
    private int attemp;

    @Override
    public String execute() throws Exception {
        return "success";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    public int getAttemp() {
        return attemp;
    }

    public void setAttemp(int attemp) {
        this.attemp = attemp;
    }
}