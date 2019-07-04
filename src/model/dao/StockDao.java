package model.dao;

import java.util.List;

import model.entities.Stock;

public interface StockDao {
	
	void insertInStock(Stock obj);
	void removeFromStock(Double new_value, String lot_number);
	Double totalInStock();
	Double totalQtdLot(String lot_number);
	//List<Stock> returnStock();
	List<Stock> checkProductInStock(Integer id);
	List<Stock> returnProduct(Integer id);
	

}
