package it.unicam.cs.mpgc.rpg123061.persistence;

import java.io.IOException;

/**
 * Defines the contract for saving and loading game data.
 * The core game logic will depend on this interface, allowing the actual storage mechanism
 * (JSON, XML, Database) to be swapped out easily without affecting the rest of the application.
 */
public interface SaveRepository {
    /**
     * Saves the current game state to a persistent storage.
     *
     * @param state    the game state to save.
     * @param filename the destination file or identifier.
     * @throws IOException if an error occurs during saving.
     */
    void save(GameState state, String filename) throws IOException;

    /**
     * Loads a game state from persistent storage.
     *
     * @param filename the source file or identifier.
     * @return the loaded game state.
     * @throws IOException if an error occurs during loading or if the file is not found.
     */
    GameState load(String filename) throws IOException;
}