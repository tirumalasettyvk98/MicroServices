package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Firstcontroller {
	
	@Autowired
	private Environment env;
	
	@GetMapping("/first")
	public String hello()
	{
		return "first service from Micro-services-1";
	}

	@GetMapping("/load")
	public String load()
	{
		return "loadBalancer "+ env.getProperty("local.server.port");
	}
}
