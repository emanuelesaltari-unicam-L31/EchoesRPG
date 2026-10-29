package it.unicam.cs.mpgc.rpg123061.model.items;

import it.unicam.cs.mpgc.rpg123061.model.characters.AbstractCombatant;
import it.unicam.cs.mpgc.rpg123061.model.core.GameEntity;

/**
 * Represents an item that can be used on a combatant.
 * Each specific item will implement its own logic in the use() method.
 */
public interface UsableItem extends GameEntity {
    /**
     * Applies the item's specific effect to the target combatant.
     *
     * @param target the combatant receiving the effect of the item.
     */
    void use(AbstractCombatant target);
}