package com.filipehenrique.ISysCream.entities;

import java.time.LocalDate;
import java.util.List;

public class Sorvete {

	private Integer id;
    private TipoSorvete tipoSorvete;
    private LocalDate dataVenda;
    private List<Sabor> sabores;
    
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
	public LocalDate getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(LocalDate dataVenda) {
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
