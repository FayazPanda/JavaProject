package com.qa.ims.persistence.dao;

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

public class OrderItemsDAO {

    public static final Logger LOGGER = LogManager.getLogger();

    public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long item_id = resultSet.getLong("item_id");
        int quantity = resultSet.getInt("quantity");
        return new OrderItems(item_id, quantity);
    }

    /**
     * Reads all orders from the database
     *
     * @return A list of orders
     */
    public List<OrderItems> readAllItems(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order_item WHERE order_id=" + id)) {
            List<OrderItems> orderitems = new ArrayList<>();
            while (resultSet.next()) {
                orderitems.add(modelFromResultSet(resultSet));
            }
            return orderitems;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public OrderItems readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order_item ORDER BY order_id DESC LIMIT 1")) {
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
     * @param orderItems - takes in a order object. id will be ignored
     */
    public OrderItems create(long id, OrderItems orderItems) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO order_item(order_id,item_id,quantity) VALUES(" + id + "," + orderItems.getItemID()
                    + "," + orderItems.getQuantity() + ")");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public OrderItems readOrderItems(long orderID, long itemID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order_item WHERE order_id =" + orderID+" AND item_id="+itemID);) {
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
     * @param orderItems - takes in a order object, the id field will be used to
     *              update that order in the database
     * @return
     */
    public OrderItems updateQuantity(long id,OrderItems orderItems) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            if (orderItems.getQuantity() == 0){
                statement.executeUpdate("DELETE FROM order_item WHERE order_id =" + id+" AND item_id="+orderItems.getItemID());
                return null;
            } else {
                statement.executeUpdate("UPDATE order_item SET quantity ='" + orderItems.getQuantity() + "' WHERE order_id =" + id+" AND item_id="+orderItems.getItemID());
                return readOrderItems(id, orderItems.getItemID());
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a order in the database
     *
     * @param orderID - id of the order
     */


    public int delete(long orderID, long itemID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate("DELETE FROM order_item WHERE order_id =" +orderID+" AND item_id="+itemID);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}
