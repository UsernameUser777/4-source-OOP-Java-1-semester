package io.jfxdevelop.lab_7_1_part_hard.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Order> orders = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
