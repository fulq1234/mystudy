package org.hibernate.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Ա�����Ӧ��ʵ����
 * @author Administrator
 *
 */
public class Emp implements Serializable {
	//Ա������
	private String empName;
	
	//Ա�����
	private Integer empNo;
	
	//Ա����ְʱ��
	private Date hireDate;
	
	//Ա��ְλ
	private String job;
	
	//Ա������
	private Double salary;
	
	public Emp() {
		// TODO Auto-generated constructor stub
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
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
