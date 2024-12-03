package com.progtech_beadando_rsb.model;

public class Player {
    private String name;
    private final char token;

    public Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getToken() {
        return token;
    }
}
