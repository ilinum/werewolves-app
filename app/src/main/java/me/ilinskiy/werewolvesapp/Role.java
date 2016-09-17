package me.ilinskiy.werewolvesapp;

/**
 * Created by Svyatoslav Ilinskiy on 16.09.16
 */
public class Role {
    public final String name;
    public final String description;
    private int players = 0;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getPlayers() {
        return players;
    }

    public void addPlayer() {
        players++;
    }

    public void removePlayer() {
        if (players > 0) {
            players--;
        }
    }
}
