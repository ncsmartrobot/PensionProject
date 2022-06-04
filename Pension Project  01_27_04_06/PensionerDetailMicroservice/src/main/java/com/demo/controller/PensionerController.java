package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping(value = "/pensionerdetailsdyaaddhar")
	Optional<Pensioner> getPensionerDetailsByAaddhar(long aadharcardid){
		return pensionerservice.getPensionerDetailsByAaddhar(aadharcardid);
		
	}
}
