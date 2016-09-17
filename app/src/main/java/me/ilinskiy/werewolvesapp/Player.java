package me.ilinskiy.werewolvesapp;

import java.io.Serializable;

/**
 * Created by Svyatoslav Ilinskiy on 17.09.16
 */
public abstract class Player implements Serializable {
    public final String name;
    public final String description;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
