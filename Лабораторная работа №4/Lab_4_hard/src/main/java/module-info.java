module io.jfxdevelop.lab_4_hard {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.jfxdevelop.lab_4_hard to javafx.fxml;
    exports io.jfxdevelop.lab_4_hard;
}
