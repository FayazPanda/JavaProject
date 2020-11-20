package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter UserID");
		long userID = utils.getLong();
		List<OrderItems> items = new ArrayList<>();
		long itemID = 0,quantity = 0;
		while(true){
			LOGGER.info("Please enter product id");
			itemID = utils.getLong();
			LOGGER.info("Please enter quantity");
			quantity = utils.getLong();
			items.add(new OrderItems(itemID,quantity));
			LOGGER.info("Would you like to add another item?(yes/no)");
			if (utils.getString().equalsIgnoreCase("no")){
				break;
			}
		}
		Order order = orderDAO.create(new Order(userID, items));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		long orderID = utils.getLong();
		LOGGER.info("Please enter UserID");
		long userID = utils.getLong();
		List<OrderItems> items = new ArrayList<>();
		long itemID = 0,quantity = 0;
		while(true){
			LOGGER.info("Please enter product id");
			itemID = utils.getLong();
			LOGGER.info("Please enter quantity");
			quantity = utils.getLong();
			items.add(new OrderItems(itemID,quantity));
			LOGGER.info("Would you like to add another item?(yes/no)");
			if (utils.getString().equalsIgnoreCase("no")){
				break;
			}
		}
		Order order = orderDAO.update(new Order(orderID,userID, items));
		LOGGER.info("Order updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
