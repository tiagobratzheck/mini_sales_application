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
import model.dao.SalesDao;
import model.entities.Client;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.Sales;

public class SalesDaoJDBC implements SalesDao {
	
	private Connection conn;

	public SalesDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/*
	 * (non-Javadoc)
	 * @see model.dao.SalesDao#insertSales(model.entities.Sales)
	 */
	@Override
	public void insertSales(Sales obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO sales (price, sale_date, id_client, id_product, quantity, total) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setDouble(1, obj.getPrice());
			st.setDate(2, new java.sql.Date(obj.getSaleDate().getTime()));
			st.setInt(3, obj.getClient().getIdClient());
			st.setInt(4, obj.getidProduct().getIdProduct());
			st.setFloat(5, obj.getQuantity());
			st.setDouble(6, obj.getTotal());			
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see model.dao.SalesDao#listOnesale(java.lang.Integer)
	 */
	@Override
	public List<Sales> listOnesale(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT sales.*, product.id_product, product.name_product, product.cost_product, product.unit_product, "
					+ " client.id_client, client.name_client"
					+ " FROM sales"
					+ " INNER JOIN product ON product.id_product = sales.id_product"
					+ " INNER JOIN client ON client.id_client = sales.id_client"				
					+ " WHERE id_sales = ?"
					);
			st.setInt(1, id);			
			
			rs = st.executeQuery();
			List<Sales> slc = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Client cli = ic.instantiateClient(rs);
				Sales sales = ic.instantiateSales(rs, prd, cli);
				slc.add(sales);
			}
			return slc;						
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see model.dao.SalesDao#listSales(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Sales> listSales(Date begin, Date end) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT sales.*, product.id_product, product.name_product, product.cost_product, product.unit_product, "
					+ " client.id_client, client.name_client"
					+ " FROM sales"
					+ " INNER JOIN product ON product.id_product = sales.id_product"
					+ " INNER JOIN client ON client.id_client = sales.id_client"				
					+ " WHERE sale_date BETWEEN ? AND ?"
					);
			st.setDate(1, new java.sql.Date(begin.getTime()));
			st.setDate(2, new java.sql.Date(end.getTime()));
			
			rs = st.executeQuery();
			List<Sales> slc = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Client cli = ic.instantiateClient(rs);
				Sales sales = ic.instantiateSales(rs, prd, cli);
				slc.add(sales);
			}
			return slc;						
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see model.dao.SalesDao#listClientSales(java.lang.Integer)
	 */
	@Override
	public List<Sales> listClientSales(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT sales.*, product.id_product, product.name_product, product.cost_product, product.unit_product, "
					+ " client.id_client, client.name_client"
					+ " FROM sales"
					+ " INNER JOIN product ON product.id_product = sales.id_product"
					+ " INNER JOIN client ON client.id_client = sales.id_client"				
					+ " WHERE sales.id_client = ?"
					);
			st.setInt(1, id);			
			
			rs = st.executeQuery();
			List<Sales> slc = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Client cli = ic.instantiateClient(rs);
				Sales sales = ic.instantiateSales(rs, prd, cli);
				slc.add(sales);
			}
			return slc;						
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
