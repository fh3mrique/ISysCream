package com.filipehenrique.ISysCream.entities;

import java.util.List;

public class Sorvete {

	private Integer id;
	private long dataVenda;
    private TipoSorvete tipoSorvete;
    private List<Sabor> sabores;
    
    public Sorvete() {
    	
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoSorvete getTipoSorvete() {
		return tipoSorvete;
	}
	public void setTipoSorvete(TipoSorvete tipoSorvete) {
		this.tipoSorvete = tipoSorvete;
	}
	public long getDataVenda() {
		return dataVenda;
	}
	public void Instant(long dataVenda) {
		this.dataVenda = dataVenda;
	}
	public List<Sabor> getSabores() {
		return sabores;
	}
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
	public void registrarSorvete() {
		if (sabores.size() > tipoSorvete.getQuantBolas()) {
			sabores.subList(0, tipoSorvete.getQuantBolas());
		}
	}
    
    
}
