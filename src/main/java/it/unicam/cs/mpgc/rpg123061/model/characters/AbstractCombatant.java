package it.unicam.cs.mpgc.rpg123061.model.characters;

import it.unicam.cs.mpgc.rpg123061.model.core.Attacker;
import it.unicam.cs.mpgc.rpg123061.model.core.Damageable;
import it.unicam.cs.mpgc.rpg123061.model.core.GameEntity;

/**
 * Manages only the vital state and base statistics of a combatant.
 * It implements the common behaviors for any entity that can both
 * attack and take damage, reducing code duplication.
 */
public abstract class AbstractCombatant implements GameEntity, Damageable, Attacker {
    private final String name;
    private final String description;
    private final int maxHp;
    private int currentHp;
    private final int baseAttackPower;

    /**
     * Constructs a new combatant with the specified attributes.
     *
     * @param name            the name of the combatant.
     * @param description     the description of the combatant.
     * @param maxHp           the maximum health points.
     * @param baseAttackPower the base attack power.
     */
    public AbstractCombatant(String name, String description, int maxHp, int baseAttackPower) {
        this.name = name;
        this.description = description;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.baseAttackPower = baseAttackPower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCurrentHp() {
        return currentHp;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public int getAttackPower() {
        return baseAttackPower;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount > 0) {
            this.currentHp = Math.max(0, this.currentHp - amount);
        }
    }

    @Override
    public void heal(int amount) {
        if (amount > 0 && isAlive()) {
            this.currentHp = Math.min(this.maxHp, this.currentHp + amount);
        }
    }

    @Override
    public boolean isAlive() {
        return this.currentHp > 0;
    }
}