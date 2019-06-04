package com.javabyexamples.libraries.nanohttpd;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.Value;

public class JsonServer extends NanoHTTPD {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonServer() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    public static void main(String[] args) {
        try {
            new JsonServer();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @SneakyThrows
    @Override
    public Response serve(IHTTPSession session) {
        Map<String, String> parameters = session.getParms();
        String message = "Hello " + parameters.get("username");
        Greeting greeting = new Greeting(message);
        String jsonGreeting = mapper.writeValueAsString(greeting);
        return newFixedLengthResponse(jsonGreeting);
    }

    @Value
    public static class Greeting {

        private String message;
    }
}