package model.dao;

import java.util.List;

import model.entities.Supplier;

public interface SupplierDao {
	
	void insertSupplier (Supplier obj);
	void updateSupplier (Supplier obj);
	void deleteSupplier (Integer id);
	List<Supplier> findSuppliers();	
	List<Supplier> findOne(Integer idSupplier);

}
