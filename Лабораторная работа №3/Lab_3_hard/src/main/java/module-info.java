module io.jfxdevelop.lab_3_hard {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.jfxdevelop.lab_3_hard to javafx.fxml;
    exports io.jfxdevelop.lab_3_hard;
}