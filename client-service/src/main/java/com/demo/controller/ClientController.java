package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ClientController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder builder;
	
	@Autowired 
	private FirstClient firstClient;
	
	@Autowired
	private SecondClient secondClient;
	
	
	@GetMapping("/client")
	public String getMessage()
	{
		return "Message";
	}
	
	@GetMapping("/restTemplate")
	public String callFromRestTempalte()
	{
		
		String first= restTemplate.getForObject("http://localhost:8080/first", String.class);
		
		String second= restTemplate.getForObject("http://localhost:8081/second",String.class);
		
		return first+"<----RestTemplate-->"+second;
	}
	
	@GetMapping("/webClient")
	public String callFromWebClient()
	{
		
		String first= builder.build()
				      .get()
				      .uri("http://localhost:8080/first")
				      .retrieve()
				      .bodyToMono(String.class)
				      .block();
		
		String second=builder.build()
				      .get()
					  .uri("http://localhost:8081/second")
					  .retrieve() // retrieve and execute
					  .bodyToMono(String.class) // store to string or type what we put
					  .block(); // sync call
		
		return first+"<----WebClient-->"+second;
	}
	
	@GetMapping("/feignClient")
	public String callFromFeignClient()
	{
		
	    String first=firstClient.hello();
	    
	    String second= secondClient.hello();
		
		return first+"<----FeignClient-->"+second;
	}
	
	
	

}
