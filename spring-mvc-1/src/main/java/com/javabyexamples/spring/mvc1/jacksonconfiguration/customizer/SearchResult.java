package com.javabyexamples.spring.mvc1.jacksonconfiguration.customizer;

import lombok.Data;

@Data
public class SearchResult {

    private String searchTerm;
    private int totalCount;
}
