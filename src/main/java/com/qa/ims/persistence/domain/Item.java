package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

    private Long id;
    private String name;
    private double value;

    public Item(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public Item(Long id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ID:" + id + " Item Name:" + name + " Value:£" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.value, value) == 0 &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

}
