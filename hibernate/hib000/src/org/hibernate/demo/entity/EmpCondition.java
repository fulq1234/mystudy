package org.hibernate.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工查询对应的条件封装类
 * 比如：入职时间在1981年4月1日至1985年9月9日之间
 * @author Administrator
 *
 */
public class EmpCondition implements Serializable {
	
	private Date from;
	private Date to;	
	//员工职位
	private String job;
	//员工工资
	private Double salary;
	
	public EmpCondition(){}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
}
