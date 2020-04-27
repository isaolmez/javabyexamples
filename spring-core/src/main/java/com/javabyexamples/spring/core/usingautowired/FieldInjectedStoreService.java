package com.javabyexamples.spring.core.usingautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInjectedStoreService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    public void start() {
        managerService.manage();
        employeeService.work();
    }
}
