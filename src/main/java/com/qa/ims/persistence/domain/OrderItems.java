package com.qa.ims.persistence.domain;

public class OrderItems {
    private long itemID;
    private long quantity;

    public OrderItems(long itemID){
        this.itemID = itemID;
        this.quantity = 1;
    }

    public OrderItems(long itemID, long quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getQuantity() {
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
