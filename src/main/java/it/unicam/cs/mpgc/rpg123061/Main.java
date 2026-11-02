package it.unicam.cs.mpgc.rpg123061;

import it.unicam.cs.mpgc.rpg123061.controller.GameController;
import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import it.unicam.cs.mpgc.rpg123061.model.exploration.Sector;
import it.unicam.cs.mpgc.rpg123061.view.CLIView;
import it.unicam.cs.mpgc.rpg123061.view.GameView;

/**
 * Main application entry point.
 * Acts as the Composition Root: it wires the MVC components together
 * and starts the main game loop.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Initialize the View
        GameView view = new CLIView();
        view.displayMessage("=== INITIALIZING ECHOES: THE ANOMALY HUNTER ===");

        // 2. Initialize the Model (Entities)
        Hunter player = new Hunter("Kael", 100, 20, 50);
        Sector tutorialSector = new Sector("Sector Alpha", "A dark, abandoned space station corridor.");
        Anomaly basicEnemy = new Anomaly("Crawler", "A low-level bioweapon.", 40, 10, "Low");

        // Add the enemy to the sector
        tutorialSector.setAnomaly(basicEnemy);

        // 3. Initialize the Controller
        GameController controller = new GameController(player);
        controller.enterSector(tutorialSector);

        // 4. Start the Game Loop
        view.displayMessage("\nWelcome, Hunter " + player.getName() + ".");
        view.displayMessage("Entering " + tutorialSector.getName() + "...");
        view.displayMessage(tutorialSector.getDescription());

        boolean isRunning = true;
        while (isRunning && player.isAlive()) {
            view.displayMessage("\n--- PLAYER STATUS ---");
            view.displayMessage("HP: " + player.getCurrentHp() + "/" + player.getMaxHp());

            String[] options = {"Explore Sector", "Engage in Combat", "Flee (Exit Game)"};
            int choice = view.getUserChoice("What do you want to do?", options);

            switch (choice) {
                case 0:
                    view.displayMessage("You search the area...");
                    if (controller.getCurrentSector().getAnomaly().isPresent() && controller.getCurrentSector().getAnomaly().get().isAlive()) {
                        view.displayMessage("Watch out! An Anomaly is lurking here!");
                    } else {
                        view.displayMessage("The sector is clear.");
                    }
                    break;
                case 1:
                    boolean combatOccurred = controller.executeCombatTurn();
                    if (combatOccurred) {
                        view.displayMessage("Combat executed!");
                        controller.getCurrentSector().getAnomaly().ifPresent(anomaly -> {
                            view.displayMessage("Anomaly HP: " + anomaly.getCurrentHp());
                            if (!anomaly.isAlive()) {
                                view.displayMessage("The Anomaly has been defeated!");
                            }
                        });
                    } else {
                        view.displayMessage("There is nothing to fight here.");
                    }
                    break;
                case 2:
                    view.displayMessage("You fled the sector. Game Over.");
                    isRunning = false;
                    break;
            }
        }

        if (!player.isAlive()) {
            view.displayMessage("\nYou have been defeated. MISSION FAILED.");
        }
    }
}