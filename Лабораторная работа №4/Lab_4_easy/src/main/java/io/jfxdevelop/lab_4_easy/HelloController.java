package io.jfxdevelop.lab_4_easy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane drawingPane;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Рисуем фигуры...");

        // очищаем Pane перед рисованием
        drawingPane.getChildren().clear();

        // Круг (красный)
        Circle circle = new Circle(60, 60, 50);
        circle.setFill(Color.RED);

        // Квадрат (синий)
        Rectangle square = new Rectangle(150, 30, 100, 100);
        square.setFill(Color.BLUE);

        // Треугольник (зелёный)
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                300.0, 30.0,
                350.0, 130.0,
                250.0, 130.0
        );
        triangle.setFill(Color.GREEN);

        drawingPane.getChildren().addAll(circle, square, triangle);
    }
}
