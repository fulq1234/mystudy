package org.hibernate.demo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ÏîÄ¿Àà
 * @author Administrator
 *
 */
public class Project implements java.io.Serializable{
	private Short proid;
	private String proname;
	private Set<Employee> employees = new HashSet<Employee>();
	public Project(){
		
	}
	
	

	public Project(Short proid, String proname) {
		super();
		this.proid = proid;
		this.proname = proname;
	}



	public Short getProid() {
		return proid;
	}

	public void setProid(Short proid) {
		this.proid = proid;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	
}
