package com.ecommerceproject.apis;

import com.ecommerceproject.service.ReportExportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/export")
@CrossOrigin
public class ReportExportApis {
    @Autowired
    private ReportExportService reportService;
    @GetMapping("/exportReport")
    public ResponseEntity getNutritionReport(@RequestParam("month") Integer month, @RequestParam("year") Integer year) throws JRException {
        ByteArrayOutputStream reportStream=reportService.generateReport(month, year);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity(reportStream.toByteArray(),httpHeaders, HttpStatus.OK);
    }
}
