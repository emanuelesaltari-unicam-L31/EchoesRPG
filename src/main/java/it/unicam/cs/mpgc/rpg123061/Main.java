package it.unicam.cs.mpgc.rpg123061;

import it.unicam.cs.mpgc.rpg123061.controller.GameController;
import it.unicam.cs.mpgc.rpg123061.model.characters.Anomaly;
import it.unicam.cs.mpgc.rpg123061.model.characters.Hunter;
import it.unicam.cs.mpgc.rpg123061.model.exploration.Sector;
import it.unicam.cs.mpgc.rpg123061.persistence.GameState;
import it.unicam.cs.mpgc.rpg123061.persistence.JsonSaveRepository;
import it.unicam.cs.mpgc.rpg123061.persistence.SaveRepository;
import it.unicam.cs.mpgc.rpg123061.view.CLIView;
import it.unicam.cs.mpgc.rpg123061.view.GameView;

import java.io.IOException;

public class Main {
    private static final String SAVE_FILE = "savegame.json";

    public static void main(String[] args) {
        GameView view = new CLIView();
        SaveRepository saveRepository = new JsonSaveRepository();

        view.displayMessage("=== INITIALIZING ECHOES: THE ANOMALY HUNTER ===");

        Hunter player = new Hunter("Kael", 100, 20, 50);
        Sector tutorialSector = new Sector("Sector Alpha", "A dark, abandoned space station corridor.");
        Anomaly basicEnemy = new Anomaly("Crawler", "A low-level bioweapon.", 40, 10, "Low");
        tutorialSector.setAnomaly(basicEnemy);

        String[] startupOptions = {"Start New Game", "Load Game"};
        int startupChoice = view.getUserChoice("Main Menu", startupOptions);

        if (startupChoice == 1) {
            try {
                GameState loadedState = saveRepository.load(SAVE_FILE);
                if (loadedState != null && loadedState.getPlayer() != null) {
                    player = loadedState.getPlayer();
                    view.displayMessage("Game loaded successfully! Welcome back, " + player.getName() + ".");
                }
            } catch (IOException e) {
                view.displayMessage("No save file found or error loading. Starting new game...");
            }
        }

        GameController controller = new GameController(player);
        controller.enterSector(tutorialSector);

        view.displayMessage("\nEntering " + tutorialSector.getName() + "...");
        view.displayMessage(tutorialSector.getDescription());

        boolean isRunning = true;
        while (isRunning && player.isAlive()) {
            view.displayMessage("\n--- PLAYER STATUS ---");
            view.displayMessage("HP: " + player.getCurrentHp() + "/" + player.getMaxHp());

            String[] options = {"Explore Sector", "Engage in Combat", "Save Game", "Flee (Exit Game)"};
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
                    try {
                        saveRepository.save(new GameState(player), SAVE_FILE);
                        view.displayMessage("Game saved successfully!");
                    } catch (IOException e) {
                        view.displayMessage("Error saving game: " + e.getMessage());
                    }
                    break;
                case 3:
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