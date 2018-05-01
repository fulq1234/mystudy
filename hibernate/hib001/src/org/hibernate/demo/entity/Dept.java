package org.hibernate.demo.entity;

import java.util.HashSet;
import java.util.Set;

public class Dept implements java.io.Serializable{
	/*public Dpt(){}*/
	private Byte deptno;
	private String dname;
	private String loc;
	private Set<Emp2> emps = new HashSet<Emp2>();//初始化一下，防止使用过程中出现空错误
	
	public Dept(){
		
	}
	
	public Dept(String dname,String loc){
		this.dname = dname;
		this.loc = loc;
	}
	
	public Byte getDeptno() {
		return deptno;
	}
	public void setDeptno(Byte deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Emp2> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp2> emps) {
		this.emps = emps;
	}
	
}
