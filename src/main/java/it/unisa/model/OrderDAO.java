package it.unisa.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class OrderDAO implements IBeanDao<OrderDTO> {

    private DataSource ds;
    private static final String TABLE_NAME = "Ordine";

    public OrderDAO(DataSource ds) {
        this.ds = ds;
    }

    public synchronized int doSaveOrder(OrderDTO ordine) throws SQLException {
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
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return generatedId;
    }

    @Override
    public int doSave(OrderDTO bean) throws SQLException {
        return 0;
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
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return result != 0;
    }

    @Override
    public synchronized OrderDTO doRetrieveByKey(int code) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        OrderDTO order = null;

        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdOrdine = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                order = new OrderDTO();
                order.setId(rs.getInt("IdOrdine"));
                order.setIdAccount(rs.getInt("IdAccount"));    
                order.setDataOrdine(rs.getTimestamp("DataOrdine"));
                order.setStato(OrderDTO.Stato.fromString(rs.getString("Stato")));
                order.setTotale(rs.getDouble("Totale"));
            }
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return order;
    }

    @Override
    public synchronized Collection<OrderDTO> doRetrieveAll(String order) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDTO> ordini = new LinkedList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.isEmpty()) {
            sql += " ORDER BY " + order;
        }
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDTO ordine = new OrderDTO();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setIdAccount(rs.getInt("IdAccount"));
                ordine.setDataOrdine(rs.getTimestamp("DataOrdine"));
                ordine.setStato(OrderDTO.Stato.fromString(rs.getString("Stato")));
                ordine.setTotale(rs.getDouble("Totale"));
                ordini.add(ordine);
            }
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return ordini;
    }

    public synchronized Collection<OrderDTO> doRetrieveByAccount(int accountId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDTO> ordini = new LinkedList<>();
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
                ordine.setDataOrdine(rs.getTimestamp("DataOrdine"));
                ordine.setStato(OrderDTO.Stato.fromString(rs.getString("Stato")));
                ordine.setTotale(rs.getDouble("Totale"));
                ordini.add(ordine);
            }
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return ordini;
    }
    
    public synchronized Collection<OrderDTO> doRetrieveByDateRange(Date from, Date to) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDTO> ordini = new LinkedList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE DataOrdine BETWEEN ? AND ?";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(from.getTime()));
            ps.setDate(2, new java.sql.Date(to.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDTO ordine = new OrderDTO();
                ordine.setId(rs.getInt("IdOrdine"));
                ordine.setIdAccount(rs.getInt("IdAccount"));
                ordine.setDataOrdine(rs.getTimestamp("DataOrdine"));
                ordine.setStato(OrderDTO.Stato.fromString(rs.getString("Stato")));
                ordine.setTotale(rs.getDouble("Totale"));
                ordini.add(ordine);
            }
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return ordini;
    }
}