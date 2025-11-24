module io.jfxdevelop.lab_4_special {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.jfxdevelop.lab_4_special to javafx.fxml;
    exports io.jfxdevelop.lab_4_special;
}
