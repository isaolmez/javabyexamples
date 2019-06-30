package com.javabyexamples.spring.mvc1.jacksonconfiguration;

import java.util.Date;
import lombok.Data;

@Data
public class SearchResult {

    private final Date date = new Date();
    private String searchTerm;
    private int totalCount;
}
