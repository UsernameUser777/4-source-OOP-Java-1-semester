package io.jfxdevelop.multiplicationtable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label l;

    @FXML
    private TextField tf;

    private int result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        l.setText(multiplicationGenerator());
    }

    private String multiplicationGenerator() {
        int a, b;
        final Random random = new Random();
        do {
            a = random.nextInt(10);
            b = random.nextInt(10);
        } while (a == 0 || b == 0 || a == 1 || b == 1);
        this.result = a * b;
        return a + " * " + b;
    }

    private void alertWindow(final String s, final String s1, final String s2) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(s2);
        alert.setHeaderText(s);
        alert.setContentText(s1);
        alert.showAndWait();
    }

    @FXML
    protected void onHelloButtonClick() {
        int us;
        try {
            us = Integer.parseInt(tf.getText());
        } catch (NumberFormatException e) {
            alertWindow("Ошибка", "Введите число!", "Внимание");
            tf.requestFocus();
            return;
        }

        if (us == result) {
            alertWindow("ВЕРНО!", l.getText() + " = " + us, "Результат");
        } else {
            alertWindow("НЕВЕРНО", "Верный ответ:\n" + l.getText() + " = " + result, "Результат");
        }

        l.setText(multiplicationGenerator());
        tf.setText("");
        tf.requestFocus();
    }
}
