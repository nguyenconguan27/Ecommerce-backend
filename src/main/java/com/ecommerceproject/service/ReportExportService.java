package com.ecommerceproject.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

public interface ReportExportService {
    ByteArrayOutputStream generateReport(Integer month, Integer year) throws JRException;
}
