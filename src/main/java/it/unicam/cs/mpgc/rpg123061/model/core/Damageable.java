package it.unicam.cs.mpgc.rpg123061.model.core;

/**
 * Represents entities that can take damage and be healed.
 * Decouples health management from core entity properties, improving extensibility.
 */
public interface Damageable {
    /**
     * Gets the current health points (HP) of the entity.
     *
     * @return the current health points.
     */
    int getCurrentHp();

    /**
     * Gets the maximum health points the entity can have.
     *
     * @return the maximum health points.
     */
    int getMaxHp();

    /**
     * Reduces the entity's health points by the specified amount.
     *
     * @param amount the amount of damage to take. Must be non-negative.
     */
    void takeDamage(int amount);

    /**
     * Increases the entity's health points by the specified amount, up to the maximum HP.
     *
     * @param amount the amount of health to restore. Must be non-negative.
     */
    void heal(int amount);

    /**
     * Checks if the entity is still alive (current HP > 0).
     *
     * @return true if the entity is alive, false otherwise.
     */
    boolean isAlive();
}