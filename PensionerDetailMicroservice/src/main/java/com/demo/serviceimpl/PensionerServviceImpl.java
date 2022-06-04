package com.demo.serviceimpl;

import java.util.Optional;

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
	public Pensioner pensionerdetails(Pensioner pensioner) {
		Pensioner pens = pensionserRepo.save(pensioner);
		return pens;
	}
	
	//get by aadharcardid
	public Optional<Pensioner> getPensionerDetailsByAaddhar(long aadhaarnumber) {
		Optional<Pensioner> pensionerget = pensionserRepo.findById(aadhaarnumber);
		return pensionerget;
	}

	

	/*
	 * @Override public Integer pensionCalculator(double basicsalary, double
	 * dearnessallowance, int workedageinorganization) {
	 * 
	 * return null; }
	 */

}
