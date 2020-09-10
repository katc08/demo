package com.svnt.workshop.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.svnt.workshop.demo.HelloWorld.HelloWorld;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicTest {

    @Spy
    HelloWorld world;

    private Logger log = LoggerFactory.getLogger(BasicTest.class);
    
    //example of actual code behavior
    @Test
    public void testHelloWorldNoMock() {

        String helloWorld = world.helloWorld();

        log.info("Message from original method is {}", helloWorld);
        assertEquals("Hello World", helloWorld);
    }

    //example of mocking a method from a spy
    @Test
    public void testHelloWorldMock() {
        // Mockito.when(world.helloWorld()).thenReturn("Using Mockito!");  //for using a mocked instance
        Mockito.doReturn("Using Mockito!").when(world).helloWorld(); //for using a spy
        String helloWorld = world.helloWorld();

        log.info("Message from mocked method is {}", helloWorld);
        assertEquals("Using Mockito!", helloWorld);
    }
}
