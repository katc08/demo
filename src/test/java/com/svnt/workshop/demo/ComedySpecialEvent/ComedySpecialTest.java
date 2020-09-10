package com.svnt.workshop.demo.ComedySpecialEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComedySpecialTest {

        // @InjectMocks is used to put the mocked dependencies into the class that you plan to run unit tests on

    @InjectMocks
    ComedySpecial comedySpecial;

    @Mock
    JokeService jokeService;

    private Celebrity celebrity;

    @BeforeEach
    public void setUp() {
        celebrity = new Celebrity("Keanu Reeves", 50);
    }


    @Test
    public void testSetName() {
        assertEquals("Keanu Reeves", celebrity.getName());
    }


    @Test
    public void testJoke() {

        /**
         * Insert unit test code into here
         */

         comedySpecial.joke();
    }
    
}
