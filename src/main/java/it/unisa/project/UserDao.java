package it.unisa.project;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class UserDao implements IBeanDao<UserDTO>{
	
	
	private static DataSource ds;
	private static final String TABLE_NAME = "Account";

	public UserDao(DataSource ds) {
		this.ds = ds;
	}

	
	@Override
	public synchronized void doSave(UserDTO user) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		String insertSql = "INSER INTO" + TABLE_NAME + "(Nome,Cognome,Email,Pw,DataNascita) VALUES (?,?,?,?,?)";
		
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(insertSql);
			ps.setString(1,user.getNome());
			ps.setString(2,user.getCognome());
			ps.setString(3,user.getEmail());
			ps.setDate(4,(Date) user.getDataNascita());
			ps.executeUpdate();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String deleteSql = "DELETE FROM " + TABLE_NAME + "WHERE CODE = ?";
		
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(deleteSql);
			ps.setInt(1, code);
			result = ps.executeUpdate();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return (result!=0);
	}

	@Override
	public synchronized UserDTO doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Collection<UserDTO> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
