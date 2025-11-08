module io.jfxdevelop.lab_2_easy {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.jfxdevelop.lab_2_easy to javafx.fxml;
    exports io.jfxdevelop.lab_2_easy;
}