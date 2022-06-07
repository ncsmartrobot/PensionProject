package com.demo.service;

import com.demo.bean.Pensioner;

public interface PensionerService {
	
	//creating a pensioner details
	Pensioner pensionerdetails(Pensioner pensioner);
	
	//getting a data by aadhaar number
	Pensioner getPensionerById(long aadhaarnumber);
	
	//process of pension amount
	
}
