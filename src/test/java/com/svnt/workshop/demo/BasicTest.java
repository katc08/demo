package com.svnt.workshop.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.svnt.workshop.demo.HelloWorld.Greetings;
import com.svnt.workshop.demo.HelloWorld.HelloWorld;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicTest {

    @InjectMocks
    Greetings testGreeting;

    @Mock
    HelloWorld mockWorld;

    private Logger log = LoggerFactory.getLogger(BasicTest.class);
    
    //example of keeping actual code behavior on a mocked instance
    @Test
    public void testHelloWorldNoMock() {

        Mockito.when(mockWorld.helloWorld()).thenCallRealMethod();
        String helloWorld = testGreeting.greetings();

        log.info("Message from original method is {}", helloWorld);
        assertEquals("Hello World!", helloWorld);
    }

    //example of mocking behavior from a mocked instance
    @Test
    public void testHelloWorldMock() {

        Mockito.when(mockWorld.helloWorld()).thenReturn("Using Mockito!");  //for using a mocked instance
        String helloWorld = testGreeting.greetings();

        log.info("Message from mocked method is {}", helloWorld);
        assertEquals("Using Mockito!", helloWorld);
    }

}
