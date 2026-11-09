package it.unicam.cs.mpgc.rpg123061.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * A Graphical UI implementation of GameView.
 * Uses a BlockingQueue to seamlessly synchronize asynchronous UI clicks
 * with the synchronous game loop running on a background thread.
 */
public class GUIView implements GameView {
    private final TextArea outputArea;
    private final VBox buttonContainer;
    private final BlockingQueue<Integer> inputQueue;

    public GUIView(TextArea outputArea, VBox buttonContainer) {
        this.outputArea = outputArea;
        this.buttonContainer = buttonContainer;
        this.inputQueue = new ArrayBlockingQueue<>(1);
    }

    @Override
    public void displayMessage(String message) {
        // UI updates must run on the JavaFX Application Thread
        Platform.runLater(() -> {
            outputArea.appendText(message + "\n\n");
            outputArea.setScrollTop(Double.MAX_VALUE); // Auto-scroll to bottom
        });
    }

    @Override
    public int getUserChoice(String prompt, String[] options) {
        Platform.runLater(() -> {
            buttonContainer.getChildren().clear();
            outputArea.appendText("--- " + prompt + " ---\n");

            for (int i = 0; i < options.length; i++) {
                int choiceIndex = i;
                Button btn = new Button((i + 1) + ". " + options[i]);
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

                // On click, send the choice index to the blocking queue and clear buttons
                btn.setOnAction(e -> {
                    buttonContainer.getChildren().clear();
                    inputQueue.offer(choiceIndex);
                });

                buttonContainer.getChildren().add(btn);
            }
        });

        try {
            // Pauses the background game loop until the user clicks a button
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        }
    }
}