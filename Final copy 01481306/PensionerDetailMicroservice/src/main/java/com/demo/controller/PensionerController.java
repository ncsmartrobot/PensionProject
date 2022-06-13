package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.demo.bean.Pensioner;
import com.demo.service.PensionerService;
import com.demo.serviceimpl.PensionerServviceImpl;

@CrossOrigin("http://localhost:8081")
@Controller
@RestController
@EnableWebMvc
public class PensionerController {

	
	@Autowired(required = true)
	PensionerService pensionerservice;
	
	@Autowired(required = true)
	PensionerServviceImpl pensionerServviceImpl;
	
	
	public double pensionAmount;
	public int publicBankCharge = 500;
	public int privateBankCharge = 550;
	
		  
	@PostMapping(value = "/pensionerdetails")
	Pensioner createpensioner(@RequestBody Pensioner pensioner) {
		Pensioner createpensioner = pensionerservice.pensionerdetails(pensioner);
		return createpensioner; 
	}
	
	@GetMapping(value = "/PensionerDetailByAadhaar/{aadhaarNumber}")
    public ResponseEntity<String> getPensionerby(@PathVariable("aadhaarNumber") long aadhaarNumber) {
		
		
		Pensioner pens =pensionerservice.getPensionerById(aadhaarNumber);
		return ResponseEntity.ok("<bgcolor=red>"+"Name: "+pens.getName()+"<br/>"+
				"Date of birth: "+pens.getDob()+"<br/>"+
				"PAN: "+pens.getPancard()+"<br/>"+
				"SalaryEarned: "+pens.getSalaryEarned()+"<br/>"+
				"Allowances: "+pens.getAllowance()+"<br/>"+
				"Pension Type: "+pens.getTypeSelfORFamilypension()+"<br/>"+
				"Bank name: "+pens.getBankName()+"<br/>"+
				"Account number: "+pens.getBankAccountNumber()+"<br/>"+
				"Bank Type: "+pens.getBankType()+"<br/>");
		
	} 
	
	@GetMapping(value = "/processpension/{aadhaarNumber}")
	public ResponseEntity<String> getPensioner(@PathVariable("aadhaarNumber") long aadhaarNumber) {
		
		Pensioner pensi = pensionerservice.getPensionerById(aadhaarNumber);
		
		try {
			
			if(pensi.getBankType().equals("Public bank")) {
				if(pensi.getTypeSelfORFamilypension().equals("self pension")) {
					pensionAmount = (0.8 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				else if(pensi.getTypeSelfORFamilypension().equals("family pension")) {
					pensionAmount = (0.5 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				return ResponseEntity.ok("Pension Amount of "+pensi.getTypeSelfORFamilypension()+": "+ pensionAmount+"Rs"+"<br/>"+"Public bank charges: INR "+ publicBankCharge);
			}
			else if(pensi.getBankType().equals("Private bank")) {
				if(pensi.getTypeSelfORFamilypension().equals("self pension")) {
					pensionAmount = (0.8 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				else if(pensi.getTypeSelfORFamilypension().equals("family pension")) {
					pensionAmount = (0.5 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				return ResponseEntity.ok("Pension Amount of "+pensi.getTypeSelfORFamilypension()+": "+ pensionAmount+"Rs"+"<br/>"+"Private bank charges: INR "+ privateBankCharge);
			}
			
			return ResponseEntity.ok("Enter correct Pension Type ");
		} catch (Exception e) {
			return ResponseEntity.ok("Data does not exits ");
		}
	}
	
}
