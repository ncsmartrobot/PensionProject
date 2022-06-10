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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.demo.bean.Bank;
import com.demo.bean.Pensioner;
import com.demo.csv.helper.CSVHelperPensioner;
import com.demo.csv.message.ResponseMessage;
import com.demo.service.BankService;
import com.demo.service.PensionerService;
import com.demo.serviceimpl.PensionerServviceImpl;

@CrossOrigin("http://localhost:8081")
@Controller
@RestController
public class PensionerController {

	
	@Autowired(required = true)
	PensionerService pensionerservice;
	
	@Autowired(required = true)
	PensionerServviceImpl pensionerServviceImpl;
	
	@Autowired(required = true)
	BankService bankservice;
	
	public double pensionAmount;
	public int publicBankCharge = 500;
	public int privateBankCharge = 550;
	
	@PostMapping("/pensioner/csv/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (CSVHelperPensioner.hasCSVFormat(file)) {
	      try {
	    	  pensionerServviceImpl.save(file);

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

	  @GetMapping("/pensioner/csv/pensioners")
	  public ResponseEntity<List<Pensioner>> getAllPensioners() {
	    try {
	      List<Pensioner> pensioner = pensionerServviceImpl.getAllPensioners();

	      if (pensioner.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(pensioner, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/pensioner/csv/download")
	  public ResponseEntity<Resource> getFile() {
	    String filename = "tutorials.csv";
	    InputStreamResource file = new InputStreamResource(pensionerServviceImpl.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
	  
	@PostMapping(value = "/pensionerdetails")
	Pensioner createpensioner(@RequestBody Pensioner pensioner) {
		Pensioner createpensioner = pensionerservice.pensionerdetails(pensioner);
		return createpensioner; 
	}
	
	@GetMapping(value = "/PensionerDetailByAadhaar/{aadhaarNumber}")
    public ResponseEntity<String> getPensionerby(@PathVariable("aadhaarNumber") long aadhaarNumber) {
		
		Pensioner pens =pensionerservice.getPensionerById(aadhaarNumber);
		Bank bd = bankservice.getBankdetailsById(aadhaarNumber);
		return ResponseEntity.ok("Name: "+pens.getName()+"\n"+
				"Date of birth: "+pens.getDob()+"\n"+
				"PAN: "+pens.getPancard()+"\n"+
				"SalaryEarned: "+pens.getSalaryEarned()+"\n"+
				"Allowances: "+pens.getAllowance()+"\n"+
				"Pension Type: "+pens.getTypeSelfORFamilypension()+"\n"+
				"Bank name: "+bd.getBankName()+"\n"+
				"Account number: "+bd.getBankAccountNumber()+"\n"+
				"Bank Type: "+bd.getBankType()+"\n");
		
	}
	
	@GetMapping(value = "/pensiondetailsbyaadhaar/{aadhaarNumber}")
	public ResponseEntity<String> getPensioner(@PathVariable("aadhaarNumber") long aadhaarNumber) {
		
		Pensioner pensi = pensionerservice.getPensionerById(aadhaarNumber);
		Bank b = bankservice.getBankdetailsById(aadhaarNumber);
		
		try {
			
			if(b.getBankType().equals("Public bank")) {
				if(pensi.getTypeSelfORFamilypension().equals("self pension")) {
					pensionAmount = (0.8 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				else if(pensi.getTypeSelfORFamilypension().equals("family pension")) {
					pensionAmount = (0.5 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				return ResponseEntity.ok("Pension Amount of "+pensi.getTypeSelfORFamilypension()+": "+ pensionAmount+"Rs"+"\n"+"Public bank charges: INR "+ publicBankCharge);
			}
			else if(b.getBankType().equals("Private bank")) {
				if(pensi.getTypeSelfORFamilypension().equals("self pension")) {
					pensionAmount = (0.8 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				else if(pensi.getTypeSelfORFamilypension().equals("family pension")) {
					pensionAmount = (0.5 * pensi.getSalaryEarned()) + pensi.getAllowance();
				}
				return ResponseEntity.ok("Pension Amount of "+pensi.getTypeSelfORFamilypension()+": "+ pensionAmount+"Rs"+"\n"+"Private bank charges: INR "+ privateBankCharge);
			}
			
			return ResponseEntity.ok("Enter correct Pension Type ");
		} catch (Exception e) {
			return ResponseEntity.ok("Data does not exits ");
		}
	}
	
}
