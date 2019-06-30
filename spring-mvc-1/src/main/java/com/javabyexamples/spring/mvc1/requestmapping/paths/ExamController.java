package com.javabyexamples.spring.mvc1.requestmapping.paths;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/exams/{department}")
public class ExamController {

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<Exam> list(@PathVariable("department") String department) {
        return getExams();
    }

    @RequestMapping(path = "/list/{name}", method = RequestMethod.GET)
    public Exam get(@PathVariable("department") String department, @PathVariable("name") String name) {
        Exam exam = new Exam(name, department, new Date());
        return exam;
    }

    @RequestMapping(path = "/list/**/*", method = RequestMethod.GET)
    public List<Exam> listAll() {
        return getExams();
    }

    @RequestMapping(path = "/${resultsPath}", method = RequestMethod.GET)
    public List<String> results() {
        return getResults();
    }

    private List<String> getResults() {
        List<String> results = new ArrayList<>();
        results.add("80");
        results.add("90");
        return results;
    }

    private List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        exams.add(new Exam("oop", "cs", new Date()));
        return exams;
    }
}
