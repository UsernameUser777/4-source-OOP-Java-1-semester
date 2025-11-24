module io.jfxdevelop.lab_6_hard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens io.jfxdevelop.lab_6_hard to javafx.fxml;
    exports io.jfxdevelop.lab_6_hard;
}
