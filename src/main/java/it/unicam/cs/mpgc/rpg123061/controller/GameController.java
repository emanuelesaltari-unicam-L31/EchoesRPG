package it.unicam.cs.mpgc.rpg123061.controller;

import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
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

    /**
     * Transfers all loot from the current sector to the player's inventory.
     *
     * @return the number of items looted.
     */
    public int lootCurrentSector() {
        if (currentSector == null) return 0;

        int itemsLooted = 0;
        for (var item : currentSector.getLoot()) {
            player.getInventory().addItem(item);
            itemsLooted++;
        }
        currentSector.clearLoot();
        return itemsLooted;
    }

    /**
     * Uses an item from the player's inventory on the player themselves.
     *
     * @param index the 0-based index of the item in the inventory.
     * @return true if the item was used successfully, false otherwise.
     */
    public boolean useItemFromInventory(int index) {
        var items = player.getInventory().getItems();
        if (index >= 0 && index < items.size()) {
            var itemToUse = items.get(index);

            itemToUse.use(player);
            player.getInventory().removeItem(itemToUse);
            return true;
        }
        return false;
    }
}