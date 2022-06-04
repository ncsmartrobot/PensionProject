package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping(value = "/pensiondetails/{aadhaarnumber}")
	private Pensioner getPensioner(@PathVariable("aadhaarnumber") long aadhaarnumber) {
		return pensionerservice.getPensionerById(aadhaarnumber);
	}
}
