package com.demo;

import javax.batch.api.chunk.ItemProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableEurekaClient
@ComponentScan(excludeFilters = 
		{@ComponentScan.Filter(type = Filterype.ASSIGNABLE_TYPE, classes = ItemProcessor.class)})
public class PensionerDetailMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(PensionerDetailMicroservice.class, args);
	}

}
