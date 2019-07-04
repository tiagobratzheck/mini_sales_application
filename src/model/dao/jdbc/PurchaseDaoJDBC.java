package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PurchaseDao;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.Supplier;

public class PurchaseDaoJDBC implements PurchaseDao {

	private Connection conn;

	public PurchaseDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertPurchase(Purchase obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO purchase (quantity, datePurchase, id_product, id_supplier, total_purchase) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setFloat(1, obj.getQuantity());
			st.setDate(2, new java.sql.Date(obj.getDatePurchase().getTime()));
			st.setInt(3, obj.getProduct().getIdProduct());
			st.setInt(4, obj.getSupplier().getIdSupplier());
			st.setDouble(5, obj.getTotal());

			int insert = st.executeUpdate();

			if (insert > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdPurchase(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Purchase> listOnePurchase(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT purchase.*, product.id_product, product.name_product, product.cost_product, product.unit_product, "
					+ " supplier.name_supplier"
					+ " FROM purchase"
					+ " INNER JOIN product ON product.id_product = purchase.id_product"
					+ " INNER JOIN supplier ON supplier.id_supplier = purchase.id_supplier"				
					+ " WHERE id_Purchase = ?"
					);
			st.setInt(1, id);			
			
			rs = st.executeQuery();
			List<Purchase> pch = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Supplier sup = ic.instantiateSupplier(rs);
				Purchase prc = ic.instantiatePurchase(rs, prd, sup);
				pch.add(prc);
			}
			return pch;						
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Purchase> listPurchase(Date begin, Date end) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT purchase.*, product.id_product, product.name_product, product.cost_product, product.unit_product, "
							+ " supplier.name_supplier"
							+ " FROM purchase"
							+ " INNER JOIN product ON product.id_product = purchase.id_product"
							+ " INNER JOIN supplier ON supplier.id_supplier = purchase.id_supplier"				
							+ " WHERE datePurchase BETWEEN ? AND ? "
					);
			st.setDate(1, new java.sql.Date(begin.getTime()));
			st.setDate(2, new java.sql.Date(end.getTime()));
			
			rs = st.executeQuery();
			List<Purchase> pch = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Supplier sup = ic.instantiateSupplier(rs);
				Purchase prc = ic.instantiatePurchase(rs, prd, sup);
				pch.add(prc);
			}
			return pch;						
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}	

}