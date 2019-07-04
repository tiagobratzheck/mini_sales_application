package model.entities;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idProduct;
	private String nameProduct;
	private Double costProduct;
	private String unit;
	
	
	public Product() {
		
	}

	public Product(Integer idProduct, String nameProduct, Double costProduct, String unit) {		
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.costProduct = costProduct;
		this.unit = unit;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Double getCostProduct() {
		return costProduct;
	}

	public void setCostProduct(Double costProduct) {
		this.costProduct = costProduct;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
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
		Product other = (Product) obj;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [ idProduct = " + idProduct + ", nameProduct = " + nameProduct + ", costProduct = " + costProduct
				+ ", unit = " + unit + " ]";
	}
		

}
