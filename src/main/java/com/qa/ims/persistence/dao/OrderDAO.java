package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();
    OrderItemsDAO oi = new OrderItemsDAO();
    private List<OrderItems> items = new ArrayList<>();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long userID = resultSet.getLong("user_id");
        Long orderID = resultSet.getLong("order_id");
        items.clear();
        items = oi.readAllItems(orderID);
        return new Order(orderID, userID, items);
    }

    /**
     * Reads all orders from the database
     *
     * @return A list of orders
     */
    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from `order`")) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `order` ORDER BY order_id DESC LIMIT 1")) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a order in the database
     *
     * @param order - takes in a order object. id will be ignored
     */
    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `order`(user_id) values(" + order.getUserID() + ")");
            Order newOrder = readLatest();
            for (int i = 0; i < order.getItems().size(); i++) {
                oi.create(newOrder.getOrderID(), order.getOrderItems(i));
            }
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order readOrder(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `order` where order_id = " + id)) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Updates a order in the database
     *
     * @param order - takes in a order object, the id field will be used to
     *              update that order in the database
     * @return
     */
    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE `order` SET user_id=" + order.getUserID() + " WHERE order_id =" + order.getOrderID());
            for (int i = 0; i < order.getItems().size(); i++) {
                oi.updateQuantity(order.getOrderID(), order.getOrderItems(i));
            }
            return readOrder(order.getOrderID());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a order in the database
     *
     * @param id - id of the order
     */
    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM order_item WHERE order_id = " + id);
            return statement.executeUpdate("delete from `order` where order_id = " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public float calculateOrder(long id) {
        Order order = readOrder(id);
        ItemDAO itemDAO = new ItemDAO();
        float cost = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            cost += (order.getOrderItems(i).getQuantity() * itemDAO.readItem(order.getOrderItems(i).getItemID()).getValue());
        }
        return cost;
    }

}
