package org.hibernate.demo.entity;

public class Dept implements java.io.Serializable{
	/*public Dpt(){}*/
	private Byte deptno;
	private String dname;
	private String loc;
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
	
}
