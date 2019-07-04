package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Sales;

public interface SalesDao {
	
	void insertSales(Sales obj);
	List<Sales> listOnesale(Integer id);
	List<Sales> listSales(Date begin, Date end);	
	List<Sales> listClientSales (Integer id);


}
