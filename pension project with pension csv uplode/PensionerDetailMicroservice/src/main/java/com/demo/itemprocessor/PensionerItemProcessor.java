package com.demo.itemprocessor;

import org.springframework.batch.item.ItemProcessor;


import com.demo.bean.Pensioner;


public class PensionerItemProcessor implements ItemProcessor<Pensioner, Pensioner>{

	@Override
	public Pensioner process(Pensioner pensioner) throws Exception {
		System.out.println("Processing: " + pensioner);

		final String initCapFirstName = pensioner.getName().substring(0, 1).toUpperCase() + pensioner.getName().substring(1);
		//final String initCapLastName = pensioner.get().substring(0, 1).toUpperCase() + pensioner.getLastName().substring(1);

		Pensioner transformedPensioner = new Pensioner();
		transformedPensioner.setAadhaarNumber(pensioner.getAadhaarNumber());
		transformedPensioner.setName(initCapFirstName);
		transformedPensioner.setDob(pensioner.getDob());
		transformedPensioner.setSalaryEarned(pensioner.getSalaryEarned());
		transformedPensioner.setAllowance(pensioner.getAllowance());
		transformedPensioner.setTypeSelfORFamilypension(pensioner.getTypeSelfORFamilypension());
		transformedPensioner.setBasicSalary(pensioner.getBasicSalary());
		transformedPensioner.setPensionerAccountNumber(pensioner.getPensionerAccountNumber());
		transformedPensioner.setPancard(pensioner.getPancard());
		transformedPensioner.setUserName(pensioner.getUserName());
		transformedPensioner.setPassword(pensioner.getPassword());
		
		return transformedPensioner;
	}
	
	

}
