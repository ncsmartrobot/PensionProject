package com.demo.csv.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.demo.bean.Bank;


public class CSVHelperBank {
	public static String TYPE = "text/csv";
	static String[] HEADERs = {"aadhaarNumber","bankAccountNumber","bankName","bankType"};
	
	public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }
	public static List<Bank> csvToBanks(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Bank> banks = new ArrayList<Bank>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  Bank bank = new Bank(
		        		Long.parseLong(csvRecord.get("aadhaarNumber")),
		        		Long.parseLong(csvRecord.get("bankAccountNumber")),
		        		csvRecord.get("bankName"),
		        		csvRecord.get("bankType")
		            );

	    	  banks.add(bank);
		      }

		      return banks;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }
	
	public static ByteArrayInputStream csvToBanks(List<Bank> banks) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Bank bank : banks) {
	        List<String> data = Arrays.asList(
	        		
	        		String.valueOf(bank.getAadhaarNumber()),
	        		String.valueOf(bank.getBankAccountNumber()),
	        		bank.getBankName(),
	        		bank.getBankType()
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
	    

}
