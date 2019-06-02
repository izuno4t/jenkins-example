package com.example.service;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloServiceTest {

    @Test
    public void sayHello() {
        HelloService service = new HelloService();
        assertThat(service.sayHello()).isEqualTo("hello");
    }

    @Test
    public void sayHappy() {
        HelloService service = new HelloService();
        assertThat(service.sayHappy()).isEqualTo("HAPPY");
    }
}