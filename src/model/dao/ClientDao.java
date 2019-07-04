package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {
	
	void insertClient (Client obj);
	void updateClient (Client obj);
	void deleteClient (Integer id);
	List<Client> findClients();	
	List<Client> findOneClient(Integer idS);

}