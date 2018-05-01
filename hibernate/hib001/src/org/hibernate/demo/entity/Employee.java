package org.hibernate.demo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * πÕ‘±¿‡
 * @author Administrator
 *
 */
public class Employee {
	private Short empid;
	private String empname;
	private Set<Project> projects = new HashSet<Project>();
	
	public Employee(){
		
	}

	public Employee(Short empid, String empname) {
		super();
		this.empid = empid;
		this.empname = empname;
	}

	public Short getEmpid() {
		return empid;
	}

	public void setEmpid(Short empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
}
