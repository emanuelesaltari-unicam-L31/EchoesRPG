package it.unicam.cs.mpgc.rpg123061.model.combat;

import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the CombatManager to ensure damage calculation and HP updates work correctly.
 */
class CombatManagerTest {
    private CombatManager combatManager;
    private Hunter player;
    private Anomaly enemy;

    @BeforeEach
    void setUp() {
        // Initialize fresh instances before each test
        combatManager = new CombatManager();
        player = new Hunter("TestHunter", 100, 20, 50);
        enemy = new Anomaly("TestEnemy", "Description", 50, 15, "Low");
    }

    @Test
    void testExecuteAttackReducesTargetHp() {
        // Arrange
        int initialHp = enemy.getCurrentHp();

        // Act
        combatManager.executeAttack(player, enemy);

        // Assert
        assertEquals(initialHp - player.getAttackPower(), enemy.getCurrentHp(),
                "Enemy HP should decrease by the attacker's power.");
    }

    @Test
    void testEntityDiesWhenHpReachesZero() {
        // Act: Deal massive damage exceeding max HP
        enemy.takeDamage(1000);

        // Assert
        assertEquals(0, enemy.getCurrentHp(), "HP should not drop below zero.");
        assertFalse(enemy.isAlive(), "Entity should be dead when HP is zero.");
    }
}