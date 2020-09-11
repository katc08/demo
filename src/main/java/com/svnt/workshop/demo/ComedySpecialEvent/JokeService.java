package com.svnt.workshop.demo.ComedySpecialEvent;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private Set<String> jokeBin;

    @Autowired
    public JokeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
        jokeBin = new HashSet<>();
    }

    /**
     * Calls the joke api to obtain a random joke
     * @return the joke from the api
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public Joke getJoke() throws JsonProcessingException {

        ResponseEntity<String> responseEntity = restTemplate.exchange(JOKE_URL, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class, new Object[0]);
        
        Joke joke = objectMapper.readValue((String) responseEntity.getBody(), Joke.class);

        return joke;
    }


    /**
     * Calls the joke api after a certain amount of tries or until it returns a joke in the genre we are looking for
     * @param genre - the type of joke we want
     * @param tries - the amount of times we're willing to try to get that type of joke
     * @return hopefully the type of joke we want, a random joke, or an empty joke
     */
    public Joke getJoke(String genre, int tries) throws JsonProcessingException{

        ResponseEntity<String> responseEntity;

        Joke joke = new Joke();
        while (!(joke.getType().equals(genre)) || tries >= 0) {

            responseEntity = restTemplate.exchange(JOKE_URL, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class, new Object[0]);
            joke = objectMapper.readValue((String) responseEntity.getBody(), Joke.class);

            tries--;
        }

        return joke;
    }


    /**
     * Adds an joke to the joke bin
     * @param badJoke - the joke that people didn't laugh at
     */
    public void addToJokeBin(Joke badJoke) {
        jokeBin.add(badJoke.getSetUp() + " " + badJoke.getPunchline());
    }
    
}
