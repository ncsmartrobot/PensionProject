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
	
	public double pensionamount;
	
	@PostMapping(value = "/pensionerdetails")
	Pensioner createpensioner(@RequestBody Pensioner pensioner) {
		Pensioner createpensioner = pensionerservice.pensionerdetails(pensioner);
		return createpensioner; 
	}
	
	@GetMapping(value = "/pensiondetailsbyaadhaar/{aadhaarnumber}")
	ResponseEntity<String> getPensioner(@PathVariable("aadhaarnumber") long aadhaarnumber) {
		
		Pensioner pensi = pensionerservice.getPensionerById(aadhaarnumber);
		
		try {
			if(pensi.getTypeselforfamilypension().equals("self_pension")) {
				pensionamount = (0.8 * pensi.getSalaryearned()) + pensi.getDa_allowance();
				return ResponseEntity.ok("Pension Amount of Self pension: "+ pensionamount);
			}
			else if(pensi.getTypeselforfamilypension().equals("family_pension")) {
				pensionamount = (0.5 * pensi.getSalaryearned()) + pensi.getDa_allowance();
				return ResponseEntity.ok("Pension Amount of Family pension: "+pensionamount);
			}
			return ResponseEntity.ok("Enter correct Pension Type ");
		} catch (Exception e) {
			return ResponseEntity.ok("Data does not exits ");
		}
	}
	
}
