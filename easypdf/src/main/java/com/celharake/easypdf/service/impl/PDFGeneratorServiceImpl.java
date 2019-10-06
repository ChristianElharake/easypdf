package com.celharake.easypdf.service.impl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.celharake.easypdf.dto.Settings;
import com.celharake.easypdf.service.PDFGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService{

	@Override
	public InputStreamResource build(Settings settings) {
		return null;
	}

}
