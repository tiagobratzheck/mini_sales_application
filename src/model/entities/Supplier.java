package model.entities;

import java.io.Serializable;

public class Supplier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idSupplier;
	private String nameSupplier;
	
	
	public Supplier() {
		
	}

	public Supplier(Integer idSupplier, String nameSupplier) {
		super();
		this.idSupplier = idSupplier;
		this.nameSupplier = nameSupplier;
	}

	public Integer getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Integer idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getNameSupplier() {
		return nameSupplier;
	}

	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSupplier == null) ? 0 : idSupplier.hashCode());
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
		Supplier other = (Supplier) obj;
		if (idSupplier == null) {
			if (other.idSupplier != null)
				return false;
		} else if (!idSupplier.equals(other.idSupplier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Supplier [ idSupplier = " + idSupplier + ", nameSupplier = " + nameSupplier + " ]";
	}

		

}
