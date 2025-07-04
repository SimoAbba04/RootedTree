package it.unisa.project;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.project.UserDTO.Ruolo;

public class UserDAO implements IBeanDao<UserDTO> {

	private static DataSource ds;
	private static final String TABLE_NAME = "Account";

	public UserDAO(DataSource ds) {
		UserDAO.ds = ds;
	}

	@Override
	public synchronized void doSave(UserDTO user) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		String insertSql = "INSERT INTO " + TABLE_NAME + "(Nome,Cognome,Email,Pw,DataNascita) VALUES (?,?,?,?,?)";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(insertSql);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getCognome());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setDate(5, (Date) user.getDataNascita());
			ps.executeUpdate();
		} finally {
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
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized UserDTO doRetrieveByKey(int code) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		UserDTO user = new UserDTO();

		String selectSql = "SELECT FROM " + TABLE_NAME + "WHERE CODE = ?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(selectSql);
			ps.setInt(1, code);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setID(rs.getInt("IdAccount"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setDataNascita(rs.getDate("DataNascita"));
				user.setRuolo(Ruolo.fromString(rs.getString("Ruolo")));
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return user;

	}

	@Override
	public synchronized Collection<UserDTO> doRetrieveAll(String order) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		Collection<UserDTO> users = new LinkedList<UserDTO>();

		String selectSql = "SELECT * FROM " + TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSql += " ORDER BY " + order;
		}

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(selectSql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserDTO user = new UserDTO();
				user.setID(rs.getInt("IdAccount"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setDataNascita(rs.getDate("DataNascita"));
				user.setRuolo(Ruolo.fromString(rs.getString("Ruolo")));

			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return users;
	}

	public synchronized UserDTO doRetrieveByMail(String email) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		UserDTO user = new UserDTO();

		String sql = "SELECT * FROM" + " WHERE Email = " + email;

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				return null;
			}
			while (rs.next()) {
				user.setID(rs.getInt("IdAccount"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setEmail(rs.getString("Email"));
				user.setDataNascita(rs.getDate("DataNascita"));
				user.setRuolo(Ruolo.fromString(rs.getString("Ruolo")));
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return user;
	}

}
