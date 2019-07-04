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
import model.dao.ClientDao;
import model.entities.Client;

public class ClientDaoJDBC implements ClientDao {
	
	
	private Connection conn;
	
	
	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void insertClient(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO client (name_client) VALUES (?)", Statement.RETURN_GENERATED_KEYS
					);			
			st.setString(1, obj.getName());			
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
	public void updateClient(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE client SET name_client = ? WHERE id_client = ?"
					);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getIdClient());	
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
	public void deleteClient(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM client WHERE id_client = ?"
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
	public List<Client> findClients() {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM client ORDER BY name_client"
					);
			rs = st.executeQuery();
			List<Client> cli = new ArrayList<>();
			
			while(rs.next()) {
				Client client = ic.instantiateClient(rs);
				cli.add(client);
			}
			return cli;						
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
	public List<Client> findOneClient(Integer id) {
		PreparedStatement st  = null;
		ResultSet rs = null;
		InstantiateClasses ic = new InstantiateClasses();
		try {
			st = conn.prepareStatement(
					"SELECT * FROM client WHERE id_client = ? "
					);
			st.setInt(1, id);
			
			rs = st.executeQuery();
			List<Client> client = new ArrayList<>();
			
			while(rs.next()) {
				Client cli = ic.instantiateClient(rs);
				client.add(cli);
			}
			return client;						
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