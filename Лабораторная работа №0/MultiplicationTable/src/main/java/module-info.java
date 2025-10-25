module io.jfxdevelop.multiplicationtable {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.jfxdevelop.multiplicationtable to javafx.fxml;
    exports io.jfxdevelop.multiplicationtable;
}