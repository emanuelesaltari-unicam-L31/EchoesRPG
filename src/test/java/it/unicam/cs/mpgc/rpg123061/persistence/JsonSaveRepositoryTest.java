package it.unicam.cs.mpgc.rpg123061.persistence;

import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the JsonSaveRepository ensuring serialization and deserialization work correctly.
 */
class JsonSaveRepositoryTest {

    @Test
    void testSaveAndLoadGameState(@TempDir Path tempDir) throws IOException {
        // Arrange
        JsonSaveRepository repository = new JsonSaveRepository();
        Hunter player = new Hunter("TestPlayer", 100, 20, 50);
        player.takeDamage(10); // HP is now 90
        GameState originalState = new GameState(player);

        // Use a temporary file for testing
        File tempFile = tempDir.resolve("test_save.json").toFile();

        // Act - Save
        repository.save(originalState, tempFile.getAbsolutePath());

        // Assert - File exists
        assertTrue(tempFile.exists(), "The save file should be created.");

        // Act - Load
        GameState loadedState = repository.load(tempFile.getAbsolutePath());

        // Assert - Data integrity
        assertNotNull(loadedState, "Loaded state should not be null.");
        assertEquals("TestPlayer", loadedState.getPlayer().getName(), "Player name should match.");
        assertEquals(90, loadedState.getPlayer().getCurrentHp(), "Player HP should be preserved.");
    }
}