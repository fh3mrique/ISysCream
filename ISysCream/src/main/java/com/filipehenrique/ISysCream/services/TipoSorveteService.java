package com.filipehenrique.ISysCream.services;

import java.sql.SQLException;
import java.util.List;

import com.filipehenrique.ISysCream.entities.TipoSorvete;
import com.filipehenrique.ISysCream.repositories.Repository;
import com.filipehenrique.ISysCream.repositories.TipoSorveteRepository;

public class TipoSorveteService {
	
	//implementação do singleton
	private static TipoSorveteService myself = null;
			
		private Repository<TipoSorvete> repositorioTipoSorvete = null;
			
		private TipoSorveteService() {
			repositorioTipoSorvete = new TipoSorveteRepository();	
		}
			
		public static TipoSorveteService getCurrentInstance() {
				
			if(myself == null)
				myself = new TipoSorveteService();
				
			return myself;
				
		}
		//fim da implementação do singleton
		
		
		public void insert(TipoSorvete t) throws SQLException {
			this.repositorioTipoSorvete.insert(t);
		}
		
		public void update(TipoSorvete t) throws SQLException {
			this.repositorioTipoSorvete.update(t);
		}
		
		public TipoSorvete findById(int id) throws SQLException {
			return this.repositorioTipoSorvete.findById(id);
		}
		
		public void delete(int codigo) throws SQLException {
			this.repositorioTipoSorvete.delete(codigo);
		}
		
		public List<TipoSorvete> findAll() throws SQLException{
			return this.repositorioTipoSorvete.findAll();
		}

}
