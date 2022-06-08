package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Pensioner;
import com.demo.repo.PensionserRepo;
import com.demo.service.PensionerService;

@Service
public class PensionerServviceImpl implements PensionerService{

	@Autowired 
	PensionserRepo pensionserRepo ;
	
	@Override
	//data into database
	public Pensioner pensionerdetails(Pensioner pensioner) {
		Pensioner pens = pensionserRepo.save(pensioner);
		return pens;
	}
	 
	//getting a specific details by using the method findById() of CrudRepository  
	public Pensioner getPensionerById(long aadhaarNumber) {
		return pensionserRepo.findById((long) aadhaarNumber).get();
	}

	//process of details
	
		
}
