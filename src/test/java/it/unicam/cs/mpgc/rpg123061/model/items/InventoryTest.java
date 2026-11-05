package it.unicam.cs.mpgc.rpg123061.model.items;

import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Inventory mechanics and the item consumption (Strategy Pattern).
 */
class InventoryTest {
    private Hunter player;
    private Medkit medkit;

    @BeforeEach
    void setUp() {
        player = new Hunter("TestHunter", 100, 20, 50);
        medkit = new Medkit("Small Medkit", 30);
    }

    @Test
    void testAddItemToInventory() {
        // Act
        player.getInventory().addItem(medkit);

        // Assert
        assertEquals(1, player.getInventory().getItems().size(), "Inventory should contain 1 item.");
        assertEquals("Small Medkit", player.getInventory().getItems().get(0).getName());
    }

    @Test
    void testUseMedkitHealsPlayer() {
        // Arrange: Player takes 50 damage (HP becomes 50/100)
        player.takeDamage(50);
        int hpAfterDamage = player.getCurrentHp();

        // Act: Use a medkit that heals 30 HP
        medkit.use(player);

        // Assert
        assertEquals(hpAfterDamage + 30, player.getCurrentHp(), "Player HP should increase by 30.");
    }

    @Test
    void testHealDoesNotExceedMaxHp() {
        // Arrange: Player is at full HP
        int maxHp = player.getMaxHp();

        // Act: Try to heal beyond max HP
        medkit.use(player);

        // Assert
        assertEquals(maxHp, player.getCurrentHp(), "Player HP should not exceed maximum capacity.");
    }
}