
package com.kh.spring.board.model.vo;

import java.sql.Date;

public class Product {
	private int productNo;
	private String productName;
	private String productContent;
	private int price;
	private Date createDate;
	
	public Product() {}

	public Product(int productNo, String productName, String productContent, int price, Date createDate) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productContent = productContent;
		this.price = price;
		this.createDate = createDate;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productContent=" + productContent
				+ ", price=" + price + ", createDate=" + createDate + "]";
	}
	
	
}
