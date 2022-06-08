package com.bezkoder.spring.files.csv.helper;

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

import com.bezkoder.spring.files.csv.model.Tutorial;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "aadhaarNumber","name","dob","salaryEarned","allowance","typeSelfORFamilypension","basicSalary",
		  "pensionerAccountNumber","pancard","userName","password" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Tutorial> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Tutorial> tutorials = new ArrayList<Tutorial>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Tutorial tutorial = new Tutorial(
        		Long.parseLong(csvRecord.get("aadhaarNumber")),
        		csvRecord.get("name"),
        		csvRecord.get("dob"),
        		Double.parseDouble(csvRecord.get("salaryEarned")),
        		Double.parseDouble(csvRecord.get("allowance")),
        		csvRecord.get("typeSelfORFamilypension"),
        		Double.parseDouble(csvRecord.get("basicSalary")),
        		csvRecord.get("pensionerAccountNumber"),
        		csvRecord.get("pancard"),
        		csvRecord.get("userName"),
        		csvRecord.get("password")
            );

        tutorials.add(tutorial);
      }

      return tutorials;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Tutorial> tutorials) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Tutorial tutorial : tutorials) {
        List<String> data = Arrays.asList(
        		
        		String.valueOf(tutorial.getAadhaarNumber()),
        		tutorial.getName(),
        		tutorial.getDob(),
        		String.valueOf(tutorial.getSalaryEarned()),
        		String.valueOf(tutorial.getAllowance()),
        		tutorial.getTypeSelfORFamilypension(),
        		String.valueOf(tutorial.getBasicSalary()),
        		tutorial.getPensionerAccountNumber(),
        		tutorial.getPancard(),
        		tutorial.getUserName(),
        		tutorial.getPassword()
        		
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
