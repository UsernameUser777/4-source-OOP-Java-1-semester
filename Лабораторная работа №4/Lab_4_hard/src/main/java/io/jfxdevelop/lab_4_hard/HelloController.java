package io.jfxdevelop.lab_4_hard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane histogramPane;

    @FXML
    protected void onBuildHistogramClick() {
        welcomeText.setText("Гистограмма построена");

        histogramPane.getChildren().clear();

        int[] data = {5, 12, 8, 20, 15};
        String[] labels = {"A", "B", "C", "D", "E"};

        double barWidth = 50;
        double spacing = 20;
        double maxHeight = 200;
        int maxValue = 20;

        for (int i = 0; i < data.length; i++) {
            double scaledHeight = (data[i] / (double) maxValue) * maxHeight;
            double x = i * (barWidth + spacing);
            double y = maxHeight - scaledHeight;

            Rectangle bar = new Rectangle(x, y, barWidth, scaledHeight);
            bar.setFill(Color.DODGERBLUE);

            Text label = new Text(x + 15, maxHeight + 15, labels[i]);
            histogramPane.getChildren().addAll(bar, label);
        }
    }
}
