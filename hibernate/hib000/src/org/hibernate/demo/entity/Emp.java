package org.hibernate.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工表对应的实体类
 * @author Administrator
 *
 */
public class Emp implements Serializable {
	//员工姓名
	private String empName;
	
	//员工编号
	private Integer empNo;
	
	//员工入职时间
	private Date hireDate;
	
	//员工职位
	private String job;
	
	//员工工资
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
