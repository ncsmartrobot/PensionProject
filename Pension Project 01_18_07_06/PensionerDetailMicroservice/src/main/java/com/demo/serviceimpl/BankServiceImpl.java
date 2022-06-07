package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Bank;
import com.demo.repo.BankRepo;
import com.demo.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepo bankrepo;

	@Override
	public Bank bankdetails(Bank bank) {
		Bank ba = bankrepo.save(bank);
		return ba;
	}

	@Override
	public Bank getBankdetailsById(long aadhaarNumber) {
		
		return bankrepo.findById((long) aadhaarNumber).get();
	}


	
	
}
