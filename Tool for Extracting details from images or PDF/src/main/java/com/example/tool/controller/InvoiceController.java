package com.example.tool.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.tool.useclasses.InvoiceExtractionService;

import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.io.File;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceExtractionService extractionService;

    @PostMapping("/extract")
    public ResponseEntity<String> extractInvoiceDetails(@RequestParam("file") MultipartFile file) {
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            String text;
            if (file.getContentType().equals("application/pdf")) {
                text = extractionService.extractTextFromPDF(convFile);
            } else {
                text = extractionService.extractTextFromImage(convFile);
            }
            return new ResponseEntity<>(text, HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



