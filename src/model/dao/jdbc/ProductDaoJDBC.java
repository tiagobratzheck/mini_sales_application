package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;


public class ProductDaoJDBC implements ProductDao {
	
	
	private Connection conn;
	
	
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void insertProduct(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO product (name_product, cost_product, unit_product) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
					);			
			st.setString(1, obj.getNameProduct());
			st.setDouble(2, obj.getCostProduct());
			st.setString(3, obj.getUnit());
			st.executeUpdate();					
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void updateProduct(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE product SET name_product = ?, cost_product = ?, unit_product = ? WHERE id_product = ?"
					);
			st.setString(1, obj.getNameProduct());
			st.setDouble(2, obj.getCostProduct());
			st.setString(3, obj.getUnit());
			st.setInt(4, obj.getIdProduct());
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteProduct(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM product WHERE id_product = ?"
					);
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Product> findProducts() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM product ORDER BY name_product"
					);
			rs = st.executeQuery();
			List<Product> prod = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				prod.add(prd);
			}
			return prod;						
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
	public List<Product> findOneProduct(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM product WHERE id_product = ? "
					);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			List<Product> prd = new ArrayList<>();
			
			while(rs.next()) {
				Product pr = ic.instantiateProduct(rs);
				prd.add(pr);
			}
			return prd;						
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