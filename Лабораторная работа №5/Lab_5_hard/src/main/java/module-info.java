module io.jfxdevelop.lab_5_hard {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.jfxdevelop.lab_5_hard to javafx.fxml;
    exports io.jfxdevelop.lab_5_hard;
}
