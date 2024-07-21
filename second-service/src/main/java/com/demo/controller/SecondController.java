package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class SecondController {
	
	Logger logger= LoggerFactory.getLogger(SecondController.class);

	
	@GetMapping("/second")
	//@Retry(name="hello",fallbackMethod = "errorHandler")// default value 3, it will call 3 times if it fails still it will report error
	@CircuitBreaker(name="hello",fallbackMethod = "errorHandler")
	public String hello()
	{	
		
		logger.info("==========================================>>Second controller method is called");
		new RestTemplate().getForEntity("localhost:8080/abcd", String.class);
		return "Second service from Micro-services-2";
	}
	
	
	public String errorHandler(Exception ex)
	{
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~  ExecutingallbackMethod = \"errorHandler\" "+ex.getMessage());
		return " Second Service not working , please try after sometime ";
	}


}
