package com.example.hello3;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "greetingBean")
@RequestScoped
public class GreetingBean {
    private static final String GREETING = "Hello World!";

    public String getGreeting() {
        return GREETING;
    }
}
