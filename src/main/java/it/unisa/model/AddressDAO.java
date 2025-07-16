package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.unisa.model.AddressDTO;
import it.unisa.model.IBeanDao;

public class AddressDAO implements IBeanDao<AddressDTO> {

    private static DataSource ds;
    private static final String TABLE_NAME = "Indirizzo";

    public AddressDAO(DataSource ds) {
        AddressDAO.ds = ds;
    }

    @Override
    public synchronized int doSave(AddressDTO address) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int generatedId = -1;
        String insertSql = "INSERT INTO " + TABLE_NAME 
                         + " (Stato, Provincia, Citta, Via, CAP, Descrizione, NumeroTelefono, IdAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStato());
            ps.setString(2, address.getProvincia());
            ps.setString(3, address.getCittà());
            ps.setString(4, address.getVia());
            ps.setString(5, address.getCAP());
            ps.setString(6, address.getDescrizione());
            ps.setString(7, address.getNumeroTelefono());
            ps.setInt(8, address.getIdUtente());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return generatedId;
    }

    @Override
    public synchronized boolean doDelete(int code) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int result = 0;

        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE IdIndirizzo = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(deleteSql);
            ps.setInt(1, code);
            result = ps.executeUpdate();
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return (result != 0);
    }

    @Override
    public synchronized AddressDTO doRetrieveByKey(int code) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        AddressDTO address = null;

        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdIndirizzo = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            ps.setInt(1, code);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = new AddressDTO();
                address.setId(rs.getInt("IdIndirizzo"));
                address.setStato(rs.getString("Stato"));
                address.setProvincia(rs.getString("Provincia"));
                address.setCittà(rs.getString("Citta"));
                address.setVia(rs.getString("Via")); 
                address.setCAP(rs.getString("CAP"));
                address.setDescrizione(rs.getString("Descrizione"));
                address.setNumeroTelefono(rs.getString("NumeroTelefono"));
                address.setIdUtente(rs.getInt("IdAccount"));
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return address;
    }

    @Override
    public synchronized Collection<AddressDTO> doRetrieveAll(String order) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<AddressDTO> addresses = new LinkedList<>();

        String selectSql = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSql += " ORDER BY " + order;
        }

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddressDTO address = new AddressDTO();
                address.setId(rs.getInt("IdIndirizzo"));
                address.setStato(rs.getString("Stato"));
                address.setProvincia(rs.getString("Provincia"));
                address.setCittà(rs.getString("Citta"));
                address.setVia(rs.getString("Via"));
                address.setCAP(rs.getString("CAP"));
                address.setDescrizione(rs.getString("Descrizione"));
                address.setNumeroTelefono(rs.getString("NumeroTelefono"));
                address.setIdUtente(rs.getInt("IdAccount"));
                addresses.add(address);
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return addresses;
    }
    

    public synchronized Collection<AddressDTO> doRetrieveByAccount(int accountId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<AddressDTO> addresses = new LinkedList<>();

        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdAccount = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            ps.setInt(1, accountId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddressDTO address = new AddressDTO();
                address.setId(rs.getInt("IdIndirizzo"));
                address.setStato(rs.getString("Stato"));
                address.setProvincia(rs.getString("Provincia"));
                address.setCittà(rs.getString("Citta"));
                address.setVia(rs.getString("Via"));
                address.setCAP(rs.getString("CAP"));
                address.setDescrizione(rs.getString("Descrizione"));
                address.setNumeroTelefono(rs.getString("NumeroTelefono"));
                address.setIdUtente(rs.getInt("IdAccount"));
                addresses.add(address);
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return addresses;
    }
}
