package com.javabyexamples.spring.mvc1.restwithjson;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@RequestBody Query query) {
        Customer customer = new Customer();
        customer.setName(query.getName());
        customer.setLastName("doe");
        customer.setCountry("us");
        customer.setCreditCardNumber("123-456");
        return customer;
    }
}
