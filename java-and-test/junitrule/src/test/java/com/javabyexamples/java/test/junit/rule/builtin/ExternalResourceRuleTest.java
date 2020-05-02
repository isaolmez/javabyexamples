package com.javabyexamples.java.test.junit.rule.builtin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

public class ExternalResourceRuleTest {

    private Server myServer = new Server();

    @Rule
    public final ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            myServer.connect();
        }

        @Override
        protected void after() {
            myServer.disconnect();
        }
    };

    @Test
    public void shouldManageExternalResource() {
        System.out.println("Client can connect now!");
    }

    public static class Server {

        public void connect() {
            System.out.println("Connecting to the server");
        }

        public void disconnect() {
            System.out.println("Disconnecting from the server");
        }
    }
}
