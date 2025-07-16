package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class PayMethodDAO implements IBeanDao<PayMethodDTO> {

    private static DataSource ds;
    private static final String TABLE_NAME = "MetodoPagamento";

    public PayMethodDAO(DataSource ds) {
        PayMethodDAO.ds = ds;
    }

    @Override
    public synchronized int doSave(PayMethodDTO paymentMethod) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int generatedId = -1;
        String insertSql = "INSERT INTO " + TABLE_NAME 
                         + " (NumeroCarta, DataScadenza, CodiceSicurezza, Titolare, IdAccount) VALUES (?, ?, ?, ?, ?)";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paymentMethod.getNumeroCarta());
            ps.setDate(2, paymentMethod.getDataScadenza());
            ps.setString(3, paymentMethod.getCodiceSicurezza());
            ps.setString(4, paymentMethod.getTitolare());
            ps.setInt(5, paymentMethod.getIdAccount());
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

        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE IdPagamento = ?";

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
    public synchronized PayMethodDTO doRetrieveByKey(int code) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PayMethodDTO paymentMethod = null;

        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdPagamento = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            ps.setInt(1, code);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paymentMethod = new PayMethodDTO();
                paymentMethod.setId(rs.getInt("IdPagamento"));
                paymentMethod.setNumeroCarta(rs.getString("NumeroCarta"));
                paymentMethod.setDataScadenza(rs.getDate("DataScadenza"));
                paymentMethod.setCodiceSicurezza(rs.getString("CodiceSicurezza"));
                paymentMethod.setTitolare(rs.getString("Titolare"));
                paymentMethod.setIdAccount(rs.getInt("IdAccount"));
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return paymentMethod;
    }

    @Override
    public synchronized Collection<PayMethodDTO> doRetrieveAll(String order) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<PayMethodDTO> paymentMethods = new LinkedList<>();

        String selectSql = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSql += " ORDER BY " + order;
        }

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PayMethodDTO paymentMethod = new PayMethodDTO();
                paymentMethod.setId(rs.getInt("IdPagamento"));
                paymentMethod.setNumeroCarta(rs.getString("NumeroCarta"));
                paymentMethod.setDataScadenza(rs.getDate("DataScadenza"));
                paymentMethod.setCodiceSicurezza(rs.getString("CodiceSicurezza"));
                paymentMethod.setTitolare(rs.getString("Titolare"));
                paymentMethod.setIdAccount(rs.getInt("IdAccount"));
                paymentMethods.add(paymentMethod);
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return paymentMethods;
    }
    

    public synchronized PayMethodDTO doRetrieveByAccount(int accountId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        PayMethodDTO paymentMethod = null;

        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE IdAccount = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(selectSql);
            ps.setInt(1, accountId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paymentMethod = new PayMethodDTO();
                paymentMethod.setId(rs.getInt("IdPagamento"));
                paymentMethod.setNumeroCarta(rs.getString("NumeroCarta"));
                paymentMethod.setDataScadenza(rs.getDate("DataScadenza"));
                paymentMethod.setCodiceSicurezza(rs.getString("CodiceSicurezza"));
                paymentMethod.setTitolare(rs.getString("Titolare"));
                paymentMethod.setIdAccount(rs.getInt("IdAccount"));
            }
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
        return paymentMethod;
    }
    
    public synchronized void doUpdate(PayMethodDTO paymentMethod) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        String updateSql = "UPDATE " + TABLE_NAME 
                         + " SET NumeroCarta = ?, DataScadenza = ?, CodiceSicurezza = ?, Titolare = ? WHERE IdPagamento = ?";

        try {
            c = ds.getConnection();
            ps = c.prepareStatement(updateSql);
            ps.setString(1, paymentMethod.getNumeroCarta());
            ps.setDate(2, paymentMethod.getDataScadenza());
            ps.setString(3, paymentMethod.getCodiceSicurezza());
            ps.setString(4, paymentMethod.getTitolare());
            ps.setInt(5, paymentMethod.getId()); 
            
            ps.executeUpdate();
        } finally {
            try {
                if (ps != null) ps.close();
            } finally {
                if (c != null) c.close();
            }
        }
    }
}
