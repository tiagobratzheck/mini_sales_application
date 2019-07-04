package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idPurchase;
	private float quantity;
	private Date datePurchase;
	private Product product;
	private Supplier supplier;
	private Double total;
	
	
	public Purchase() {
		
	}

	public Purchase(Integer idPurchase, float quantity, Date datePurchase, Product product2, Supplier supplier2, Double total) {		
		this.idPurchase = idPurchase;
		this.quantity = quantity;
		this.datePurchase = datePurchase;
		this.product = product2;
		this.supplier = supplier2;
		this.total = total;
	}

	public Integer getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Integer idPurchase) {
		this.idPurchase = idPurchase;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Date getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
		result = prime * result + ((idPurchase == null) ? 0 : idPurchase.hashCode());
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
		Purchase other = (Purchase) obj;
		if (idPurchase == null) {
			if (other.idPurchase != null)
				return false;
		} else if (!idPurchase.equals(other.idPurchase))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase [ idPurchase = " + idPurchase + ", quantity = " + quantity + ", datePurchase = " + datePurchase
				+ ", product = " + product + ", supplier = " + supplier + " ]";
	}
		
}

