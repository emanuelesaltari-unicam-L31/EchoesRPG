package it.unicam.cs.mpgc.rpg123061.persistence;

import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;

/**
 * Data Transfer Object (DTO) pattern.
 * Represents a snapshot of the game's current state.
 * This class is used purely to group data together for saving and loading,
 * isolating the live game objects from the serialization process.
 */
public class GameState {
    private Hunter player;

    /**
     * Constructs a GameState with the current player.
     *
     * @param player the player to save.
     */
    public GameState(Hunter player) {
        this.player = player;
    }

    /**
     * Retrieves the saved player data.
     *
     * @return the saved Hunter.
     */
    public Hunter getPlayer() {
        return player;
    }
}