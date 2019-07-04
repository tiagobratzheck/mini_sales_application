package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.entities.Client;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.Sales;
import model.entities.Stock;
import model.entities.Supplier;

public class InstantiateClasses {
	
	
	public Product instantiateProduct(ResultSet rs) throws SQLException {
		Product prd = new Product();
		prd.setIdProduct(rs.getInt("id_product"));
		prd.setNameProduct(rs.getString("name_product"));
		prd.setCostProduct(rs.getDouble("cost_product"));
		prd.setUnit(rs.getString("unit_product"));
		return prd;
	}
	
	public Product instantiateProduct2(ResultSet rs) throws SQLException {
		Product prd = new Product();
		prd.setIdProduct(rs.getInt("id_product"));
		prd.setNameProduct(rs.getString("name_product"));		
		return prd;
	}
		
	public Purchase instantiatePurchase(ResultSet rs, Product prd, Supplier spl) throws SQLException {
		Purchase prch = new Purchase();
		prch.setIdPurchase(rs.getInt("id_purchase"));
		prch.setQuantity(rs.getFloat("quantity"));
		prch.setDatePurchase(rs.getDate("datePurchase")); 
		prch.setProduct(prd);
		prch.setSupplier(spl);
		prch.setTotal(rs.getDouble("total_purchase"));		
		return prch;
		
	}
	
	public Purchase instantiatePurchase2(ResultSet rs) throws SQLException {
		Purchase prch = new Purchase();
		prch.setIdPurchase(rs.getInt("id_purchase"));	
		prch.setDatePurchase(rs.getDate("datePurchase"));
		return prch;
		
	}
	public Stock instantiateStock(ResultSet rs, Product prd, Purchase prch) throws SQLException {
		Stock stk = new Stock();
		stk.setId_Movement(rs.getInt("id_movement")); 
		stk.setProduct(prd); 	
		stk.setLot_number(rs.getString("lot_number"));
		stk.setPurchase(prch);  
		stk.setQuantity(rs.getFloat("quantity"));  
		stk.setTotal(rs.getDouble("total_stock"));
		return stk;
		
	}
	
	public Stock instantiateStock2(ResultSet rs, Product prd, Integer qts, Double total) throws SQLException {
		Stock stk = new Stock();		
		stk.setProduct(prd);  		 
		stk.setQuantity(qts);  
		stk.setTotal(total);
		return stk;
	}
	
	public Supplier instantiateSupplier(ResultSet rs) throws SQLException {
		Supplier sp = new Supplier();
		sp.setIdSupplier(rs.getInt("id_supplier"));
		sp.setNameSupplier(rs.getString("name_supplier"));
		return sp;
	}
	
	public Client instantiateClient(ResultSet rs) throws SQLException {
		Client cli = new Client();
		cli.setIdClient(rs.getInt("id_client"));
		cli.setName(rs.getString("name_client"));
		return cli;
	}

	public Sales instantiateSales(ResultSet rs, Product prd, Client cli) throws SQLException {
		Sales sale = new Sales();
		sale.setIdSales(rs.getInt("id_sales"));
		sale.setPrice(rs.getDouble("price"));
		sale.setSaleDate(rs.getDate("sale_date"));
		sale.setidClient(cli);
		sale.setProduct(prd);
		sale.setQuantity(rs.getFloat("quantity"));
		sale.setTotal(rs.getDouble("total"));
		return sale;
	}

}
