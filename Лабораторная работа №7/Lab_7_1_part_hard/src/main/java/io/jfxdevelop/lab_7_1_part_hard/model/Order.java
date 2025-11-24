package io.jfxdevelop.lab_7_1_part_hard.model;

public class Order {
    private int id;
    private String description;

    public Order(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
