package com.svnt.workshop.demo.ComedySpecialEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComedySpecial {

    private JokeService jokeBook;
    private Random contestantNum = new Random();

    private List<Celebrity> lineUp = Arrays.asList(new Celebrity("John Mulaney", 38), new Celebrity("The Rock", 48), new Celebrity("Meryl Streep", 71));

    @Autowired
    public ComedySpecial(JokeService jokeService) {
        this.jokeBook = jokeService;
    }
    
    @GetMapping(value = "/joke", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJoke() {
        
        Celebrity comedian = lineUp.get(contestantNum.nextInt(lineUp.size()));

        Joke joke;
        try {
            joke = jokeBook.getJoke();
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String comedianLine = comedian.getName() + "(" + comedian.getAge() + "): " + joke.getSetUp() + " " + joke.getPunchline();

        return ResponseEntity.status(HttpStatus.OK).body(comedianLine);
    }

    public String getProgrammingJoke() {

        Celebrity comedian = lineUp.get(contestantNum.nextInt(lineUp.size()));

        Joke programmingJoke;
        try {
            programmingJoke = jokeBook.getJoke("programming", 10);
        } catch (JsonProcessingException ex) {
            return "No programming jokes!";
        }

        return comedian.getName() + "(" + comedian.getAge() + "): " + programmingJoke.getSetUp() + " " + programmingJoke.getPunchline();
    }

}
