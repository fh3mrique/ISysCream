package com.filipehenrique.ISysCream.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
	
	public void insert(T t) throws SQLException;
	public void update(T t) throws SQLException;
	public T findById(int codigo) throws SQLException;
	public void delete(int codigo) throws SQLException;
	public List<T> findAll() throws SQLException;

}
