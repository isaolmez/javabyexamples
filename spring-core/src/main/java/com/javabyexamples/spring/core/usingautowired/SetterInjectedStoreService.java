package com.javabyexamples.spring.core.usingautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectedStoreService {

    private EmployeeService employeeService;
    private ManagerService managerService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    public void start() {
        managerService.manage();
        employeeService.work();
    }
}
