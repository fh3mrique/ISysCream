package com.filipehenrique.ISysCream.entities;

import java.util.List;

public class Sorvete {

	private Integer id;
	private java.time.Instant dataVenda;
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
	public java.time.Instant getDataVenda() {
		return dataVenda;
	}
	public void Instant(java.time.Instant dataVenda) {
		this.dataVenda = dataVenda;
	}
	public List<Sabor> getSabores() {
		return sabores;
	}
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
	public void setDataVenda(java.time.Instant instant) {
		this.dataVenda = instant;
	}

	public void registrarSorvete() {
		if (sabores.size() > tipoSorvete.getQuantBolas()) {
			sabores.subList(0, tipoSorvete.getQuantBolas());
		}
	}
	
	
    
    
}
