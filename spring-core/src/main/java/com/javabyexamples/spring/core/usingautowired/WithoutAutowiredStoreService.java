package com.javabyexamples.spring.core.usingautowired;

import org.springframework.stereotype.Component;

@Component
public class WithoutAutowiredStoreService {

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    public WithoutAutowiredStoreService(EmployeeService employeeService,
      ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    public void start() {
        managerService.manage();
        employeeService.work();
    }
}
