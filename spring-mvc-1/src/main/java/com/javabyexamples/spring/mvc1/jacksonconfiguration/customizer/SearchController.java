package com.javabyexamples.spring.mvc1.jacksonconfiguration.customizer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SearchResult getCustomer(@RequestBody Query query) {
        SearchResult searchResult = new SearchResult();
        searchResult.setSearchTerm(query.getSearchTerm());
        searchResult.setTotalCount(100);
        return searchResult;
    }
}
