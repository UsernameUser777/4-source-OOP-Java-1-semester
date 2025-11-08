package io.jfxdevelop.lab_3_easy;

import java.io.*;

public class TextFile {
    private final String filename;

    public TextFile(String filename) {
        this.filename = filename;
    }

    public void writeLine(String text) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(text + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    public String readContent() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
        }
        return content.toString();
    }
}
