package cn.intro.action;

import java.util.Date;

public class UserAction{	
	private String id = "";
	private String message = "";
	private Date timeDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String add(){
		if(id.length() == 0){
			this.message = "操作失败";
			return "input";
		}else{
			this.message = "操作成功";
			return "success";
		}
	}
	public String dele(){
		return "input";
	}
	
	private String nextDispose = "";
	private boolean manager;
	//用户登录的逻辑
	public String doLogin(){
		//省略的登录的逻辑
		if(isManager()){
			nextDispose = "manager";
		}else{
			nextDispose = "common";
		}
		return "success";
	}
	public String getNextDispose() {
		return nextDispose;
	}
	public void setNextDispose(String nextDispose) {
		this.nextDispose = nextDispose;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
}
