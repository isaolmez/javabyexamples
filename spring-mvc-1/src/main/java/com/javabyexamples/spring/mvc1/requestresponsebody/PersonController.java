package com.javabyexamples.spring.mvc1.requestresponsebody;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson(@RequestParam("id") String id) {
        Person foundPerson = queryPerson(id);
        return foundPerson;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void savePerson(@RequestBody Person person) {
        // Save person...
    }

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_XML_VALUE)
    public Person getPersonInXML(@RequestParam("id") String id) {
        Person foundPerson = queryPerson(id);
        return foundPerson;
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_XML_VALUE)
    public void savePersonInXML(@RequestBody Person person) {
        // Save person...
    }

    /*
     * Unsupported formats
     */

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    public PersonWithoutXMLAnnotation getPersonInUnsupported(@RequestParam("id") String id) {
        return new PersonWithoutXMLAnnotation();
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
    public void savePersonInUnsupported(@RequestBody PersonWithoutXMLAnnotation person) {
        // Save person...
    }

    private Person queryPerson(String id) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAge(30);
        return person;
    }
}
