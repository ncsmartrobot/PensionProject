package com.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name="pensioners")
public class Pensioner {
	
	@Id 
	@Column(name = "aadhaarNumber")
	private long aadhaarNumber;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "salaryEarned")
	private double salaryEarned;
	
	@Column(name = "allowance")
	private double allowance;
	
	@Column(name = "typeSelfORFamilypension")
	private String typeSelfORFamilypension;
	
	@Column(name = "basicSalary")
	private double basicSalary;
	
	@Column(name = "pensionerAccountNumber")
	private String pensionerAccountNumber;
	
	@Column(name = "pancard")
	private String pancard;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "bankAccountNumber")
	private long bankAccountNumber;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "bankType")
	private String bankType;
	
	public Pensioner() {

	}

	  
	public Pensioner(long aadhaarNumber, String name, String dob, double salaryEarned, double allowance,
			String typeSelfORFamilypension, double basicSalary, String pensionerAccountNumber, String pancard,
			String userName, String password, long bankAccountNumber, String bankName, String bankType) {
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
		this.bankAccountNumber = bankAccountNumber;
		this.bankName = bankName;
		this.bankType = bankType;
	}


	@XmlAttribute(name = "aadhaarNumber")
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


	public long getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankType() {
		return bankType;
	}


	public void setBankType(String bankType) {
		this.bankType = bankType;
	}


	@Override
	public String toString() {
		return "Pensioner [aadhaarNumber=" + aadhaarNumber + ", name=" + name + ", dob=" + dob + ", salaryEarned="
				+ salaryEarned + ", allowance=" + allowance + ", typeSelfORFamilypension=" + typeSelfORFamilypension
				+ ", basicSalary=" + basicSalary + ", pensionerAccountNumber=" + pensionerAccountNumber + ", pancard="
				+ pancard + ", userName=" + userName + ", password=" + password + ", bankAccountNumber="
				+ bankAccountNumber + ", bankName=" + bankName + ", bankType=" + bankType + "]";
	} 
		
}
