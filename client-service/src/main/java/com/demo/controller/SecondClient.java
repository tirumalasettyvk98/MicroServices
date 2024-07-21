package com.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="SECOND-SERVICE")
public interface SecondClient {
	
	@GetMapping("/second")
	public String hello();
	
	
	
}
