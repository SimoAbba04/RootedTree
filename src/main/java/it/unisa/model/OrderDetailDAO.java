package it.unisa.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

// Questa classe non implementa IBeanDao perché la sua chiave primaria è meno importante
// dei suoi legami con Ordine e Prodotto.
public class DettaglioOrdineDAO {

    private DataSource ds;
    private static final String TABLE_NAME = "DettaglioOrdine";

    public DettaglioOrdineDAO(DataSource ds) {
        this.ds = ds;
    }

    public synchronized void doSave(DettaglioOrdineDTO dettaglio) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO " + TABLE_NAME + " (IdOrdine, IdProdotto, Quantita, PrezzoUnitario) VALUES (?, ?, ?, ?)";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, dettaglio.getIdOrdine());
            ps.setInt(2, dettaglio.getIdProdotto());
            ps.setInt(3, dettaglio.getQuantita());
            ps.setBigDecimal(4, dettaglio.getPrezzoUnitario());
            ps.executeUpdate();
        } finally {
            // Gestione chiusura risorse
        }
    }

    // Metodo fondamentale per recuperare i dettagli di un ordine specifico
    public synchronized Collection<DettaglioOrdineDTO> doRetrieveByOrdine(int ordineId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        Collection<DettaglioOrdineDTO> dettagli = new LinkedList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE IdOrdine = ?";
        try {
            c = ds.getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, ordineId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO();
                dettaglio.setIdDettaglio(rs.getInt("IdDettaglio"));
                dettaglio.setIdOrdine(rs.getInt("IdOrdine"));
                dettaglio.setIdProdotto(rs.getInt("IdProdotto"));
                dettaglio.setQuantita(rs.getInt("Quantita"));
                dettaglio.setPrezzoUnitario(rs.getBigDecimal("PrezzoUnitario"));
                dettagli.add(dettaglio);
            }
        } finally {
            // Gestione chiusura risorse
        }
        return dettagli;
    }
}