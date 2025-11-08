package io.jfxdevelop.lab_1_easy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> directionBox;

    @FXML
    private Label resultLabel;

    @FXML
    protected void onConvertButtonClick() {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String direction = directionBox.getValue();
            double result;

            if ("Цельсий → Фаренгейт".equals(direction)) {
                result = (inputTemp * 9 / 5) + 32;
                resultLabel.setText(String.format("%.2f°C = %.2f°F", inputTemp, result));
            } else if ("Фаренгейт → Цельсий".equals(direction)) {
                result = (inputTemp - 32) * 5 / 9;
                resultLabel.setText(String.format("%.2f°F = %.2f°C", inputTemp, result));
            } else {
                resultLabel.setText("Выберите направление перевода.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Ошибка: введите корректное число.");
        }
    }

    @FXML
    public void initialize() {
        ObservableList<String> directions = FXCollections.observableArrayList(
                "Цельсий → Фаренгейт",
                "Фаренгейт → Цельсий"
        );
        directionBox.setItems(directions);
        directionBox.setValue("Цельсий → Фаренгейт");
    }
}
