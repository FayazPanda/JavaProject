package com.qa.ims.persistence.domain;

public class OrderItems {
    private int itemID;
    private int quantity;

    public OrderItems(int itemID){
        this.itemID = itemID;
        this.quantity = 1;
    }

    public OrderItems(int itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity() {
        this.quantity += 1;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0){
            this.quantity = 1;
        }
        this.quantity = quantity;
    }

    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "Item ID:" + itemID + " Quantity:" + quantity;
    }
}
