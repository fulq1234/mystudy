package org.hibernate.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Ա����ѯ��Ӧ��������װ��
 * ���磺��ְʱ����1981��4��1����1985��9��9��֮��
 * @author Administrator
 *
 */
public class EmpCondition implements Serializable {
	
	private Date from;
	private Date to;	
	//Ա��ְλ
	private String job;
	//Ա������
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
