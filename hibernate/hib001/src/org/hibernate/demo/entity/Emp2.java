package org.hibernate.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Emp2 implements Serializable {
	
	private Short empno;//OID
	private String ename;
	private String job;
	private Short mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	//private Byte deptno;//fk
	private Dept dept;
	
	public Emp2(){
		
	}

	public Short getEmpno() {
		return empno;
	}

	public void setEmpno(Short empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Short getMgr() {
		return mgr;
	}

	public void setMgr(Short mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	/*public Byte getDeptno() {
		return deptno;
	}

	public void setDeptno(Byte deptno) {
		this.deptno = deptno;
	}
	*/
	
}
