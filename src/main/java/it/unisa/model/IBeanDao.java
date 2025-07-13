package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDao<T> {
	public int doSave(T bean) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public T doRetrieveByKey(int code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
}
