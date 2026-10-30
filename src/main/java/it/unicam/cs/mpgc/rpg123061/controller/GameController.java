package it.unicam.cs.mpgc.rpg123061.controller;

import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.combat.CombatManager;
import it.unicam.cs.mpgc.rpg123061.model.exploration.Sector;

/**
 * Orchestrates the main flow of the game.
 * Acts as the Controller in the MVC architecture, managing interactions
 * between the Player (Hunter), the Environment (Sector), and Systems (Combat).
 */
public class GameController {
    private final Hunter player;
    private final CombatManager combatManager;
    private Sector currentSector;

    /**
     * Initializes the game controller with the main player character.
     *
     * @param player the user-controlled Hunter.
     */
    public GameController(Hunter player) {
        this.player = player;
        this.combatManager = new CombatManager();
    }

    /**
     * Moves the player to a new sector.
     *
     * @param sector the new sector to explore.
     */
    public void enterSector(Sector sector) {
        this.currentSector = sector;
    }

    /**
     * Retrieves the sector currently being explored by the player.
     *
     * @return the current sector.
     */
    public Sector getCurrentSector() {
        return currentSector;
    }

    /**
     * Executes a combat turn where the player attacks the anomaly in the current sector,
     * and if the anomaly survives, it counterattacks.
     *
     * @return true if combat occurred, false if there is no enemy or the player is dead.
     */
    public boolean executeCombatTurn() {
        if (currentSector == null || player == null || !player.isAlive()) {
            return false;
        }

        // Check if there is a living anomaly in the sector
        return currentSector.getAnomaly().map(anomaly -> {
            if (anomaly.isAlive()) {
                // Player attacks Anomaly
                combatManager.executeAttack(player, anomaly);

                // Anomaly counterattacks if still alive
                if (anomaly.isAlive()) {
                    combatManager.executeAttack(anomaly, player);
                }
                return true;
            }
            return false;
        }).orElse(false);
    }
}