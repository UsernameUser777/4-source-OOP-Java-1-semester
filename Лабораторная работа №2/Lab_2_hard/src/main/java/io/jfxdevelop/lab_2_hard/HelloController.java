package io.jfxdevelop.lab_2_hard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label soundLabel;

    @FXML
    protected void onDogButtonClick() {
        Animal dog = new Dog();
        soundLabel.setText("Собака говорит: " + dog.makeSound());
    }

    @FXML
    protected void onCatButtonClick() {
        Animal cat = new Cat();
        soundLabel.setText("Кошка говорит: " + cat.makeSound());
    }
}
