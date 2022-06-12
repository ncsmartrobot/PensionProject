package com.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.demo.bean.Bank;
import com.demo.service.BankService;
import com.demo.serviceimpl.BankServiceImpl;

@CrossOrigin("http://localhost:8082")
@Controller
@RestController
public class BankController {
	
	@Autowired(required = true)
	BankService bankservice;
	
	@Autowired(required = true)
	BankServiceImpl bankServiceImpl;
	  
	  
	@PostMapping(value ="/bankdetails")
	Bank createbankdetails(@RequestBody Bank bank) {
		Bank createbankdetails = bankservice.bankdetails(bank);
		return createbankdetails;
	}
	
}
