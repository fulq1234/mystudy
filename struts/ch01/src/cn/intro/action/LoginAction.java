package cn.intro.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String name = "";
	private String password = "";
		
	/**
	 * 验证消息
	 */
	@Override
	public void validate() {
		if(this.getName().length() == 0){
			addFieldError("name","用户名不能为空");
		}
		if(this.getPassword().length() ==0){
			addFieldError("pwd","用户密码不能为空");
		}
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//保存参数到session
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("name", name);//在前台显示${sessionScope.name}
		
		if(name.equals("jason") && password.equals("2010")){
			return "success";
		}else{
			return "fail";
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
