package com.qa.ims.controllers;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final long USERID = 1L;
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(2,3));
		final Order created = new Order(USERID,items);

		Mockito.when(utils.getString()).thenReturn(String.valueOf(USERID),String.valueOf(items.get(0).getItemID()),String.valueOf(items.get(0).getQuantity()),"no");
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(1,5));
		items.add(new OrderItems(2,3));
		orders.add(new Order(1L, 1L,items));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		final long USERID = 2L;
		List<OrderItems> items = new ArrayList<>();
		items.add(new OrderItems(3,4));
		final Order created = new Order(USERID,items);
		Order updated = new Order(1L,USERID,items);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(utils.getString()).thenReturn(String.valueOf(USERID),String.valueOf(items.get(0).getItemID()),String.valueOf(items.get(0).getQuantity()),"no");
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
