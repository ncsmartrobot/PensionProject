package com.demo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pensioners")
public class Pensioner {
	
	@Id private long aadhaarnumber;
	private String name;
	private String dob;
	private double salaryearned;
	private double da_allowance;
	private String typeselforfamilypension;
	private double basicsalary;
	private static final int fixedageByGovt=70;
	private String pensioneraccountnumber;
	private String pancard;
	private String username;
	private String password;
	
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public double getSalaryearned() {
		return salaryearned;
	}
	public void setSalaryearned(double salaryearned) {
		this.salaryearned = salaryearned;
	}
	public double getDa_allowance() {
		return da_allowance;
	}
	public void setDa_allowance(double da_allowance) {
		this.da_allowance = da_allowance;
	}
	public String getTypeselforfamilypension() {
		return typeselforfamilypension;
	}
	public void setTypeselforfamilypension(String typeselforfamilypension) {
		this.typeselforfamilypension = typeselforfamilypension;
	}

	public long getAadhaarnumber() {
		return aadhaarnumber;
	}
	public void setAadhaarnumber(long aadhaarnumber) {
		this.aadhaarnumber = aadhaarnumber;
	}
	
	public double getBasicsalary() {
		return basicsalary;
	}
	public void setBasicsalary(double basicsalary) {
		this.basicsalary = basicsalary;
	}
	public int getFixedageByGovt() {
		return fixedageByGovt;
	}

	public String getPensioneraccountnumber() {
		return pensioneraccountnumber;
	}
	public void setPensioneraccountnumber(String pensioneraccountnumber) {
		this.pensioneraccountnumber = pensioneraccountnumber;
	}
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
