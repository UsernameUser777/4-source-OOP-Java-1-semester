package io.jfxdevelop.lab_7_1_part_hard;

import io.jfxdevelop.lab_7_1_part_hard.db.Database;
import io.jfxdevelop.lab_7_1_part_hard.model.Order;
import io.jfxdevelop.lab_7_1_part_hard.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField userIdField;

    @FXML
    private ListView<String> ordersList;

    @FXML
    protected void loadOrders() {
        ordersList.getItems().clear();
        try {
            int userId = Integer.parseInt(userIdField.getText());
            User user = Database.getUserWithOrders(userId);
            if (user != null) {
                for (Order order : user.getOrders()) {
                    ordersList.getItems().add("Заказ #" + order.getId() + ": " + order.getDescription());
                }
            } else {
                ordersList.getItems().add("Пользователь не найден.");
            }
        } catch (NumberFormatException e) {
            ordersList.getItems().add("Введите корректный ID.");
        }
    }
}
