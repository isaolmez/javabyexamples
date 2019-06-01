package com.javabyexamples.spring.core.usingautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMethodInjectedStoreService {

    private EmployeeService employeeService;
    private ManagerService managerService;

    @Autowired
    public void prepare(EmployeeService employeeService, ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    public void start() {
        managerService.manage();
        employeeService.work();
    }
}
