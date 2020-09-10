package com.svnt.workshop.demo.ComedySpecialEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

@Component
public class Joke {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("setup")
    private String setup;

    @JsonProperty("punchline")
    private String punchline;

    Joke() {
        id = 0;
        type = "";
        setup = "";
        punchline = "";
    }

    Joke(String setup, String punchline) {
        id = 0;
        type = "generic";
        this.setup = setup;
        this.punchline = punchline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetUp() {
        return setup;
    }

    public void setSetUp(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

}
