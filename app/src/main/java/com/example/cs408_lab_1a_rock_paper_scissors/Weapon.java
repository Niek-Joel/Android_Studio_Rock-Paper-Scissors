package com.example.cs408_lab_1a_rock_paper_scissors;

public enum Weapon {

    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");
    private String message;

    private Weapon(String msg) { message = msg; }

    @Override
    public String toString() { return message; }

};