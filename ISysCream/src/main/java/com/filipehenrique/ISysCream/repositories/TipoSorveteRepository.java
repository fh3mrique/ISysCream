package com.filipehenrique.ISysCream.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.filipehenrique.ISysCream.entities.TipoSorvete;

@Component
public class TipoSorveteRepository implements Repository<TipoSorvete>{

	@Override
	public void insert(TipoSorvete t) throws SQLException {
		/*tipo varchar(30) unique,
	    quant_Bolas int,
	    peso float,
	    descricao varchar(120),
	    valor float*/
		
		String sql = "INSERT INTO tiposorvete (tipo, quant_Bolas, peso, descricao, valor) VALUES (?,?,?,?,?)";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getTipo());
		pstm.setInt(2, t.getQuantBolas());
		pstm.setDouble(3, t.getPeso());
		pstm.setString(4, t.getDescricao());
		pstm.setDouble(5, t.getValor());	
		
		pstm.execute();
	}

	@Override
	public void update(TipoSorvete t) throws SQLException {
		String sql = "update tiposorvete "
				+ "set tipo=?, quant_Bolas=?, peso=?, descricao=?, valor=? "
				+ "where id_TipoSorvete=?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, t.getTipo());
		pstm.setInt(2, t.getQuantBolas());
		pstm.setDouble(3, t.getPeso());
		pstm.setString(4, t.getDescricao());
		pstm.setDouble(5, t.getValor());
		
		pstm.setLong(5, t.getId());
		
		pstm.execute();
		
	}

	@Override
	public  TipoSorvete findById(int codigo) throws SQLException {
		String sql ="select * from tiposorvete where id_TipoSorvete=?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, codigo);
		
		ResultSet result = pstm.executeQuery();
		
		if(result.next()) {
			
			TipoSorvete tipoSorvete = new TipoSorvete();
			tipoSorvete.setId(result.getInt("id_TipoSorvete"));
			tipoSorvete.setTipo(result.getString("tipo"));
			tipoSorvete.setQuantBolas(result.getInt("quant_Bolas"));
			tipoSorvete.setPeso(result.getDouble("peso"));
			tipoSorvete.setDescricao(result.getString("descricao"));
			tipoSorvete.setValor(result.getDouble("valor"));
			
			return tipoSorvete;
			
		}
		
		return null;
	}

	@Override
	public void delete(int codigo) throws SQLException {
		String sql = "delete from tiposorvete where id_TipoSorvete = ?";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, codigo);
		
		pstm.execute();
		
	}

	@Override
	public List<TipoSorvete> findAll() throws SQLException {
		String sql ="select * from tiposorvete";
		
		PreparedStatement pstm = ConnectionManager
				.getCurrentConnection().prepareStatement(sql);
		
		
		ResultSet result = pstm.executeQuery();
		
		List<TipoSorvete> tiposSorvete = new ArrayList<>();
		
		while(result.next()) {
			
			TipoSorvete a = new TipoSorvete();
			
			a.setId(result.getInt("id_TipoSorvete"));
			a.setTipo(result.getString("tipo"));
			a.setQuantBolas(result.getInt("quant_Bolas"));
			a.setPeso(result.getDouble("peso"));
			a.setDescricao(result.getString("descricao"));
			a.setValor(result.getDouble("valor"));
			
			tiposSorvete.add(a);
			
		}
		
		return tiposSorvete;
	}

}
