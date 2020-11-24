package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Takes in item details for CRUD functionality
 */
public class ItemController implements CrudController<Item> {

    public static final Logger LOGGER = LogManager.getLogger();

    private final ItemDAO itemDAO;
    private final Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    /**
     * Reads all items to the logger
     */
    @Override
    public List<Item> readAll() {
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(items.toString());
        }
        return items;
    }

    /**
     * Creates a item by taking in user input
     */
    @Override
    public Item create() {
        LOGGER.info("Please enter the product name");
        String name = utils.getString();
        LOGGER.info("Please enter the product value");
        double value = utils.getDouble();
        Item item = itemDAO.create(new Item(name, value));
        LOGGER.info("Item created");
        return item;
    }

    /**
     * Updates an existing item by taking in user input
     */
    @Override
    public Item update() {
        LOGGER.info("Please enter the id of the item you would like to update");
        Long id = utils.getLong();
        LOGGER.info("Please enter the product name");
        String name = utils.getString();
        LOGGER.info("Please enter the product value");
        double value = utils.getDouble();
        Item item = itemDAO.update(new Item(id, name, value));
        LOGGER.info("Customer Updated");
        return item;
    }

    /**
     * Deletes an existing item by the id of the item
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = utils.getLong();
        return itemDAO.delete(id);
    }

}
