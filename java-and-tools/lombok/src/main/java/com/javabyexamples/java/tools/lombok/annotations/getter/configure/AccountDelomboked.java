package com.javabyexamples.java.tools.lombok.annotations.getter.configure;

public class AccountDelomboked {

    private String username;
    private String password;

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    public AccountDelomboked username(String username) {
        this.username = username;
        return this;
    }

    public AccountDelomboked password(String password) {
        this.password = password;
        return this;
    }
}
