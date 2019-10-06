package com.celharake.easypdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celharake.easypdf.service.PDFGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PDFController {
	
	@Autowired
	private PDFGeneratorService generatorService;
	
    @PostMapping("/generate-pdf")
    public ResponseEntity<InputStreamResource> generate() {
    	log.info("Generation pdf from setttings : ");
    	generatorService.build(null);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
