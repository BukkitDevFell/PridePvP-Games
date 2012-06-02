package com.pridemc.games.arena;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 6/2/12
 */
public class Arena {
    public enum State {
        WAITING_FOR_PLAYERS,
        INITIAL_GRACE,
        RUNNING_GAME
    }

    private String name;
    private Set<ArenaPlayer> players = new HashSet<ArenaPlayer>();
    private State state = State.WAITING_FOR_PLAYERS;

    public Arena(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<ArenaPlayer> getPlayers() {
        return players;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
