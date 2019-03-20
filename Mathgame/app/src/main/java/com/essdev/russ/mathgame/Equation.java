package com.essdev.russ.mathgame;

public class Equation {
    public int numOne, numTwo;

    public Equation () {
        numOne = (int)(Math.random()*12) + 1;
        numTwo = (int)(Math.random()*12) + 1;
    }
}
