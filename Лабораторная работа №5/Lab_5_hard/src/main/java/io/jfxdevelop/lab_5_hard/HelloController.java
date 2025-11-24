package io.jfxdevelop.lab_5_hard;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML private TextField input1;
    @FXML private TextField input2;
    @FXML private Label resultLabel;
    @FXML private TextArea historyArea;

    private Double parseInput(TextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            showError("Поле не должно быть пустым");
            return null;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            showError("Введите корректное число: \"" + text + "\"");
            return null;
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void appendHistory(String operation, double a, double b, double result) {
        historyArea.appendText(String.format("%f %s %f = %f%n", a, operation, b, result));
    }

    @FXML protected void handleAdd() {
        Double a = parseInput(input1);
        Double b = parseInput(input2);
        if (a != null && b != null) {
            double result = a + b;
            resultLabel.setText("Сумма: " + result);
            appendHistory("+", a, b, result);
        }
    }

    @FXML protected void handleSubtract() {
        Double a = parseInput(input1);
        Double b = parseInput(input2);
        if (a != null && b != null) {
            double result = a - b;
            resultLabel.setText("Разность: " + result);
            appendHistory("-", a, b, result);
        }
    }

    @FXML protected void handleMultiply() {
        Double a = parseInput(input1);
        Double b = parseInput(input2);
        if (a != null && b != null) {
            double result = a * b;
            resultLabel.setText("Произведение: " + result);
            appendHistory("*", a, b, result);
        }
    }

    @FXML protected void handleDivide() {
        Double a = parseInput(input1);
        Double b = parseInput(input2);
        if (a != null && b != null) {
            if (b == 0) {
                showError("Деление на ноль невозможно");
            } else {
                double result = a / b;
                resultLabel.setText("Частное: " + result);
                appendHistory("/", a, b, result);
            }
        }
    }

    @FXML protected void handleClear() {
        input1.clear();
        input2.clear();
        resultLabel.setText("Результат будет здесь");
    }

    @FXML protected void handleClearHistory() {
        historyArea.clear();
    }
}
