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
                "Doctor: Don't worry. Mine too.");
        jokes.add("I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.");
        jokes.add("Job interview in a psychiatry: \n" +
                "So you’re interested in working with us. What is your experience with mentally disturbed people?\n" +
                "I’ve been on Facebook for 5 years now.\n" +
                "Very good, the job is yours.");
        jokes.add("Doctor says to his patient: \"You have cancer and Alzheimer.\"\n" +
                "Patient: \"At least I don't have cancer.");
        jokes.add("Doctor: Your test results are showing you'll easily live to be 80. \n" +
                "Patient: But, wait, I am 80 just now.\n" +
                "Doctor: See, I told you to live healthier!\n");
    }

    public List<String> getJokes() {
        return jokes;
    }

    public String getJoke() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}
