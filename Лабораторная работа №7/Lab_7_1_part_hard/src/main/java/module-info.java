module io.jfxdevelop.lab_7_1_part_hard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens io.jfxdevelop.lab_7_1_part_hard to javafx.fxml;
    exports io.jfxdevelop.lab_7_1_part_hard;
    exports io.jfxdevelop.lab_7_1_part_hard.model;
    exports io.jfxdevelop.lab_7_1_part_hard.db;
}
