package com.demo.itemprocessor;

import org.springframework.batch.item.ItemProcessor;

import com.demo.bean.Bank;


public class BankItemProcessor implements ItemProcessor<Bank, Bank> {

	@Override
	public Bank process(Bank bank) throws Exception {
		System.out.println("Processing: " + bank);

		final String initCapFirstName = bank.getBankName().substring(0, 1).toUpperCase() + bank.getBankName().substring(1);
		
		Bank transformedBank = new Bank();
		transformedBank.setAadhaarNumber(bank.getAadhaarNumber());
		transformedBank.setBankAccountNumber(bank.getBankAccountNumber());
		transformedBank.setBankName(initCapFirstName);
		transformedBank.setBankType(bank.getBankType());
		
		return transformedBank;
	}
	

}
