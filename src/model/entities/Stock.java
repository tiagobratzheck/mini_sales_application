package model.entities;

import java.io.Serializable;

public class Stock implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id_Movement;
	private Product product;	
	private Purchase purchase;	
	private float quantity;	
	private Double total;
	private String lot_number;
	
    public Stock() {
		
	}
    
		
	public Stock(Integer id_Movement, Product product, Purchase purchase, float quantity, Double total, String lot_number) {		
		this.id_Movement = id_Movement;
		this.product = product;		
		this.purchase = purchase;
		this.quantity = quantity;
		this.total = total;
		this.lot_number = lot_number;
	}

	public Integer getId_Movement() {
		return id_Movement;
	}

	public void setId_Movement(Integer id_Movement) {
		this.id_Movement = id_Movement;
	}

	public String getLot_number() {
		return lot_number;
	}

	public void setLot_number(String lot_number) {
		this.lot_number = lot_number;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase prch) {
		this.purchase = prch;
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
		result = prime * result + ((id_Movement == null) ? 0 : id_Movement.hashCode());
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
		Stock other = (Stock) obj;
		if (id_Movement == null) {
			if (other.id_Movement != null)
				return false;
		} else if (!id_Movement.equals(other.id_Movement))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [ id_Movement = " + id_Movement + ", lot_number = " + lot_number + ", quantity = " + quantity
				+ ", product = " + product + ", purchase = " + purchase + " ]";
	}	
	
}
