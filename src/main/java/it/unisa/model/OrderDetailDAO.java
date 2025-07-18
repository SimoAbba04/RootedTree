package it.unisa.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class OrderDetailDAO {

    private DataSource ds;
    private static final String TABLE_NAME = "DettaglioOrdine";

    public OrderDetailDAO(DataSource ds) {
        this.ds = ds;
    }

    public synchronized void doSave(OrderDetailDTO dettaglio) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + TABLE_NAME + " (IdOrdine, IdProdotto, Quantita, PrezzoUnitario) VALUES (?, ?, ?, ?)";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, dettaglio.getIdOrdine());
            ps.setInt(2, dettaglio.getIdProdotto());
            ps.setInt(3, dettaglio.getQta());
            ps.setDouble(4, dettaglio.getPrezzoUnitario());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
    }

    public synchronized Collection<OrderDetailDTO> doRetrieveByOrdine(int ordineId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<OrderDetailDTO> dettagli = new LinkedList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE IdOrdine = ?";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, ordineId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailDTO dettaglio = new OrderDetailDTO();
                dettaglio.setId(rs.getInt("IdDettaglio"));
                dettaglio.setIdOrdine(rs.getInt("IdOrdine"));
                dettaglio.setIdProdotto(rs.getInt("IdProdotto"));
                dettaglio.setQta(rs.getInt("Quantita"));
                dettaglio.setPrezzoUnitario(rs.getDouble("PrezzoUnitario"));
                dettagli.add(dettaglio);
            }
        } finally {
            if (ps != null) ps.close();
            if (c != null) c.close();
        }
        return dettagli;
    }
}
