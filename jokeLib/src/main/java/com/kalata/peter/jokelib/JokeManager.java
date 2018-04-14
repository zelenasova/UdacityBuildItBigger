package com.kalata.peter.jokelib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeManager {

    private List<String> jokes = new ArrayList<>();
    private Random random;

    public JokeManager() {
        random = new Random();
        jokes.add("Patient: Oh doctor, I’m just so nervous. This is my first operation.\n" +
                "-\n" +
                "Doctor: Don't worry. Mine too.");
        jokes.add("I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.");
        jokes.add("A naked women robbed a bank. Nobody could remember her face.");
        jokes.add("Doctor says to his patient: \"You have cancer and Alzheimer.\"\n" +
                "-\n" +
                "Patient: \"At least I don't have cancer.");
    }

    public List<String> getJokes() {
        return jokes;
    }

    public String getJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
