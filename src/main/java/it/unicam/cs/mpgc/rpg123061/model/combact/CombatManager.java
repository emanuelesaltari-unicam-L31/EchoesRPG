package it.unicam.cs.mpgc.rpg123061.model.combact;

import it.unicam.cs.mpgc.rpg123061.model.core.Attacker;
import it.unicam.cs.mpgc.rpg123061.model.core.Damageable;

/**
 * Handles the combat logic and damage calculation
 * between an attacker and a target.
 */
public class CombatManager {

    /**
     * Executes a basic attack from an attacker to a target.
     * Future extensions can include defense stats, critical hits, or evasion modifiers here.
     *
     * @param attacker the entity performing the attack.
     * @param target   the entity receiving the attack.
     */
    public void executeAttack(Attacker attacker, Damageable target) {
        if (attacker == null || target == null || !target.isAlive()) {
            return;
        }

        int damage = calculateDamage(attacker);
        target.takeDamage(damage);
    }

    /**
     * Calculates the damage based on the attacker's stats.
     * Extracted to a private method to allow easy modifications or overriding in subclasses.
     *
     * @param attacker the entity attacking.
     * @return the calculated damage value.
     */
    private int calculateDamage(Attacker attacker) {
        // Base implementation: pure attack power.
        // Can be expanded with RNG (Random Number Generation) for damage variance.
        return attacker.getAttackPower();
    }
}