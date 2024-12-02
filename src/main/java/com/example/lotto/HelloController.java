package com.example.lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class HelloController {

    private final List<Integer> sorsoltSzamok = new ArrayList<>();
    private final Random random = new Random();

    @FXML
    private Label textOut;
    @FXML
    private Label textList;
    @FXML
    private Button button;


    @FXML
    protected void buttonEvent() {
        if (button.getText().equals("Sorsol")) {
            if (sorsoltSzamok.size() < 5) {
                simulateRandomNumbers();
            }
        } else if (button.getText().equals("Rendez")) {
            Collections.sort(sorsoltSzamok);
            textList.setText(sorsoltSzamok.toString());
            button.setText("Sorsol");
            sorsoltSzamok.clear();
        }
    }

    private void simulateRandomNumbers() {
        new Thread(() -> {
            try {
                button.setDisable(true);
                int randomNum = 0;
                    float sleeptime = 80f;
                    for (int i = 0; i < 15; i++) {
                        randomNum = random.nextInt(90) + 1;
                        int finalRandomNum = randomNum;

                        Platform.runLater(() -> textOut.setText(String.valueOf(finalRandomNum)));

                        Thread.sleep((int) sleeptime);
                        sleeptime *= 1.1f;}
                do {
                    randomNum = random.nextInt(90) + 1;
                    int finalRandomNum = randomNum;
                    Platform.runLater(() -> textOut.setText(String.valueOf(finalRandomNum)));
                } while(sorsoltSzamok.contains(randomNum));

                sorsoltSzamok.add(randomNum);
                Platform.runLater(() -> textList.setText(sorsoltSzamok.toString()));
                if (sorsoltSzamok.size() == 5) {
                    Platform.runLater(() -> button.setText("Rendez"));
                }
                button.setDisable(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
