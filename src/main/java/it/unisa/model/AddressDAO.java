package it.unisa.model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.UserDTO.Ruolo;

public class AddressDAO implements IBeanDao<AddressDTO> {

	
	private static DataSource ds;
	
	public AddressDAO(DataSource ds) {
		AddressDAO.ds = ds;
	}

	@Override
	public synchronized void doSave(AddressDTO bean) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		String insertSql = "INSERT INTO Indirizzo (Stato,Provincia,Citta,Via,CAP,Descrizione,NumeroTelefono,IdAccount) VALUES (?,?,?,?,?)";

	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized AddressDTO doRetrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Collection<AddressDTO> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

