package com.demo.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name="Bank")
public class Bank {
		  
	@Id
	private long aadhaarNumber;
	private long bankAccountNumber;
	private String bankName;
	private String bankType;
	
	public Bank() {
		
	}
	
	public Bank(long aadhaarNumber, long bankAccountNumber, String bankName, String bankType) {
		this.aadhaarNumber = aadhaarNumber;
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
		return "Bank [aadhaarNumber=" + aadhaarNumber + ", bankAccountNumber=" + bankAccountNumber + ", bankName="
				+ bankName + ", bankType=" + bankType + "]";
	}
	
	
}
