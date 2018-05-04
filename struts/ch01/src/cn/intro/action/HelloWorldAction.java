package cn.intro.action;

import com.opensymphony.xwork2.Action;

/**
 * 
 * @author Administrator
 *
 */
public class HelloWorldAction implements Action{
	private String name = "";//用于接受表单的姓名，用户输入的姓名
	private String message = "";//输出的message,向用户显示的信息
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.setMessage("Hello!" + this.getName());
		return "success";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
