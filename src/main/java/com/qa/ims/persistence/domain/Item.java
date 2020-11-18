package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String name;
	private float value;

	public Item(String name, float value) {
		this.name = name;
		this.value = value;
	}

	public Item(Long id, String name, float value) {
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

	public float getValue() {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Math.signum(value) == 0) {
			if (Math.signum(other.value) != 0)
				return false;
		} else if (value == other.value)
			return false;
		return true;
	}

}
