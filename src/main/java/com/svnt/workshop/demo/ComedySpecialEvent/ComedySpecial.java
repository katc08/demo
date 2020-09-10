package com.svnt.workshop.demo.ComedySpecialEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComedySpecial {

    private JokeService jokeBook;

    private List<Celebrity> lineUp = Arrays.asList(new Celebrity("John Mulaney", 38), new Celebrity("The Rock", 48), new Celebrity("Meryl Streep", 71));

    @Autowired
    public ComedySpecial(JokeService jokeService) {
        this.jokeBook = jokeService;
    }
    
    @GetMapping(value = "/joke", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> joke() {
        
        Random contestantNum = new Random();

        Celebrity comedian = lineUp.get(contestantNum.nextInt(lineUp.size()));

        Joke joke = jokeBook.getJoke();
        String comedianLine = comedian.getName() + "(" + comedian.getAge() + "): " + joke.getSetUp() + " " + joke.getPunchline();

        return ResponseEntity.status(HttpStatus.OK).body(comedianLine);
    }

    public void addToLineup(Celebrity celeb) {
        lineUp.add(celeb);
    }

    public void addToLineup(String name, int age) {
        lineUp.add(new Celebrity(name, age));
    }
}
