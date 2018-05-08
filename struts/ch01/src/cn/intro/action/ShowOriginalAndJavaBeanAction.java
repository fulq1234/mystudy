package cn.intro.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.intro.entity.Address;
import cn.intro.entity.User;

public class ShowOriginalAndJavaBeanAction extends ActionSupport {
	
	private User user;
	private String message;
	@Override
	public String execute() throws Exception {
		setUser(new User());
		getUser().setName("jbit");
		getUser().setAge(23);
		Address address=new Address();
		address.setCountry("中国");
		address.setCity("北京");
		address.setStreet("长安街");
		getUser().setAddress(address);
		setMessage("展示原始类型和JavaBean");
		return SUCCESS;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
