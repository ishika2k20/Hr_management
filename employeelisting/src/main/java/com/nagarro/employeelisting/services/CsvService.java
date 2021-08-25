package com.nagarro.employeelisting.services;

import com.nagarro.employeelisting.models.Employee;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class CsvService {

    public void exportToCSV(HttpServletResponse response, List<Employee> employees) throws IOException {
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees.csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Employee Code", "Name", "Location", "E-mail", "Date of Birth"};
        String[] nameMapping = {"employeeCode", "name", "location", "email", "dateOfBirth"};

        csvWriter.writeHeader(csvHeader);

        for (int i=0; i<employees.size(); i++) {
            csvWriter.write(employees.get(i), nameMapping);
        }

        csvWriter.close();

    }

}
