package it.unicam.cs.mpgc.rpg123061.model.core;

/**
 * Represents a basic entity within the game world.
 * Any object, character, or interactable item should implement this interface
 * to provide fundamental identification.
 */
public interface GameEntity {
    /**
     * Retrieves the name of the entity.
     *
     * @return the name of the entity.
     */
    String getName();

    /**
     * Retrieves the description of the entity, providing lore or specific details.
     *
     * @return the description of the entity.
     */
    String getDescription();
}