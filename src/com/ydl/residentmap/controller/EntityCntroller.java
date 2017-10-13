package com.ydl.residentmap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/test")
public class EntityCntroller {
	 @RequestMapping(value = "/handle", method = { RequestMethod.GET }, produces = "text/plain;charset=UTF-8;")
	 public ResponseEntity<String> responseEntityStatusCode() { 
	        return new ResponseEntity<String>("The String ResponseBody with custom status code (500 Forbidden)",  
	                HttpStatus.INTERNAL_SERVER_ERROR);  
	    }  
	
}
