package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Pensioner;
import com.demo.service.PensionerService;

@RestController
public class PensionerController {

	@Autowired(required = true)
	PensionerService pensionerservice;
	
	@PostMapping(value = "/pensionerdetails")
	Pensioner createpensioner(@RequestBody Pensioner pensioner) {
		Pensioner createpensioner = pensionerservice.pensionerdetails(pensioner);
		return createpensioner; 
	}
	
	Pensioner tp = new Pensioner();
	String typepension = tp.getTypeselforfamilypension();
	double salaryearned = tp.getSalaryearned();
	double da_allowance = tp.getDa_allowance();
	double pensionamount = tp.getPensionamount();
	
	@GetMapping(value = "/pensiondetailsbyaadhaar/{aadhaarnumber}")
	private Pensioner getPensioner(@PathVariable("aadhaarnumber") long aadhaarnumber) {
		return pensionerservice.getPensionerById(aadhaarnumber);
	}
	
	@GetMapping(value = "/procespension")
	ResponseEntity<String> gettypepresenior(@PathVariable String typeselforfamilypension){
		try {
			if(typepension.equals("self pension")) {
				pensionamount = (0.08 * salaryearned) + da_allowance;
				return ResponseEntity.ok("Pension Amount of Self pension: "+pensionamount);
			}
			else if(typepension.equals("family pension")) {
				pensionamount = (0.05 * salaryearned) + da_allowance;
				return ResponseEntity.ok("Pension Amount of Family pension: "+pensionamount);
			}
			return ResponseEntity.ok("Enter correct Pension Type ");
		} catch (Exception e) {
			return ResponseEntity.ok("Data does not exits ");
		}
	}
	
}
