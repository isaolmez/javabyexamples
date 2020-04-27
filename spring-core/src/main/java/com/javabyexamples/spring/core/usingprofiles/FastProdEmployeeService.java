package com.javabyexamples.spring.core.usingprofiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod & fast")
public class FastProdEmployeeService implements EmployeeService {

}
