package it.unisa.model;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.unisa.model.ProductDTO.Categoria;

public class ProductDAO implements IBeanDao<ProductDTO> {

	private DataSource ds;
	private static final String TABLE_NAME = "Prodotto";

	public ProductDAO(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public int doSave(ProductDTO bean) throws SQLException {
		// Aggiungere eccezione
		return 0;
	}

	// Metodo doSave modificato per accettare l'immagine
	public synchronized int doSave(ProductDTO prodotto, InputStream immagine) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int generatedId = -1;
		String sql = "INSERT INTO " + TABLE_NAME
				+ " (Nome, Descrizione, Prezzo, Stock, Categoria, Immagine) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setInt(4, prodotto.getDisponibilità());
			ps.setString(5, prodotto.getCategoria().name());
			if (immagine != null) {
				ps.setBinaryStream(6, immagine);
			} else {
				ps.setNull(6, Types.BLOB);
			}
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

	public synchronized void doUpdate(ProductDTO prodotto, InputStream immagine) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		String sql = "UPDATE " + TABLE_NAME + " SET Nome=?, Descrizione=?, Prezzo=?, Stock=?, Categoria=?"
				+ (immagine != null ? ", Immagine=?" : "") + " WHERE IdProdotto=?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, prodotto.getNome());
			ps.setString(2, prodotto.getDescrizione());
			ps.setDouble(3, prodotto.getPrezzo());
			ps.setInt(4, prodotto.getDisponibilità());
			ps.setString(5, prodotto.getCategoria().name());
			// Metodo elegante per variare il numero di parametri
			int parameterIndex = 6;
			if (immagine != null) {
				ps.setBinaryStream(parameterIndex++, immagine);
			}
			ps.setInt(parameterIndex, prodotto.getId());
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
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE IdProdotto = ?";
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

	@Override
	public synchronized ProductDTO doRetrieveByKey(int code) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ProductDTO prodotto = new ProductDTO();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE IdProdotto = ?";
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);
			ps.setInt(1, code);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				prodotto.setId(rs.getInt("IdProdotto"));
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setDescrizione(rs.getString("Descrizione"));
				prodotto.setPrezzo(rs.getDouble("Prezzo"));
				prodotto.setDisponibilità(rs.getInt("Stock"));
				prodotto.setCategoria(Categoria.fromString(rs.getString("Categoria")));
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
		return prodotto;
	}

	@Override
	public synchronized Collection<ProductDTO> doRetrieveAll(String order) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		Collection<ProductDTO> prodotti = new LinkedList<ProductDTO>();
		String sql = "SELECT * FROM " + TABLE_NAME;
		if (order != null && !order.equals("")) {
			sql += " ORDER BY " + order;
		}
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductDTO prodotto = new ProductDTO();
				prodotto.setId(rs.getInt("IdProdotto"));
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setDescrizione(rs.getString("Descrizione"));
				prodotto.setPrezzo(rs.getDouble("Prezzo"));
				prodotto.setDisponibilità(rs.getInt("Stock"));
				prodotto.setCategoria(Categoria.fromString(rs.getString("Categoria")));
				prodotti.add(prodotto);
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
		return prodotti;
	}

	// Metodo per recuperare l'immagine come array di byte
	public synchronized byte[] getImmagine(int id) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		byte[] imageData = null;
		String sql = "SELECT Immagine FROM " + TABLE_NAME + " WHERE IdProdotto = ?";
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				imageData = rs.getBytes("Immagine");
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
		return imageData;
	}

	public synchronized void updateImage(int productId, InputStream imageStream) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Prodotto SET Immagine = ? WHERE IdProdotto = ?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);

			ps.setBinaryStream(1, imageStream);
			ps.setInt(2, productId);

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

	public synchronized Collection<ProductDTO> doRetrieveByName(String query) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		Collection<ProductDTO> prodotti = new LinkedList<ProductDTO>();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Nome LIKE ?";
		try {
			c = ds.getConnection();
			ps = c.prepareStatement(sql);

			ps.setString(1, "%" + query + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductDTO prodotto = new ProductDTO();
				prodotto.setId(rs.getInt("IdProdotto"));
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setDescrizione(rs.getString("Descrizione"));
				prodotto.setPrezzo(rs.getDouble("Prezzo"));
				prodotto.setDisponibilità(rs.getInt("Stock"));
				prodotto.setCategoria(Categoria.fromString(rs.getString("Categoria")));
				prodotti.add(prodotto);
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
		return prodotti;

	}
	
	public synchronized Collection<ProductDTO> doRetrieveByCategory(String category) throws SQLException {
	    Connection c = null;
	    PreparedStatement ps = null;
	    Collection<ProductDTO> prodotti = new LinkedList<>();

	    String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Categoria = ?";
	    
	    try {
	        c = ds.getConnection();
	        ps = c.prepareStatement(sql);
	        ps.setString(1, category);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	ProductDTO prodotto = new ProductDTO();
				prodotto.setId(rs.getInt("IdProdotto"));
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setDescrizione(rs.getString("Descrizione"));
				prodotto.setPrezzo(rs.getDouble("Prezzo"));
				prodotto.setDisponibilità(rs.getInt("Stock"));
				prodotto.setCategoria(Categoria.fromString(rs.getString("Categoria")));
				prodotti.add(prodotto);
	        }

	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } finally {
	            if (c != null) c.close();
	        }
	    }
	    return prodotti;
	}
	
	public synchronized Collection<ProductDTO> doRetrieveLastFourItem() throws SQLException {
	    Connection c = null;
	    PreparedStatement ps = null;
	    Collection<ProductDTO> prodotti = new LinkedList<>();
	    

	    String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY IdProdotto DESC LIMIT 4";
	    
	    try {
	        c = ds.getConnection();
	        ps = c.prepareStatement(sql);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            ProductDTO prodotto = new ProductDTO();
	            prodotto.setId(rs.getInt("IdProdotto"));
	            prodotto.setNome(rs.getString("Nome"));
	            prodotto.setDescrizione(rs.getString("Descrizione"));
	            prodotto.setPrezzo(rs.getDouble("Prezzo"));
	            prodotto.setDisponibilità(rs.getInt("Stock"));
	            prodotto.setCategoria(Categoria.fromString(rs.getString("Categoria")));
	            prodotti.add(prodotto);
	        }

	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } finally {
	            if (c != null) c.close();
	        }
	    }
	    
	    return prodotti;
	}


}