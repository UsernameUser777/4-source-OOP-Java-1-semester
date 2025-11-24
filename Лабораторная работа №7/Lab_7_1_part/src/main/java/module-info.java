module io.jfxdevelop.lab_7_1_part {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.jfxdevelop.lab_7_1_part to javafx.fxml;
    exports io.jfxdevelop.lab_7_1_part;
}
