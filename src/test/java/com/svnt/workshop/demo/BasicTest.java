package com.svnt.workshop.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.svnt.workshop.demo.HelloWorld.HelloWorld;
import com.svnt.workshop.demo.HelloWorld.HelloWorld2;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicTest {

    @Mock
    HelloWorld mockWorld;

    @Spy
    HelloWorld2 spyWorld;

    private Logger log = LoggerFactory.getLogger(BasicTest.class);
    
    //example of keeping actual code behavior on a mocked instance
    @Test
    public void testHelloWorldNoMock() {

        Mockito.when(mockWorld.helloWorld()).thenCallRealMethod();
        String helloWorld = mockWorld.helloWorld();

        log.info("Message from original method is {}", helloWorld);
        assertEquals("Hello World!", helloWorld);
    }

    //example of mocking behavior from a mocked instance
    @Test
    public void testHelloWorldMock() {

        Mockito.when(mockWorld.helloWorld()).thenReturn("Using Mockito!");  //for using a mocked instance
        String helloWorld = mockWorld.helloWorld();

        log.info("Message from mocked method is {}", helloWorld);
        assertEquals("Using Mockito!", helloWorld);
    }



    //example of using a spy and not mocking behavior
    @Test
    public void testHelloWorld2NoMock() {

        String helloWorld = spyWorld.helloWorld();

        log.info("Message from original method is {}", helloWorld);
        assertEquals("Hello World 2.0!", helloWorld);
    }

    @Test
    public void testHelloWorld2Mock() {
        
        Mockito.doReturn("Using Mockito spy!").when(spyWorld).helloWorld(); //using Mockito.when will work too, depending on the version of Mockito being used
        String helloWorld = spyWorld.helloWorld();

        log.info("Message from spied method is {}", helloWorld);
        assertEquals("Using Mockito spy!", helloWorld);
    }
}
