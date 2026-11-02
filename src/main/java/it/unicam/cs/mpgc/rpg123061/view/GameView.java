package it.unicam.cs.mpgc.rpg123061.view;

/**
 * Defines the contract for any user interface.
 * This allows easy swapping between a Console UI, a Graphical UI (JavaFX), or a Web UI
 * without changing the core game logic.
 */
public interface GameView {
    /**
     * Displays a standard message to the user.
     *
     * @param message the text to display.
     */
    void displayMessage(String message);

    /**
     * Asks the user to choose an action from a menu.
     *
     * @param prompt  the question or instruction presented to the user.
     * @param options the list of available choices.
     * @return the index of the selected option (0-based).
     */
    int getUserChoice(String prompt, String[] options);
}