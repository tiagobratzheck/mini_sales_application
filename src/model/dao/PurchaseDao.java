package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Purchase;

public interface PurchaseDao {
	
	void insertPurchase(Purchase obj);
	List<Purchase> listOnePurchase(Integer id);
	List<Purchase> listPurchase(Date begin, Date end);	

}
