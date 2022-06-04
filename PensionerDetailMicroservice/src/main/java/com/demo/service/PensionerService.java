package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.bean.Pensioner;

public interface PensionerService {
	
	//creating a pensioner details
	Pensioner pensionerdetails(Pensioner pensioner);
	
	//GET PensionerDetailss servcie
	Optional<Pensioner> getPensionerDetailsByAaddhar(long aadharcardid);

	/*
	Integer pensionCalculator(double basicsalary,double dearnessallowance,int workedageinorganization);
    List<Pensioner> getAllListOfPensioners();
	*/

}
