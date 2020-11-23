package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderItemsDAOTest {

	private final OrderItemsDAO DAO = new OrderItemsDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "pass");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final OrderItems created = new OrderItems(2,4);
		assertEquals(created, DAO.create(2,created));
	}

	@Test
	public void testReadAll() {
		List<OrderItems> expected = new ArrayList<>();
		expected.add(new OrderItems(1,5));
		assertEquals(expected, DAO.readAllItems(1));
	}

	@Test
	public void testReadLatest() {
		assertEquals(new OrderItems(2,3), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long orderID = 1L;
		final long itemID = 1L;
		assertEquals(new OrderItems(itemID,5), DAO.readOrderItems(orderID,itemID));
	}

	@Test
	public void testUpdate() {
		final OrderItems updated = new OrderItems(1L,1);
		assertEquals(updated, DAO.updateQuantity(1L,updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1L,1L));
	}
}
