package com.demo.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.bean.Bank;
import com.demo.csv.helper.CSVHelperBank;
import com.demo.repo.BankRepo;
import com.demo.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	BankRepo bankrepo;

	public void save(MultipartFile file) {
	    try {
	      List<Bank> banks = CSVHelperBank.csvToBanks(file.getInputStream());
	      bankrepo.saveAll(banks);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }
	public ByteArrayInputStream load() {
	    List<Bank> banks = bankrepo.findAll();

	    ByteArrayInputStream in = CSVHelperBank.csvToBanks(banks);
	    return in;
	  }

	  public List<Bank> getAllBanks() {
	    return bankrepo.findAll();
	  }
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
