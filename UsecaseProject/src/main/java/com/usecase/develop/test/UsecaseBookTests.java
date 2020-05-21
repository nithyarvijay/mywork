package com.usecase.develop.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

public class UsecaseBookTests 
{
	@Autowired
    private RestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
 
    public String testBookService() { 
        final String uri = "http://localhost:9085/Books";      
        String result = restTemplate.getForObject(uri, String.class);  
        System.out.println(result);         
        return result;
    }
    
}