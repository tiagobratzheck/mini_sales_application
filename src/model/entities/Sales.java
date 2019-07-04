package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sales implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idSales;
	private Double price;
	private Date saleDate;
	private Client idClient;
	private Product idProduct;
	private float quantity;
	private Double total;
	
	
	public Sales() {
		
	}
	
	
	public Sales(Integer idSales, Double price, Date saleDate, Client idClient, Product idProduct, float quantity, Double total) {		
		this.idSales = idSales;
		this.price = price;
		this.saleDate = saleDate;
		this.idClient = idClient;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.total = total;
	}

	public Integer getIdSales() {
		return idSales;
	}

	public void setIdSales(Integer idSales) {
		this.idSales = idSales;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Client getClient() {
		return idClient;
	}

	public void setidClient(Client cli) {
		this.idClient = cli;
	}

	public Product getidProduct() {
		return idProduct;
	}

	public void setProduct(Product prd) {
		this.idProduct = prd;
	}	

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSales == null) ? 0 : idSales.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sales other = (Sales) obj;
		if (idSales == null) {
			if (other.idSales != null)
				return false;
		} else if (!idSales.equals(other.idSales))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sales [idSales=" + idSales + ", price=" + price + ", saleDate=" + saleDate + ", client=" + idClient
				+ ", product=" + idProduct + ", quantity=" + quantity + ", total=" + total + "]";
	}

	
		
}
