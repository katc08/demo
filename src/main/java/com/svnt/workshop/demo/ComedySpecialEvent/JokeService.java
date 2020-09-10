package com.svnt.workshop.demo.ComedySpecialEvent;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {

    private static final String JOKE_URL = "https://official-joke-api.appspot.com/random_joke";
    private Set<String> jokeBin;

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    @Autowired
    public JokeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
        this.jokeBin = new HashSet<>();
    }

    /**
     * Calls the joke api to obtain a random joke
     * @return the joke from the api
     */
    public Joke getJoke() {

        ResponseEntity<String> responseEntity = restTemplate.exchange(JOKE_URL, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class, new Object[0]);
        
        Joke joke;
        try {
            joke = objectMapper.readValue((String) responseEntity.getBody(), Joke.class);
        } catch (IOException ex) {
            return new Joke();
        }

        if (jokeBin.contains(joke.getPunchline())) {
            return new Joke("Why did the chicken cross the road?", "To get to the other side");
        }

        return joke;
    }


    /**
     * Calls the joke api after a certain amount of tries or until it returns a joke in the genre we are looking for
     * @param genre - the type of joke we want
     * @param tries - the amount of times we're willing to try to get that type of joke
     * @return hopefully the type of joke we want, a random joke, or an empty joke
     */
    public Joke getJoke(String genre, int tries) {

        ResponseEntity<String> responseEntity;

        Joke joke = new Joke();
        while (!(joke.getType().equals(genre)) || tries != 0) {

            responseEntity = restTemplate.exchange(JOKE_URL, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class, new Object[0]);
            try {
                joke = objectMapper.readValue((String) responseEntity.getBody(), Joke.class);
            } catch (IOException ex) {
                return new Joke();
            }

            tries--;
        }

        return joke;
    }


    /**
     * Adds a joke to the used joke list to avoid re-using old jokes
     * @param joke - the joke to put in the bin
     */
    public void addUsedJoke(Joke joke) {
        jokeBin.add(joke.getPunchline());
    }
    
}
