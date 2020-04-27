package com.javabyexamples.spring.core.stereotypes;

import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    public void save() {
        throw new RuntimeException("Planned exception");
    }
}
