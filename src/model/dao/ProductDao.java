package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {
	
	void insertProduct (Product obj);
	void updateProduct (Product obj);
	void deleteProduct (Integer id);
	List<Product> findProducts();	
	List<Product> findOneProduct(Integer idProduct);

}
