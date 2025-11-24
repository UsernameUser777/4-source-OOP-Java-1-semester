package io.jfxdevelop.lab_6_hard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {

    // Students UI
    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> studentNameCol;
    @FXML private TableColumn<Student, Integer> studentAgeCol;
    @FXML private TextField studentNameField;
    @FXML private TextField studentAgeField;
    @FXML private Button addStudentBtn;
    @FXML private Button deleteStudentBtn;

    // Courses UI
    @FXML private TableView<Course> coursesTable;
    @FXML private TableColumn<Course, String> courseTitleCol;
    @FXML private TableColumn<Course, Integer> courseCreditsCol;
    @FXML private TextField courseTitleField;
    @FXML private TextField courseCreditsField;
    @FXML private Button addCourseBtn;
    @FXML private Button deleteCourseBtn;

    // Enrollment UI
    @FXML private Button enrollBtn;
    @FXML private Button unenrollBtn;

    // Lists
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();

    // DB
    private Connection conn;
    private StudentsDAO studentsDAO;
    private CoursesDAO coursesDAO;
    private EnrollmentDAO enrollmentDAO;

    public void initialize() {
        // Connect DB
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university_complex", "root", "rootroot"
            );
            studentsDAO = new StudentsDAO(conn);
            coursesDAO = new CoursesDAO(conn);
            enrollmentDAO = new EnrollmentDAO(conn);
        } catch (SQLException e) {
            showError("Ошибка подключения к БД: " + e.getMessage());
            return;
        }

        // Bind tables
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        studentsTable.setItems(students);

        courseTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        courseCreditsCol.setCellValueFactory(new PropertyValueFactory<>("credits"));
        coursesTable.setItems(courses);

        // Load initial data
        reloadStudents();
        reloadCourses();
    }

    private void reloadStudents() {
        students.clear();
        try {
            students.addAll(studentsDAO.getAll());
        } catch (SQLException e) {
            showError("Ошибка загрузки студентов: " + e.getMessage());
        }
    }

    private void reloadCourses() {
        courses.clear();
        try {
            courses.addAll(coursesDAO.getAll());
        } catch (SQLException e) {
            showError("Ошибка загрузки курсов: " + e.getMessage());
        }
    }

    @FXML
    private void onAddStudent() {
        String name = studentNameField.getText();
        int age;
        try {
            age = Integer.parseInt(studentAgeField.getText());
        } catch (NumberFormatException ex) {
            showError("Возраст должен быть числом.");
            return;
        }
        try {
            studentsDAO.insert(name, age);
            studentNameField.clear(); studentAgeField.clear();
            reloadStudents();
        } catch (SQLException e) {
            showError("Ошибка добавления студента: " + e.getMessage());
        }
    }

    @FXML
    private void onDeleteStudent() {
        Student s = studentsTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            showError("Выберите студента для удаления.");
            return;
        }
        try {
            studentsDAO.delete(s.getId());
            reloadStudents();
        } catch (SQLException e) {
            showError("Ошибка удаления студента: " + e.getMessage());
        }
    }

    @FXML
    private void onAddCourse() {
        String title = courseTitleField.getText();
        int credits;
        try {
            credits = Integer.parseInt(courseCreditsField.getText());
        } catch (NumberFormatException ex) {
            showError("Кредиты должны быть числом.");
            return;
        }
        try {
            coursesDAO.insert(title, credits);
            courseTitleField.clear(); courseCreditsField.clear();
            reloadCourses();
        } catch (SQLException e) {
            showError("Ошибка добавления курса: " + e.getMessage());
        }
    }

    @FXML
    private void onDeleteCourse() {
        Course c = coursesTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            showError("Выберите курс для удаления.");
            return;
        }
        try {
            coursesDAO.delete(c.getId());
            reloadCourses();
        } catch (SQLException e) {
            showError("Ошибка удаления курса: " + e.getMessage());
        }
    }

    @FXML
    private void onEnroll() {
        Student s = studentsTable.getSelectionModel().getSelectedItem();
        Course c = coursesTable.getSelectionModel().getSelectedItem();
        if (s == null || c == null) {
            showError("Выберите студента и курс для записи.");
            return;
        }
        try {
            enrollmentDAO.enroll(s.getId(), c.getId());
            info("Студент записан на курс.");
        } catch (SQLException e) {
            showError("Ошибка записи на курс: " + e.getMessage());
        }
    }

    @FXML
    private void onUnenroll() {
        Student s = studentsTable.getSelectionModel().getSelectedItem();
        Course c = coursesTable.getSelectionModel().getSelectedItem();
        if (s == null || c == null) {
            showError("Выберите студента и курс для отписки.");
            return;
        }
        try {
            enrollmentDAO.unenroll(s.getId(), c.getId());
            info("Студент отписан от курса.");
        } catch (SQLException e) {
            showError("Ошибка отписки от курса: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    private void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}
