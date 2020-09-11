package com.svnt.workshop.demo.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;

public class Greetings {
    
    private HelloWorld world;

    @Autowired
    public Greetings(HelloWorld helloWorld) {

        this.world = helloWorld;
    }

    public String greetings() {
        return world.helloWorld();
    }
}
