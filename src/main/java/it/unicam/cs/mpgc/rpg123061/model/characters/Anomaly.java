package it.unicam.cs.mpgc.rpg123061.model.characters;

/**
 * Represents an enemy entity in the game, known as an Anomaly.
 * Contains specific properties like a threat level to categorize the danger.
 */
public class Anomaly extends AbstractCombatant {
    private final String threatLevel;

    /**
     * Constructs a new Anomaly with specific attributes.
     *
     * @param name            the name of the Anomaly.
     * @param description     the lore or description of the Anomaly.
     * @param maxHp           the maximum health points.
     * @param baseAttackPower the base attack power.
     * @param threatLevel     the classification of the Anomaly's danger (e.g., "Low", "High", "Critical").
     */
    public Anomaly(String name, String description, int maxHp, int baseAttackPower, String threatLevel) {
        super(name, description, maxHp, baseAttackPower);
        this.threatLevel = threatLevel;
    }

    /**
     * Retrieves the threat level of this Anomaly.
     *
     * @return the threat level as a String.
     */
    public String getThreatLevel() {
        return threatLevel;
    }
}