package io.jfxdevelop.lab_2_easy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label groupLabel;

    @FXML
    protected void onHelloButtonClick() {
        Student student = new Student("Станислав", 20, "ИВТ-3");

        welcomeText.setText("Данные студента:");
        nameLabel.setText("Имя: " + student.getName());
        ageLabel.setText("Возраст: " + student.getAge());
        groupLabel.setText("Группа: " + student.getGroup());
    }
}
