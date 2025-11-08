package io.jfxdevelop.lab_3_easy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    private final TextFile textFile = new TextFile("output.txt");

    @FXML
    protected void onHelloButtonClick() {
        textFile.writeLine("Строка от JavaFX");
        String content = textFile.readContent();
        welcomeText.setText(content);
    }
}
