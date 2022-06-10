package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.bean.Bank;
import com.demo.csv.helper.CSVHelperBank;
import com.demo.csv.message.ResponseMessage;
import com.demo.service.BankService;
import com.demo.serviceimpl.BankServiceImpl;

@CrossOrigin("http://localhost:8082")
@Controller
@RestController
public class BankController {
	
	@Autowired(required = true)
	BankService bankservice;
	
	@Autowired(required = true)
	BankServiceImpl bankServiceImpl;
	
	@PostMapping("/bank/csv/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (CSVHelperBank.hasCSVFormat(file)) {
	      try {
	    	  bankServiceImpl.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }

	  @GetMapping("/bank/csv/banks")
	  public ResponseEntity<List<Bank>> getAllBanks() {
	    try {
	      List<Bank> bank = bankServiceImpl.getAllBanks();

	      if (bank.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(bank, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/bank/csv/download")
	  public ResponseEntity<Resource> getFile() {
	    String filename = "bank.csv";
	    InputStreamResource file = new InputStreamResource(bankServiceImpl.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
	  
	  
	@PostMapping(value ="/bankdetails")
	Bank createbankdetails(@RequestBody Bank bank) {
		Bank createbankdetails = bankservice.bankdetails(bank);
		return createbankdetails;
	}
	
}
