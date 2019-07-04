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
import model.dao.StockDao;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.Stock;
import model.entities.Supplier;

public class StockDaoJDBC implements StockDao {
	
	/*
	 * Connection to data base;
	 */

	private Connection conn;

	public StockDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.dao.StockDao#insertInStock(model.entities.Stock)
	 */

	@Override
	public void insertInStock(Stock obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO stock (id_product, id_purchase, quantity, total_stock, lot_number) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getProduct().getIdProduct());			
			st.setInt(2, obj.getPurchase().getIdPurchase());
			st.setFloat(3, obj.getQuantity());
			st.setDouble(4, obj.getTotal());
			st.setString(5, obj.getLot_number());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void removeFromStock(Double quantity, String lot_number) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE stock SET quantity = ? WHERE lot_number = ?"
					);
			st.setDouble(1, quantity);
			st.setString(2, lot_number);	
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}
	
    /*
     * OLD FUNCTION
     * 
	@Override
	public List<Stock> returnStock() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT stock.*, product.name_product, product.cost_product,"
					+ " purchase.id_purchase, supplier.id_supplier"
					+ " FROM stock"
					+ " INNER JOIN product ON product.id_product = stock.id_product"
					+ " INNER JOIN purchase ON purchase.id_purchase = stock.id_purchase"
					+ " INNER JOIN supplier ON supplier.id_supplier = purchase.id_supplier"
					);					
			rs = st.executeQuery();
			List<Stock> stk = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Supplier spl = ic.instantiateSupplier(rs);
				Purchase prch = ic.instantiatePurchase(rs, prd, spl);
				Stock stock = ic.instantiateStock(rs, prd, prch);
				stk.add(stock);
			}
			return stk;						
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}	
	*/
	
	/*
	 * (non-Javadoc)
	 * @see model.dao.StockDao#checkProductInStock(java.lang.Integer)
	 */

	@Override
	public List<Stock> checkProductInStock(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					" SELECT product.id_product, product.name_product," 
					+ " sum(stock.quantity) AS qtdtotal, sum(stock.total_stock) AS total FROM product"
				    + " INNER JOIN stock ON (stock.id_product = product.id_product)"
				    + " WHERE stock.id_product = ?"
					);		
			st.setInt(1, id);
			rs = st.executeQuery();
			List<Stock> stk = new ArrayList<>();
			Integer qts = 0;
			Double total = 0.0;
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct2(rs);	
				qts = rs.getInt("qtdtotal");
				total = rs.getDouble("total");
				Stock stock = ic.instantiateStock2(rs, prd, qts, total);
				stk.add(stock);
			}
			return stk;						
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
	 * @see model.dao.StockDao#totalInStock()
	 */

	@Override
	public Double totalInStock() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT sum(total_stock) AS sumtotal FROM stock "
					);		
			
			rs = st.executeQuery();			
			Double total = 0.0;
			
			while(rs.next()) {
				total = rs.getDouble("sumtotal");
			}	
			return total;										
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
	 * @see model.dao.StockDao#returnProduct(java.lang.Integer)
	 */

	@Override
	public List<Stock> returnProduct(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT stock.*, product.id_product, product.name_product, product.cost_product, product.unit_product,"
					+ " purchase.id_purchase, purchase.datePurchase, supplier.id_supplier, supplier.name_supplier"
					+ " FROM stock"
					+ " INNER JOIN product ON product.id_product = stock.id_product"
					+ " INNER JOIN purchase ON purchase.id_purchase = stock.id_purchase"
					+ " INNER JOIN supplier ON supplier.id_supplier = purchase.id_supplier"
					+ " WHERE stock.id_product = ?"
					);	
			st.setInt(1, id);
			rs = st.executeQuery();
			List<Stock> stk = new ArrayList<>();
			
			while(rs.next()) {
				Product prd = ic.instantiateProduct(rs);
				Supplier spl = ic.instantiateSupplier(rs);
				Purchase prch = ic.instantiatePurchase2(rs);
				Stock stock = ic.instantiateStock(rs, prd, prch);
				stk.add(stock);
			}
			return stk;						
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
	 * @see model.dao.StockDao#totalQtdLot(java.lang.String)
	 */
	
	@Override
	public Double totalQtdLot(String lot_number) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT quantity AS qtd FROM stock WHERE lot_number = ? "
					);		
			
			st.setString(1, lot_number);
			rs = st.executeQuery();			
			Double total = 0.0;
			
			while(rs.next()) {
				total = rs.getDouble("qtd");
			}	
			return total;										
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