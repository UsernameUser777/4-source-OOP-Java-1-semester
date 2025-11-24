package io.jfxdevelop.lab_6_easy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Integer> ageColumn;
    @FXML private TableColumn<Student, String> groupColumn;

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField groupField;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private Connection conn;

    public void initialize() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university", "root", "rootroot"
            );
        } catch (SQLException e) {
            showError("Ошибка подключения к БД: " + e.getMessage());
            return;
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        studentTable.setItems(studentList);
        loadStudents();
    }

    private void loadStudents() {
        studentList.clear();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                studentList.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("group_name")
                ));
            }
        } catch (SQLException e) {
            showError("Ошибка загрузки студентов: " + e.getMessage());
        }
    }

    @FXML
    protected void onAddStudent() {
        String name = nameField.getText();
        String group = groupField.getText();
        int age;

        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            showError("Возраст должен быть числом.");
            return;
        }

        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO students (name, age, group_name) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, group);
            ps.executeUpdate();
            loadStudents();
            nameField.clear(); ageField.clear(); groupField.clear();
        } catch (SQLException e) {
            showError("Ошибка добавления студента: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите студента для удаления.");
            return;
        }

        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM students WHERE id = ?")) {
            ps.setInt(1, selected.getId());
            ps.executeUpdate();
            loadStudents();
        } catch (SQLException e) {
            showError("Ошибка удаления студента: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
