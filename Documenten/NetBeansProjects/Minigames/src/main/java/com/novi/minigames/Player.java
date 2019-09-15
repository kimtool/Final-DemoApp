package com.novi.minigames;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gebruiker
 */
public class Player {
    private String name;
    private char token;
    private int score;
    
    public Player (String name, char token){
        this.name=name;
        this.token=token;
    }
    public char Token(){
        return token;
    }
    
    public String Name(){
        return name;
    }
    
    public int Score(){
        return score;
    }
    
    public void addScore(){     //void = no return
        score++;
    }
    
}

