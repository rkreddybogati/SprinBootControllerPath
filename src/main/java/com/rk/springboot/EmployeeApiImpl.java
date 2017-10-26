package com.rk.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srk on 26/10/17.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeApiImpl implements EmployeeApi{

    @Override
    public List<String> getAllEmployeeNames() {
        List<String> employeeNames = new ArrayList<String >();
        employeeNames.add("RK");
        employeeNames.add("Thanush");
        employeeNames.add("Shashank");
        employeeNames.add("Leela");
        return employeeNames;
    }

    @Override
    public String getFirstEmployeeNames(String priority) {
        String employee;
        if("first".endsWith(priority)) {
            employee = "RK";
        } else if ("last".endsWith(priority)) {
            employee = "Leela";
        } else {
            employee = "default";
        }
        return employee;
    }
}
