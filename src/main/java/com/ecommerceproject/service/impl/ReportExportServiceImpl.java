package com.ecommerceproject.service.impl;

import com.ecommerceproject.dwentity.Report;
import com.ecommerceproject.dwentity.TimeDim;
import com.ecommerceproject.dwrepository.ProductDimRepository;
import com.ecommerceproject.dwrepository.SaleFactRepository;
import com.ecommerceproject.dwrepository.TimeDimRepository;
import com.ecommerceproject.dwrepository.UserFactRepository;
import com.ecommerceproject.service.ReportExportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportExportServiceImpl implements ReportExportService {
    @Autowired
    private SaleFactRepository saleFactRepository;
    @Autowired
    private TimeDimRepository timeDimRepository;
    @Autowired
    private UserFactRepository userFactRepository;
    @Autowired
    private ProductDimRepository productDimRepository;
    @Override
    public ByteArrayOutputStream generateReport(Integer month, Integer year) throws JRException {

        String filePath="C:\\Users\\Admin\\Desktop\\Ecommerce-backend\\Ecommerce-backend\\Ecommerce-backend\\src\\main\\resources\\statistic_report.jrxml";
        String timeQuery = "";

        //Mỗi lần query sẽ phải truyền đủ 4 biến này
        String timeKey = "m";

        int quater = (month - 1) / 3 + 1;

        //Xét biến timeKey là m (query theo tháng), q (query theo quý), y (query theo năm)
        if(timeKey.equals("m")){
            timeQuery = "Tháng " + String.valueOf(month)+" năm "+String.valueOf(year);
        } else if (timeKey.equals("q")) {
            timeQuery = "Quý " + String.valueOf(quater)+" năm "+String.valueOf(year);
        } else {
            timeQuery = "Năm " + String.valueOf(year);
        }
        Integer revenue = 0, totalSale = 0, totalRegister = 0;
        try {
            TimeDim timeDim = timeDimRepository.findByMonthAndYear(month, year).get(0);
            revenue = saleFactRepository.findByRevenue(timeDim.getTime_id());
            totalSale = saleFactRepository.findByTotal_Sale(timeDim.getTime_id());
            totalRegister = userFactRepository.findById(timeDim.getTime_id()).get().getTotalRegister();
        }
        catch (Exception e) {
            revenue = 0; totalSale = 0; totalRegister = 0;
        }


        //3 biến totalRevenue, saledProduct, registedUser Lấy từ db lên
        Report month1 = new Report(timeQuery, revenue, totalSale, totalRegister);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("timeKey",timeKey);
        parameters.put("month",month);
        parameters.put("quater",quater);
        parameters.put("year",year);
        parameters.put("timeQuery",month1.getTimeQuery());
        parameters.put("totalRevenue",month1.getTotalRevenue());
        parameters.put("saledProduct",month1.getSaledProduct());
        parameters.put("registedUser", month1.getRegistedUser());
        JasperReport report= JasperCompileManager.compileReport(filePath);
        JasperPrint print= JasperFillManager.fillReport(report,parameters,new JREmptyDataSource());
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        JRPdfExporter exporter=new JRPdfExporter();
        SimplePdfExporterConfiguration configuration=new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();
        return byteArrayOutputStream;
    }
}
