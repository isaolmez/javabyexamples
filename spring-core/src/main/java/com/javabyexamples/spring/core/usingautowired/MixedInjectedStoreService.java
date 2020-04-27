package com.javabyexamples.spring.core.usingautowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MixedInjectedStoreService {

    private final EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    public MixedInjectedStoreService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void start() {
        managerService.manage();
        employeeService.work();
    }
}
