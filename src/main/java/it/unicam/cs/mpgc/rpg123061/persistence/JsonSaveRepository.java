package it.unicam.cs.mpgc.rpg123061.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Handles purely the serialization and
 * deserialization of GameState objects into JSON format using the Gson library.
 */
public class JsonSaveRepository implements SaveRepository {
    private final Gson gson;

    /**
     * Initializes the JsonSaveRepository with a Gson instance configured for pretty printing.
     */
    public JsonSaveRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void save(GameState state, String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(state, writer);
        }
    }

    @Override
    public GameState load(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            return gson.fromJson(reader, GameState.class);
        }
    }
}