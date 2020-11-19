package com.qa.ims.persistence.domain;

import java.util.*;

public class Order {

	private Long orderID;
	private Long userID;
	private List<OrderItems> items = new ArrayList<>();

	public Order(Long orderID, Long userID) {
		this.orderID = orderID;
		this.userID = userID;
	}

	public Order(Long orderID, Long userID, List<OrderItems> item) {
		this.orderID = orderID;
		this.userID = userID;
		this.items = item;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public List getItems(){
		return items;
	}

	public void clearItems(){
		items.clear();
	}

	public void addItem(OrderItems item) {
		if(items.contains(item)){
			int x = items.indexOf(item);
			items.get(x).addQuantity();
		} else {
			items.add(item);
		}
	}

	public void addItem(OrderItems item, int quantity) {
		if(items.contains(item)){
			int x = items.indexOf(item);
			items.get(x).addQuantity(quantity);
		} else {
			items.add(item);
		}
	}

	public void delItem(OrderItems item){
		items.remove(item);
	}

	public void delItem(OrderItems item, int quantity) {
		int x = items.indexOf(item);
		if (items.get(x).getQuantity() < quantity){
			items.remove(item);
		} else {
			items.get(x).removeQuantity(quantity);
		}
	}

	@Override
	public String toString() {
		String t = "";
		for (OrderItems item: items){
			t = t + " " + item.getQuantity() + " copies of " + item.getItemID() + ".";
		}
		return "Order ID:" + orderID + " Customer ID:" + userID + t;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

}
