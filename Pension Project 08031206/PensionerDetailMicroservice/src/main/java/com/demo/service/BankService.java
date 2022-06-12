package com.demo.service;

import com.demo.bean.Bank;

public interface BankService {
	
	//cretaing a ban details
	Bank bankdetails(Bank bank);
	
	//getting a data of bank 
	Bank getBankdetailsById(long aadhaarNumber);

}
