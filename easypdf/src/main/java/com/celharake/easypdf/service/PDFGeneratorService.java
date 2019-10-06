package com.celharake.easypdf.service;

import org.springframework.core.io.InputStreamResource;

import com.celharake.easypdf.dto.Settings;

public interface PDFGeneratorService {

	InputStreamResource build(Settings settings);
}
