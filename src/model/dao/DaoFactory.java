package model.dao;

import db.DB;
import model.dao.jdbc.ClientDaoJDBC;
import model.dao.jdbc.ProductDaoJDBC;
import model.dao.jdbc.PurchaseDaoJDBC;
import model.dao.jdbc.SalesDaoJDBC;
import model.dao.jdbc.StockDaoJDBC;
import model.dao.jdbc.SupplierDaoJDBC;

public class DaoFactory {
	
	public static SupplierDao createSupplierDao() {
		return new SupplierDaoJDBC (DB.getConnection());
	}
	
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC (DB.getConnection());
	}
	
	public static ProductDao createProductDao() {
		return new ProductDaoJDBC (DB.getConnection());
	}
	
	public static PurchaseDao createPurchaseDao() {
		return new PurchaseDaoJDBC (DB.getConnection());
	}
	
	public static StockDao createStockDao() {
		return new StockDaoJDBC (DB.getConnection());
	}
	
	public static SalesDao createSalesDao() {
		return new SalesDaoJDBC (DB.getConnection());
	}

}
