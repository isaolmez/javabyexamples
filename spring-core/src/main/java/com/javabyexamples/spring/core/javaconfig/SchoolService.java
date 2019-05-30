package com.javabyexamples.spring.core.javaconfig;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class SchoolService {

    @Autowired
    private TeacherService teacherService;

    private PrincipalService principalService;

    private final ClassService classService;

    public SchoolService(ClassService classService) {
        this.classService = classService;
    }

    public void teachStudents() {
        List<Student> students = classService.getStudents();
        students.forEach(teacherService::teach);
        principalService.administer();
    }

    @Autowired
    public void setPrincipalService(PrincipalService principalService) {
        this.principalService = principalService;
    }
}
