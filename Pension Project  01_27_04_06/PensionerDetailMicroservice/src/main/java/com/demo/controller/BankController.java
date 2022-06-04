package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Bank;
import com.demo.service.BankService;

@RestController
public class BankController {
	
	@Autowired(required = true)
	BankService bankservice;
	
	@PostMapping(value ="/bankdetails")
	Bank createbankdetails(@RequestBody Bank bank) {
	Bank createbankdetails = bankservice.bankdetails(bank);
	return createbankdetails;
	}
}
