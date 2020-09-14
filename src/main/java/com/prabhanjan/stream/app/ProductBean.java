package com.prabhanjan.stream.app;

public class ProductBean {
	private Integer price;
	private String name;

	public ProductBean(Integer price, String name) {
		super();
		this.price = price;
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductBean [price=" + price + ", name=" + name + "]";
	}

}
