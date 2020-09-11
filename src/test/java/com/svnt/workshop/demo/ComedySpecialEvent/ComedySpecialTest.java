package com.svnt.workshop.demo.ComedySpecialEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ComedySpecialTest {

    // @InjectMocks is used to put the mocked dependencies into the class that you plan to run unit tests on
    @InjectMocks
    ComedySpecial comedySpecial;

    @Mock
    JokeService jokeService;

    private Joke badJoke;
    private Joke badProgrammingJoke;

    @BeforeEach
    public void setUp() {
        badJoke = new Joke("What do you call a singing laptop?", "A Dell");
        badProgrammingJoke = new Joke("How many software developers does it take to change a lightbulb?",
                "None, that's a hardware problem");
    }

    @Test
    public void testGetJoke() {

        /**
         * Insert mocking here
         */

        ResponseEntity<String> responseEntity = comedySpecial.getJoke();

        assertTrue(responseEntity.getBody().contains(badJoke.getPunchline()));
    }

    @Test
    public void testGetProgrammingJoke() {

        /**
         * Insert unit test here
         */
    }


    @Test
    public void testGetJokeException() {

        /**
         * Insert mocking here
         */
        
    }


    @Test
    public void testGetProgrammingJokeException() {

        /**
         * Insert unit test here
         */
    }

    @Test
    public void testTossOutJoke() {

        /**
         * Insert unit test here
         */

    }

    @Test
    public void testTossOutJokeException() {

        /**
         * Insert unit test here
         */

    }
}
