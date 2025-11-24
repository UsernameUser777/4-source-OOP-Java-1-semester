package io.jfxdevelop.lab_4_special;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.*;

public class HelloController {

    @FXML private Canvas gameCanvas;
    @FXML private Label scoreLabel;

    private final int gridSize = 20;   // поле 20x20
    private final int blockSize = 20;  // размер клетки

    private List<Point> snake;
    private Point food;
    private Direction direction = Direction.RIGHT;
    private boolean running = false;

    private int score = 0;

    private AnimationTimer timer;
    private long lastUpdate = 0;
    private long stepDelay = 200_000_000; // 200 мс

    @FXML
    public void startGame() {
        initGame();
        runLoop();
    }

    @FXML
    public void restartGame() {
        initGame();
        runLoop();
    }

    private void initGame() {
        snake = new ArrayList<>();
        snake.add(new Point(5, 5));
        direction = Direction.RIGHT;
        spawnFood();
        score = 0;
        updateScore();
        running = true;
    }

    private void runLoop() {
        // Всегда держим фокус на Canvas
        gameCanvas.requestFocus();
        gameCanvas.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            switch (code) {
                case UP -> { if (direction != Direction.DOWN) direction = Direction.UP; }
                case DOWN -> { if (direction != Direction.UP) direction = Direction.DOWN; }
                case LEFT -> { if (direction != Direction.RIGHT) direction = Direction.LEFT; }
                case RIGHT -> { if (direction != Direction.LEFT) direction = Direction.RIGHT; }
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= stepDelay) {
                    update();
                    draw();
                    lastUpdate = now;

                    // если фокус потерян — вернуть на Canvas
                    if (!gameCanvas.isFocused()) {
                        gameCanvas.requestFocus();
                    }
                }
            }
        };
        timer.start();
    }

    private void update() {
        Point head = snake.get(0);
        Point newHead = switch (direction) {
            case UP -> new Point(head.x, head.y - 1);
            case DOWN -> new Point(head.x, head.y + 1);
            case LEFT -> new Point(head.x - 1, head.y);
            case RIGHT -> new Point(head.x + 1, head.y);
        };

        // Проверка выхода за границы или столкновения
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= gridSize || newHead.y >= gridSize || snake.contains(newHead)) {
            running = false;
            timer.stop();
            return;
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            score += 10;
            updateScore();
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void draw() {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gc.setFill(Color.RED);
        gc.fillOval(food.x * blockSize, food.y * blockSize, blockSize, blockSize);

        gc.setFill(Color.GREEN);
        for (Point p : snake) {
            gc.fillRect(p.x * blockSize, p.y * blockSize, blockSize, blockSize);
        }

        if (!running) {
            gc.setFill(Color.WHITE);
            gc.fillText("Game Over", 160, 200);
        }
    }

    private void spawnFood() {
        Random rand = new Random();
        Point p;
        do {
            p = new Point(rand.nextInt(gridSize), rand.nextInt(gridSize));
        } while (snake.contains(p));
        food = p;
    }

    private void updateScore() {
        scoreLabel.setText("Счёт: " + score);
    }

    private static class Point {
        final int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point other)) return false;
            return x == other.x && y == other.y;
        }
        @Override public int hashCode() { return Objects.hash(x, y); }
    }

    private enum Direction { UP, DOWN, LEFT, RIGHT }
}
