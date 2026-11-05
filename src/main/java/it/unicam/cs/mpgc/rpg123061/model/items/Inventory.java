package it.unicam.cs.mpgc.rpg123061.model.items;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of usable items.
 * Encapsulates the logic for adding, removing, and retrieving items,
 * keeping the character classes clean and focused on combat/stats.
 */
public class Inventory {
    private final List<UsableItem> items;

    /**
     * Initializes an empty inventory.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the usable item to add.
     */
    public void addItem(UsableItem item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item the usable item to remove.
     */
    public void removeItem(UsableItem item) {
        this.items.remove(item);
    }

    /**
     * Retrieves an unmodifiable view or a copy of the items to protect internal state.
     *
     * @return a list of the current items in the inventory.
     */
    public List<UsableItem> getItems() {
        return new ArrayList<>(items);
    }
}