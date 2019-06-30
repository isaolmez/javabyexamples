package com.javabyexamples.spring.mvc1.restwithjson.view;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabyexamples.spring.mvc1.restwithjson.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewedCustomerController {

    @JsonView(PublicDetails.class)
    @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer publicDetails(@RequestBody Query query) {
        return getCustomer(query);
    }

    @JsonView(PrivateDetails.class)
    @GetMapping(value = "/private", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer privateDetails(@RequestBody Query query) {
        return getCustomer(query);
    }

    private Customer getCustomer(Query query) {
        Customer customer = new Customer();
        customer.setName(query.getName());
        customer.setLastName("doe");
        customer.setCountry("us");
        customer.setCreditCardNumber("123-456");
        return customer;
    }
}
