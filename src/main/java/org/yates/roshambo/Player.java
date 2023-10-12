package org.yates.roshambo;

import java.util.UUID;
public abstract class Player {
    protected final String name;
    protected UUID uuid;

    public Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return this.name;
    }

    public abstract Move getMove();
}