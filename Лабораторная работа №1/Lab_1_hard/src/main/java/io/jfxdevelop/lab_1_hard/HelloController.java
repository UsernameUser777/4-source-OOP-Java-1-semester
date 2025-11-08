package io.jfxdevelop.lab_1_hard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    @FXML
    protected void onShowDivisorsClick() {
        String input = inputField.getText();
        try {
            int number = Integer.parseInt(input);
            if (number <= 0) {
                resultLabel.setText("Введите положительное целое число.");
                return;
            }

            StringBuilder divisors = new StringBuilder("Делители числа " + number + ": ");
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    divisors.append(i).append(" ");
                }
            }
            resultLabel.setText(divisors.toString());

        } catch (NumberFormatException e) {
            resultLabel.setText("Ошибка: введите корректное целое число.");
        }
    }
}
