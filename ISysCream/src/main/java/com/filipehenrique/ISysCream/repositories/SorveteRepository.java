package com.filipehenrique.ISysCream.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.filipehenrique.ISysCream.entities.Sabor;
import com.filipehenrique.ISysCream.entities.Sorvete;
import com.filipehenrique.ISysCream.entities.TipoSorvete;



@Component
public class SorveteRepository implements Repository<Sorvete> {
	
	private static final Logger logger = LoggerFactory.getLogger(SorveteRepository.class);

	
	
	@Override
	public void insert(Sorvete sorvete) throws SQLException {
	    logger.info("Iniciando inserção de sorvete...");

	    // Verifica se o TipoSorvete associado já foi persistido
	    if (sorvete.getTipoSorvete() != null && sorvete.getTipoSorvete().getId() == null) {
	        // Se o ID do TipoSorvete for nulo, insira-o primeiro
	        insertTipoSorvete(sorvete.getTipoSorvete());
	    }

	    String sql = "INSERT INTO Sorvete (data_venda, id_tipo_sorvete) VALUES (?, ?)";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql,
	            PreparedStatement.RETURN_GENERATED_KEYS)) {
	        pstm.setTimestamp(1, Timestamp.from(sorvete.getDataVenda()));
	        pstm.setInt(2, sorvete.getTipoSorveteId());

	        pstm.execute();

	        // Recupera o ID gerado para o Sorvete recém-inserido
	        int sorveteId = getLastInsertedId();

	        // Inserir os Sabores
	        insertSabores(sorvete.getSaboresIds(), sorveteId);
	    }

	    logger.info("Sorvete inserido com sucesso!");
	}

	private void insertTipoSorvete(TipoSorvete tipoSorvete) throws SQLException {
	    String sql = "INSERT INTO TipoSorvete (tipo, quant_bolas, peso, descricao, valor) VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql,
	            PreparedStatement.RETURN_GENERATED_KEYS)) {
	        pstm.setString(1, tipoSorvete.getTipo());
	        pstm.setInt(2, tipoSorvete.getQuantBolas());
	        pstm.setDouble(3, tipoSorvete.getPeso());
	        pstm.setString(4, tipoSorvete.getDescricao());
	        pstm.setDouble(5, tipoSorvete.getValor());
	        pstm.execute();

	        // Recupera o ID gerado para o TipoSorvete recém-inserido
	        ResultSet generatedKeys = pstm.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            tipoSorvete.setId(generatedKeys.getInt(1));
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
    
    private void insertSabores(List<Integer> saboresIds, int sorveteId) throws SQLException {
        if (saboresIds != null && !saboresIds.isEmpty()) {
            String sql = "INSERT INTO Sorvete_Sabor (id_sorvete, id_sabor) VALUES (?, ?)";

            try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
                for (Integer saborId : saboresIds) {
                    if (saborId != null) { // Verifica se saborId não é nulo
                        pstm.setInt(1, sorveteId);
                        pstm.setInt(2, saborId);
                        pstm.execute();
                    }
                }
            }
        }
    }

    /*private void insertSabores(List<Sabor> sabores, int sorveteId) throws SQLException {
        String sql = "INSERT INTO Sorvete_Sabor (id_sorvete, id_sabor) VALUES (?, ?)";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            for (Sabor sabor : sabores) {
                pstm.setInt(1, sorveteId);
                pstm.setInt(2, sabor.getId());
                pstm.execute();
            }
        }
    }*/

	@Override
	public void update(Sorvete t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sorvete findById(int codigo) throws SQLException {
	    String sql = "SELECT * FROM Sorvete WHERE id_sorvete = ?";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	        pstm.setInt(1, codigo);
	        ResultSet result = pstm.executeQuery();

	        if (result.next()) {
	            Sorvete sorvete = new Sorvete();
	            sorvete.setId(result.getInt("id_sorvete"));
	            sorvete.setDataVenda(result.getTimestamp("data_venda").toInstant());

	            // Recupera o TipoSorvete associado ao Sorvete
	            int tipoSorveteId = result.getInt("id_tipo_sorvete");
	            TipoSorvete tipoSorvete = findTipoSorveteById(tipoSorveteId);
	            sorvete.setTipoSorvete(tipoSorvete);

	            // Recupera os Sabores associados ao Sorvete
	            List<Sabor> sabores = findSaboresBySorveteId(sorvete.getId());
	            sorvete.setSabores(sabores);

	            return sorvete;
	        }

	        return null;
	    }
	}

	@Override
	public void delete(int codigo) throws SQLException {
	    String deleteSorveteSql = "DELETE FROM Sorvete WHERE id_sorvete = ?";
	    String deleteSorveteSaborSql = "DELETE FROM Sorvete_Sabor WHERE id_sorvete = ?";

	    try (PreparedStatement deleteSorvetePstm = ConnectionManager.getCurrentConnection().prepareStatement(deleteSorveteSql);
	         PreparedStatement deleteSorveteSaborPstm = ConnectionManager.getCurrentConnection().prepareStatement(deleteSorveteSaborSql)) {

	        deleteSorveteSaborPstm.setInt(1, codigo);
	        deleteSorveteSaborPstm.executeUpdate();

	        deleteSorvetePstm.setInt(1, codigo);
	        deleteSorvetePstm.executeUpdate();
	    }
	}

	
	/*@Override
	public List<Sorvete> findAll() throws SQLException {
	    String sql = "SELECT * FROM Sorvete";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	        ResultSet result = pstm.executeQuery();
	        List<Sorvete> sorvetes = new ArrayList<>();

	        while (result.next()) {
	            Sorvete sorvete = new Sorvete();
	            sorvete.setId(result.getInt("id_sorvete"));
	            sorvete.setDataVenda(result.getTimestamp("data_venda").toInstant());

	            // Recupera o TipoSorvete associado ao Sorvete
	            int tipoSorveteId = result.getInt("id_tipo_sorvete");
	            TipoSorvete tipoSorvete = findTipoSorveteById(tipoSorveteId);
	            sorvete.setTipoSorvete(tipoSorvete);

	            // Recupera os Sabores associados ao Sorvete
	            List<Sabor> sabores = findSaboresBySorveteId(sorvete.getId());
	            sorvete.setSabores(sabores);

	            sorvetes.add(sorvete);
	        }

	        return sorvetes;
	    }
	}
    
    private TipoSorvete findTipoSorveteById(int tipoSorveteId) throws SQLException {
        String sql = "SELECT * FROM TipoSorvete WHERE id_TipoSorvete = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, tipoSorveteId);
            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                TipoSorvete tipoSorvete = new TipoSorvete();
                tipoSorvete.setId_TipoSorvete(result.getInt("id_TipoSorvete"));
                tipoSorvete.setTipo(result.getString("tipo"));
                tipoSorvete.setQuantBolas(result.getInt("quant_bolas"));
                tipoSorvete.setPeso(result.getDouble("peso"));
                tipoSorvete.setDescricao(result.getString("descricao"));
                tipoSorvete.setValor(result.getDouble("valor"));

                return tipoSorvete;
            }

            return null;
        }
    }

    private List<Sabor> findSaboresBySorveteId(int sorveteId) throws SQLException {
        String sql = "SELECT Sabor.* FROM Sabor " +
                     "JOIN Sorvete_Sabor ON Sabor.id_sabor = Sorvete_Sabor.id_sabor " +
                     "WHERE Sorvete_Sabor.id_sorvete = ?";

        try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
            pstm.setInt(1, sorveteId);
            ResultSet result = pstm.executeQuery();

            List<Sabor> sabores = new ArrayList<>();

            while (result.next()) {
                Sabor sabor = new Sabor();
                sabor.setId(result.getInt("id_sabor"));
                sabor.setNome(result.getString("nome"));
                sabor.setDescricao(result.getString("descricao"));

                sabores.add(sabor);
            }

            return sabores;
        }
    }*/
	
	@Override
	public List<Sorvete> findAll() throws SQLException {
	    String sql = "SELECT * FROM Sorvete";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	        ResultSet result = pstm.executeQuery();
	        List<Sorvete> sorvetes = new ArrayList<>();

	        while (result.next()) {
	            Sorvete sorvete = buildSorveteFromResultSet(result);
	            sorvetes.add(sorvete);
	        }

	        return sorvetes;
	    }
	}

	private Sorvete buildSorveteFromResultSet(ResultSet result) throws SQLException {
	    Sorvete sorvete = new Sorvete();
	    sorvete.setId(result.getInt("id_sorvete"));
	    sorvete.setDataVenda(result.getTimestamp("data_venda").toInstant());

	    // Recupera o TipoSorvete associado ao Sorvete
	    int tipoSorveteId = result.getInt("id_tipo_sorvete");
	    TipoSorvete tipoSorvete = findTipoSorveteById(tipoSorveteId);
	    sorvete.setTipoSorvete(tipoSorvete);

	    // Recupera os Sabores associados ao Sorvete
	    List<Sabor> sabores = findSaboresBySorveteId(sorvete.getId());
	    sorvete.setSabores(sabores);

	    return sorvete;
	}
	
	private TipoSorvete findTipoSorveteById(int tipoSorveteId) throws SQLException {
	    String sql = "SELECT * FROM TipoSorvete WHERE id_TipoSorvete = ?";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	        pstm.setInt(1, tipoSorveteId);
	        ResultSet result = pstm.executeQuery();

	        if (result.next()) {
	            return buildTipoSorveteFromResultSet(result);
	        }

	        return null;
	    }
	}

	private TipoSorvete buildTipoSorveteFromResultSet(ResultSet result) throws SQLException {
	    TipoSorvete tipoSorvete = new TipoSorvete();
	    tipoSorvete.setId_TipoSorvete(result.getInt("id_TipoSorvete"));
	    tipoSorvete.setTipo(result.getString("tipo"));
	    tipoSorvete.setQuantBolas(result.getInt("quant_bolas"));
	    tipoSorvete.setPeso(result.getDouble("peso"));
	    tipoSorvete.setDescricao(result.getString("descricao"));
	    tipoSorvete.setValor(result.getDouble("valor"));

	    return tipoSorvete;
	}
	
	private List<Sabor> findSaboresBySorveteId(int sorveteId) throws SQLException {
	    String sql = "SELECT Sabor.* FROM Sabor " +
	                 "JOIN Sorvete_Sabor ON Sabor.id_sabor = Sorvete_Sabor.id_sabor " +
	                 "WHERE Sorvete_Sabor.id_sorvete = ?";

	    try (PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql)) {
	        pstm.setInt(1, sorveteId);
	        ResultSet result = pstm.executeQuery();

	        List<Sabor> sabores = new ArrayList<>();

	        while (result.next()) {
	            Sabor sabor = buildSaborFromResultSet(result);
	            sabores.add(sabor);
	        }

	        return sabores;
	    }
	}

	private Sabor buildSaborFromResultSet(ResultSet result) throws SQLException {
	    Sabor sabor = new Sabor();
	    sabor.setId(result.getInt("id_sabor"));
	    sabor.setNome(result.getString("nome"));
	    sabor.setDescricao(result.getString("descricao"));

	    return sabor;
	}




}
