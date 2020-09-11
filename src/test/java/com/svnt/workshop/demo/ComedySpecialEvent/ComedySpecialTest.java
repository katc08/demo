package com.svnt.workshop.demo.ComedySpecialEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
                "None, that's a software problem");
    }

    @Test
    public void testGetRandomJoke() {

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

        ResponseEntity<String> responseEntity = comedySpecial.getJoke();

        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND));
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

         verify(jokeService).addToJokeBin(Mockito.any(Joke.class));
    }

    @Test
    public void testTossOutJokeException() {

        /**
         * Insert unit test here
         */

    }
}
