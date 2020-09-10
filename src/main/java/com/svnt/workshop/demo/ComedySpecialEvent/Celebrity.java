package com.svnt.workshop.demo.ComedySpecialEvent;

import org.springframework.stereotype.Component;

@Component
public class Celebrity {
    private String name;
    private int age;
    private String favoriteJoke;

    Celebrity() {
        this.name = "";
        this.age = 0;
        this.favoriteJoke = "";
    }
 
    Celebrity(String name, int age) {
        this.name = name;
        this.age = age;

        this.favoriteJoke = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteJoke() {
        return favoriteJoke;
    }

    public void setFavoriteJoke(String joke) {
        this.favoriteJoke = joke;
    }
}
