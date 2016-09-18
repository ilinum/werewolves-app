package me.ilinskiy.werewolvesapp;

/**
 * Created by Svyatoslav Ilinskiy on 16.09.16
 */
public class Role extends Player {
    private int players = 0;
    private boolean isCollapsed = true;

    public Role(String name, String description) {
        super(name, description);
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

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }
}
