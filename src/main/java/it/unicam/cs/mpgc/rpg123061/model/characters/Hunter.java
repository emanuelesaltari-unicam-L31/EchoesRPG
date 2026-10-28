package it.unicam.cs.mpgc.rpg123061.model.characters;

/**
 * Represents the player character in the game, an Anomaly Hunter.
 * Extends AbstractCombatant to inherit standard combat features and adds
 * specific mechanics like Energy Points for special abilities.
 */
public class Hunter extends AbstractCombatant {
    private int energyPoints;

    /**
     * Constructs a new Hunter with specific attributes.
     *
     * @param name            the name of the Hunter.
     * @param maxHp           the maximum health points.
     * @param baseAttackPower the base physical attack power.
     * @param maxEnergyPoints the maximum energy points for abilities.
     */
    public Hunter(String name, int maxHp, int baseAttackPower, int maxEnergyPoints) {
        super(name, "A trained Anomaly Hunter, equipped to survive hostile sectors.", maxHp, baseAttackPower);
        this.energyPoints = maxEnergyPoints;
    }

    /**
     * Retrieves the current energy points available for special abilities.
     *
     * @return the current energy points.
     */
    public int getEnergyPoints() {
        return energyPoints;
    }

    /**
     * Consumes a specified amount of energy points.
     * Ensures energy points do not drop below zero.
     *
     * @param amount the amount of energy to consume.
     */
    public void consumeEnergy(int amount) {
        if (amount > 0) {
            this.energyPoints = Math.max(0, this.energyPoints - amount);
        }
    }
}