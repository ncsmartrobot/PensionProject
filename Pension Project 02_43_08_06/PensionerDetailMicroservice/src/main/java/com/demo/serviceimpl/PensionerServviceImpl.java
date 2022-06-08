package com.demo.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.demo.bean.Pensioner;
import com.demo.csv.helper.CSVHelper;
import com.demo.repo.PensionserRepo;
import com.demo.service.PensionerService;

@Service
public class PensionerServviceImpl implements PensionerService{

	@Autowired 
	PensionserRepo pensionserRepo ;
	
	public void save(MultipartFile file) {
	    try {
	      List<Pensioner> tutorials = CSVHelper.csvToPensioners(file.getInputStream());
	      pensionserRepo.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<Pensioner> tutorials = pensionserRepo.findAll();

	    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
	    return in;
	  }

	  public List<Pensioner> getAllPensioners() {
	    return pensionserRepo.findAll();
	  }
	  
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
