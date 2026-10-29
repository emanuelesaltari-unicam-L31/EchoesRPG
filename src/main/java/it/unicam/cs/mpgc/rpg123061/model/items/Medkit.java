package it.unicam.cs.mpgc.rpg123061.model.items;

import it.unicam.cs.mpgc.rpg123061.model.characters.AbstractCombatant;

/**
 * A concrete implementation of a UsableItem that heals the target.
 */
public class Medkit implements UsableItem {
    private final String name;
    private final int healAmount;

    /**
     * Constructs a Medkit with a specific healing power.
     *
     * @param name       the name of the medkit.
     * @param healAmount the amount of HP to restore.
     */
    public Medkit(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Restores " + healAmount + " HP to the target.";
    }

    @Override
    public void use(AbstractCombatant target) {
        if (target != null && target.isAlive()) {
            target.heal(healAmount);
        }
    }
}