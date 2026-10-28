package it.unicam.cs.mpgc.rpg123061.model.core;

/**
 * Represents entities capable of dealing damage.
 * Separating this from Damageable allows for entities that can attack but are invincible,
 * or entities that can take damage but cannot attack (e.g., destructible obstacles).
 */
public interface Attacker {
    /**
     * Retrieves the base attack power of the entity.
     *
     * @return the base attack power.
     */
    int getAttackPower();
}