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
import model.dao.SupplierDao;
import model.entities.Supplier;

public class SupplierDaoJDBC implements SupplierDao {
	
	
	private Connection conn;
	
	
	public SupplierDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void insertSupplier(Supplier obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO supplier (name_supplier) VALUES (?)", Statement.RETURN_GENERATED_KEYS
					);			
			st.setString(1, obj.getNameSupplier());			
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
	public void updateSupplier(Supplier obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE supplier SET name_supplier = ? WHERE id_supplier = ?"
					);
			st.setString(1, obj.getNameSupplier());
			st.setInt(2, obj.getIdSupplier());	
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
	public void deleteSupplier(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM supplier WHERE id_supplier = ?"
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
	public List<Supplier> findSuppliers() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM supplier ORDER BY name_supplier"
					);
			rs = st.executeQuery();
			List<Supplier> suppliers = new ArrayList<>();
			
			while(rs.next()) {
				Supplier sup = ic.instantiateSupplier(rs);
				suppliers.add(sup);
			}
			return suppliers;						
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
	public List<Supplier> findOne(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM supplier WHERE id_supplier = ? "
					);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			List<Supplier> suppliers = new ArrayList<>();
			
			while(rs.next()) {
				Supplier sup = ic.instantiateSupplier(rs);
				suppliers.add(sup);
			}
			return suppliers;						
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
