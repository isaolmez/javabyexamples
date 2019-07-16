package com.javabyexamples.spring.mvc1.headers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/getHeader")
    public String getHeader(@RequestHeader("Accept") String acceptHeader) {
        return acceptHeader;
    }

    @GetMapping("/getHeaderAsList")
    public List<String> getHeaders(@RequestHeader("Accept") List<String> acceptHeaders) {
        return acceptHeaders;
    }

    @GetMapping("/getHeaderFromRequest")
    public String getHeaderFromRequest(HttpServletRequest servletRequest) {
        return servletRequest.getHeader("Accept");
    }

    @GetMapping("/getHeaderFromEntity")
    public List<String> getHeaderFromEntity(HttpEntity<String> httpEntity) {
        return httpEntity.getHeaders().getAccept().stream().map(MimeType::toString).collect(Collectors.toList());
    }

    @GetMapping("/getHeaders")
    public Set<String> getHeaders(@RequestHeader HttpHeaders httpHeaders) {
        return httpHeaders.keySet();
    }

    @GetMapping("/getHeadersFromMap")
    public Set<String> getHeadersFromMap(@RequestHeader Map<String, String> httpHeaders) {
        return httpHeaders.keySet();
    }

    @GetMapping("/getHeadersFromMultiValue")
    public Set<String> getHeadersFromMultiValue(@RequestHeader MultiValueMap<String, String> httpHeaders) {
        return httpHeaders.keySet();
    }

    @GetMapping("/setHeader")
    public void setHeader(HttpServletResponse servletResponse) {
        servletResponse.setHeader("X-Source", "Spring");
        servletResponse.addHeader("X-Source", "Java");
    }

    @GetMapping("/setHeaderWithHttpEntity")
    public ResponseEntity<String> setHeaderWithEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Source", "Spring");

        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

    @GetMapping("/setHeaderWithHttpHeaders")
    public HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Source", "Spring");
        return headers;
    }
}
