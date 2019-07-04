package model.entities;

import java.io.Serializable;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idClient;
	private String name;
	
	
	public Client() {
		
	}
	
	public Client(Integer idClient, String name) {
		this.idClient = idClient;
		this.name = name;
	}

	
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
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
		Client other = (Client) obj;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [ idClient = " + idClient + ", name = " + name + " ]";
	}	
	
}


