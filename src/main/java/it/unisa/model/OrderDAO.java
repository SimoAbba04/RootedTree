package it.unisa.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.unisa.model.OrderDTO.Stato;
import it.unisa.model.UserDTO.Ruolo;

public class OrderDAO implements IBeanDao<OrderDTO> {

    private DataSource ds;
    private static final String TABLE_NAME = "Ordine";

    public OrderDAO(DataSource ds) {
        this.ds = ds;
    }
    
    

    public synchronized int doSave(OrderDTO ordine) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + TABLE_NAME + " (IdAccount, DataOrdine, Stato, Totale) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ordine.getIdAccount());
            ps.setTimestamp(2, new Timestamp(ordine.getDataOrdine().getTime()));
            ps.setString(3, ordine.getStato().name());
            ps.setDouble(4, ordine.getTotale());
            ps.executeUpdate();
           
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
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
        return generatedId;
    }
    
 

    @Override
    public synchronized boolean doDelete(int code) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE IdOrdine = ?";
        int result = 0;
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
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
        return result != 0;
    }

    
    public synchronized OrderDTO doRetrieveByKey(int code) throws SQLException {
    	Connection c = null;
		PreparedStatement ps = null;
		OrderDTO order = new OrderDTO();

		String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdOrdine = ?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(selectSql);
			ps.setInt(1, code);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				order.setId(rs.getInt("IdOrdine"));
				order.setIdAccount(rs.getInt("IdAccount"));	
				order.setDataOrdine(rs.getDate("DataOrdine"));
				order.setStato(Stato.fromString(rs.getString("Stato")));
				order.setTotale(rs.getDouble("Totale"));
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
		return order;
    }

    @Override
    public synchronized Collection<OrderDTO> doRetrieveAll(String order) throws SQLException {
        return null;
    }


    public synchronized Collection<OrderDTO> doRetrieveByAccount(int accountId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDTO> ordini = new LinkedList<OrderDTO>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE IdAccount = ? ORDER BY DataOrdine DESC";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	OrderDTO ordine = new OrderDTO();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setIdAccount(rs.getInt("IdAccount"));
                ordine.setDataOrdine(rs.getDate("DataOrdine"));
                ordine.setStato(Stato.fromString(rs.getString("Stato")));
                ordine.setTotale(rs.getDouble("Totale"));
                ordini.add(ordine);
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
        return ordini;
    }
    
    
    public synchronized Collection<OrderDTO> doRetrieveByDateRange(Date from, Date to) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDTO> ordini = new LinkedList<OrderDTO>();
        String sql = "SELECT * FROM " + TABLE_NAME + "WHERE DataOrdine BETWEEN #?# AND #?#";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	OrderDTO ordine = new OrderDTO();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setIdAccount(rs.getInt("IdAccount"));
                ordine.setDataOrdine(rs.getDate("DataOrdine"));
                ordine.setStato(Stato.fromString(rs.getString("Stato")));
                ordine.setTotale(rs.getDouble("Totale"));
                ordini.add(ordine);
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
        return ordini;
    }


}