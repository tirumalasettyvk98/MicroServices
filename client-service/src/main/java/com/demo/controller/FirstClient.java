package com.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="FIRST-SERVICE")
public interface FirstClient {
	
	@GetMapping("/first")
	public String hello();
}
