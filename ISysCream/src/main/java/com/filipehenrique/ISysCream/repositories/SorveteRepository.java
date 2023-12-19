package com.filipehenrique.ISysCream.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.filipehenrique.ISysCream.entities.Sabor;
import com.filipehenrique.ISysCream.entities.Sorvete;
import com.filipehenrique.ISysCream.entities.TipoSorvete;

@Component
public class SorveteRepository implements Repository<Sorvete> {

	@Override
	public void insert(Sorvete sorvete) throws SQLException {
		 String sql = "INSERT INTO Sorvete (data_Venda) VALUES (?)";
		 
		 Long dataVenda = System.currentTimeMillis();
		 
	        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	            pstm.setTimestamp(1, new Timestamp(dataVenda));
	            pstm.executeUpdate();
	        }

	        // Recupera o ID gerado para o Sorvete recém-inserido
	        int sorveteId = getLastInsertedId();

	        // Inserir informações na tabela TipoSorvete
	        insertTipoSorvete(sorvete.getTipoSorvete(), sorveteId);

	        // Inserir informações na tabela Sabor
	        insertSabores(sorvete.getSabores(), sorveteId);
		
	}
	
	private void insertTipoSorvete(TipoSorvete tipoSorvete, int sorveteId) throws SQLException {
        String sql = "INSERT INTO TipoSorvete (tipo, quant_Bolas, peso, descricao, valor, Cod_fk_Sorvete) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setString(1, tipoSorvete.getTipo());
            pstm.setInt(2, tipoSorvete.getQuantBolas());
            pstm.setDouble(3, tipoSorvete.getPeso());
            pstm.setString(4, tipoSorvete.getDescricao());
            pstm.setDouble(5, tipoSorvete.getValor());
            pstm.setInt(6, sorveteId);
            pstm.executeUpdate();
        }
    }

    private void insertSabores(List<Sabor> sabores, int sorveteId) throws SQLException {
        String sql = "INSERT INTO Sabor (nome, descricao, Cod_fk_Sorvete) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            for (Sabor sabor : sabores) {
                pstm.setString(1, sabor.getNome());
                pstm.setString(2, sabor.getDescricao());
                pstm.setInt(3, sorveteId);
                pstm.executeUpdate();
            }
        }
    }

    private int getLastInsertedId() throws SQLException {
        String sql = "SELECT LAST_INSERT_ID() AS id";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            rs.next();
            return rs.getInt("id");
        }
    }

	@Override
	public void update(Sorvete t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sorvete findById(int codigo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Sorvete> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
