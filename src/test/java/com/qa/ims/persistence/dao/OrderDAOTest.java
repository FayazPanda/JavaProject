package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

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
		List<OrderItems> items = new ArrayList<>();
		final Order created = new Order(2L,1L,items);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(1L,5));
		items.add(new OrderItems(2L,3));
		expected.add(new Order(1L,1L, items));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(1L,5));
		items.add(new OrderItems(2L,3));
		assertEquals(new Order(1L, 1L,items), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(1L,5));
		items.add(new OrderItems(2L,3));
		assertEquals(new Order(ID, 1L,items), DAO.readOrder(ID));
	}

	@Test
	public void testUpdate() {
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(1L,5));
		items.add(new OrderItems(2L,3));
		final Order updated = new Order(1L, 2L,items);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
