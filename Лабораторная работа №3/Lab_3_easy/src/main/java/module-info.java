module io.jfxdevelop.lab_3_easy {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.jfxdevelop.lab_3_easy to javafx.fxml;
    exports io.jfxdevelop.lab_3_easy;
}