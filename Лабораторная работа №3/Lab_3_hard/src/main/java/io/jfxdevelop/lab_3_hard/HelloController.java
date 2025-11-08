package io.jfxdevelop.lab_3_hard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    private final Library library = new Library("library.dat");

    @FXML
    protected void onHelloButtonClick() {
        // Добавляем книгу
        library.addBook(new Book("Мастер и Маргарита", "М. Булгаков", 1967));

        // Сохраняем в файл
        library.saveToFile();

        // Загружаем из файла
        library.loadFromFile();

        // Отображаем список книг
        StringBuilder output = new StringBuilder();
        for (Book book : library.getBooks()) {
            output.append(book.toString()).append("\n");
        }
        welcomeText.setText(output.toString());
    }
}
