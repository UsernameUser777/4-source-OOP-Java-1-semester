package io.jfxdevelop.lab_7_1_part;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField nameField;

    @FXML
    protected void onHelloButtonClick() {
        String name = nameField.getText();
        if (name == null || name.isBlank()) {
            welcomeText.setText("Введите имя!");
        } else {
            welcomeText.setText("Привет, " + name + "!");
        }
    }
}
