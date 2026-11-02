package it.unicam.cs.mpgc.rpg123061.view;

import java.util.Scanner;

/**
 * Handles purely textual Input/Output via the console.
 * It is completely unaware of the game's internal rules.
 */
public class CLIView implements GameView {
    private final Scanner scanner;

    /**
     * Initializes the standard console input scanner.
     */
    public CLIView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public int getUserChoice(String prompt, String[] options) {
        System.out.println("\n--- " + prompt + " ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int choice = -1;
        while (choice < 1 || choice > options.length) {
            System.out.print("Select an option (1-" + options.length + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > options.length) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        // Return 0-based index
        return choice - 1;
    }
}