module io.jfxdevelop.lab_6_easy {
    requires javafx.controls;
    requires javafx.fxml;

    // ✅ добавляем поддержку JDBC
    requires java.sql;

    opens io.jfxdevelop.lab_6_easy to javafx.fxml;
    exports io.jfxdevelop.lab_6_easy;
}
