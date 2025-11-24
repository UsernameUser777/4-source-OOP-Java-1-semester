package io.jfxdevelop.lab_7_2_part;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

    public static void main(String[] args) {
        final int PORT = 8080;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    // Чтение запроса (можно игнорировать содержимое)
                    String line;
                    while (!(line = in.readLine()).isEmpty()) {
                        System.out.println(line);
                    }

                    // Ответ: заголовки + HTML
                    String html = """
                            <html>
                            <head><title>Лабораторная 7.2</title></head>
                            <body>
                                <h1>Привет, Станислав!</h1>
                                <p>Это статичная HTML-страница от минимального HTTP-сервера.</p>
                            </body>
                            </html>
                            """;

                    out.write("HTTP/1.1 200 OK\r\n");
                    out.write("Content-Type: text/html; charset=UTF-8\r\n");
                    out.write("Content-Length: " + html.getBytes().length + "\r\n");
                    out.write("\r\n");
                    out.write(html);
                    out.flush();

                } catch (IOException e) {
                    System.err.println("Ошибка при обработке клиента: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Не удалось запустить сервер: " + e.getMessage());
        }
    }
}
