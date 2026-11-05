package it.unicam.cs.mpgc.rpg123061.model.exploration;

import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.items.Medkit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Sector class to ensure correct handling of anomalies and loot.
 */
class SectorTest {
    private Sector sector;
    private Anomaly anomaly;
    private Medkit medkit;

    @BeforeEach
    void setUp() {
        sector = new Sector("Test Sector", "A dark room.");
        anomaly = new Anomaly("TestEnemy", "Desc", 50, 10, "Low");
        medkit = new Medkit("Test Medkit", 20);
    }

    @Test
    void testSetAndGetAnomaly() {
        // Assert initial state is empty
        assertTrue(sector.getAnomaly().isEmpty(), "Sector should initially have no anomaly.");

        // Act
        sector.setAnomaly(anomaly);

        // Assert
        assertTrue(sector.getAnomaly().isPresent(), "Sector should now contain an anomaly.");
        assertEquals("TestEnemy", sector.getAnomaly().get().getName());
    }

    @Test
    void testAddAndClearLoot() {
        // Act
        sector.addLoot(medkit);

        // Assert
        assertEquals(1, sector.getLoot().size(), "Sector should contain 1 item.");

        // Act
        sector.clearLoot();

        // Assert
        assertTrue(sector.getLoot().isEmpty(), "Loot should be empty after clearing.");
    }
}