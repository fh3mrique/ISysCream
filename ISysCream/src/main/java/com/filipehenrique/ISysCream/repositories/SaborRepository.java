package com.filipehenrique.ISysCream.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.filipehenrique.ISysCream.entities.Sabor;

@Component
public class SaborRepository implements Repository<Sabor>{

	@Override
	public void insert(Sabor t) throws SQLException {
		
		String sql = "INSERT INTO sabor(nome, descricao) VALUES (?,?)";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getNome());
		pstm.setString(2, t.getDescricao());
		
		pstm.execute();		
	}

	@Override
	public void update(Sabor t) throws SQLException {
		String sql = "update Sabor set nome=?, descricao=? where id_sabor=?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getNome());
		pstm.setString(2, t.getDescricao());
		
		pstm.setInt(3, t.getId());
		
		pstm.execute();	
		
	}

	@Override
	public Sabor findById(int codigo) throws SQLException {
		String sql ="select * from Sabor where id_sabor=?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, codigo);
		
		ResultSet result = pstm.executeQuery();
		
		if(result.next()) {
			
			Sabor sabor = new Sabor();
			sabor.setId(result.getInt("id_sabor"));
			sabor.setNome(result.getString("nome"));
			sabor.setDescricao(result.getString("descricao"));
			
			return sabor;
		}
		
		return null;
	}

	@Override
	public void delete(int codigo) throws SQLException {
		String sql = "delete from Sabor where id_sabor = ?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, codigo);
		
		pstm.execute();
		
	}

	@Override
	public List<Sabor> findAll() throws SQLException {
		String sql ="select * from Sabor";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		
		ResultSet result = pstm.executeQuery();
		
		List<Sabor> sabores = new ArrayList<>();
		
		while(result.next()) {
			
			Sabor sabor = new Sabor();
			
			sabor.setId(result.getInt("id_sabor"));
			sabor.setNome(result.getString("nome"));
			sabor.setDescricao(result.getString("descricao"));
			
			sabores.add(sabor);
		}
		
		
		return sabores;
	}

}
