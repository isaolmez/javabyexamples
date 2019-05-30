package com.javabyexamples.spring.core.usingautowired;

import org.springframework.stereotype.Component;

@Component
public class AutowireFreeConstructorInjectedStoreService {

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    public AutowireFreeConstructorInjectedStoreService(EmployeeService employeeService,
                                                       ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }
}
