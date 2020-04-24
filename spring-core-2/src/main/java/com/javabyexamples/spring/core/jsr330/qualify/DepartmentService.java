package com.javabyexamples.spring.core.jsr330.qualify;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Inject
    public DepartmentService(@Named("department") DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
