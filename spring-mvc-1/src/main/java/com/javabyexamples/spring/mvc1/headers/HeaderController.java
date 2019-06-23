package com.javabyexamples.spring.mvc1.headers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/getHeader")
    public String getHeader(@RequestHeader("Accept") String accept) {
        return accept;
    }

    @GetMapping("/getHeaderFromRequest")
    public String getHeaderFromRequest(HttpServletRequest servletRequest) {
        return servletRequest.getHeader("Accept");
    }

    @GetMapping("/getHeaders")
    public HttpHeaders getHeaders(@RequestHeader HttpHeaders httpHeaders) {
        return httpHeaders;
    }

    @GetMapping("/getHeadersFromMap")
    public Map<String, String> getHeadersFromMap(@RequestHeader Map<String, String> httpHeaders) {
        return httpHeaders;
    }

    @GetMapping("/getHeadersFromMultiValue")
    public MultiValueMap<String, String> getHeadersFromMultiValue(@RequestHeader MultiValueMap<String, String> httpHeaders) {
        return httpHeaders;
    }

    @GetMapping("/setHeader")
    public void setHeader(HttpServletResponse servletResponse) {
        servletResponse.setHeader("X-Source", "Spring");
    }

    @GetMapping("/setHeaderWithHttpHeaders")
    public HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Source", "Spring");
        return headers;
    }
}
