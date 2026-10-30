package it.unicam.cs.mpgc.rpg123061.model.exploration;

import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.items.UsableItem;
import it.unicam.cs.mpgc.rpg123061.model.core.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a specific area or location in the game world.
 * A Sector can contain a hostile Anomaly and various lootable items.
 */
public class Sector implements GameEntity {
    private final String name;
    private final String description;
    private Anomaly currentAnomaly;
    private final List<UsableItem> loot;

    /**
     * Constructs a new Sector.
     *
     * @param name        the name of the sector (e.g., "Sector 7G").
     * @param description the description of the environment.
     */
    public Sector(String name, String description) {
        this.name = name;
        this.description = description;
        this.loot = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Anomaly present in this sector.
     *
     * @param anomaly the enemy entity to place in the sector.
     */
    public void setAnomaly(Anomaly anomaly) {
        this.currentAnomaly = anomaly;
    }

    /**
     * Retrieves the Anomaly currently in this sector, if any.
     * Uses Optional to explicitly handle the absence of an enemy (Null Object Pattern alternative).
     *
     * @return an Optional containing the Anomaly, or empty if the sector is clear.
     */
    public Optional<Anomaly> getAnomaly() {
        return Optional.ofNullable(currentAnomaly);
    }

    /**
     * Adds an item to the sector's loot pool.
     *
     * @param item the item to add.
     */
    public void addLoot(UsableItem item) {
        if (item != null) {
            this.loot.add(item);
        }
    }

    /**
     * Retrieves all available loot in the sector.
     *
     * @return a list of usable items.
     */
    public List<UsableItem> getLoot() {
        return new ArrayList<>(loot); // Defensive copy to protect internal state
    }

    /**
     * Clears the loot from the sector after it has been collected.
     */
    public void clearLoot() {
        this.loot.clear();
    }
}