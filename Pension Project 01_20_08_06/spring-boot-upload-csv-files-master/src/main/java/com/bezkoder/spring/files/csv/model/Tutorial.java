package com.bezkoder.spring.files.csv.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pensioners")
public class Tutorial {

  @Id
  private long aadhaarNumber;
  private String name;
  private String dob;
  private double salaryEarned;
  private double allowance;
  private String typeSelfORFamilypension;
  private double basicSalary;
	//private static final int fixedAgeByGovt=70;
  private String pensionerAccountNumber;
  private String pancard;
  private String userName;
  private String password;
  
  public Tutorial() {

  }

  public Tutorial(long aadhaarNumber, String name, String dob, double salaryEarned, double allowance,
		String typeSelfORFamilypension, double basicSalary, String pensionerAccountNumber, String pancard,
		String userName, String password) {
	//super();
	this.aadhaarNumber = aadhaarNumber;
	this.name = name;
	this.dob = dob;
	this.salaryEarned = salaryEarned;
	this.allowance = allowance;
	this.typeSelfORFamilypension = typeSelfORFamilypension;
	this.basicSalary = basicSalary;
	this.pensionerAccountNumber = pensionerAccountNumber;
	this.pancard = pancard;
	this.userName = userName;
	this.password = password;
}
 
 public long getAadhaarNumber() {
	return aadhaarNumber;
}

public void setAadhaarNumber(long aadhaarNumber) {
	this.aadhaarNumber = aadhaarNumber;
}

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

public double getSalaryEarned() {
	return salaryEarned;
}

public void setSalaryEarned(double salaryEarned) {
	this.salaryEarned = salaryEarned;
}

public double getAllowance() {
	return allowance;
}

public void setAllowance(double allowance) {
	this.allowance = allowance;
}

public String getTypeSelfORFamilypension() {
	return typeSelfORFamilypension;
}

public void setTypeSelfORFamilypension(String typeSelfORFamilypension) {
	this.typeSelfORFamilypension = typeSelfORFamilypension;
}

public double getBasicSalary() {
	return basicSalary;
}

public void setBasicSalary(double basicSalary) {
	this.basicSalary = basicSalary;
}

public String getPensionerAccountNumber() {
	return pensionerAccountNumber;
}

public void setPensionerAccountNumber(String pensionerAccountNumber) {
	this.pensionerAccountNumber = pensionerAccountNumber;
}

public String getPancard() {
	return pancard;
}

public void setPancard(String pancard) {
	this.pancard = pancard;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "Tutorial [aadhaarNumber=" + aadhaarNumber + ", name=" + name + ", dob=" + dob + ", salaryEarned="
			+ salaryEarned + ", allowance=" + allowance + ", typeSelfORFamilypension=" + typeSelfORFamilypension
			+ ", basicSalary=" + basicSalary + ", pensionerAccountNumber=" + pensionerAccountNumber + ", pancard="
			+ pancard + ", userName=" + userName + ", password=" + password + "]";
}

}
