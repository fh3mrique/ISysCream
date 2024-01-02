package com.filipehenrique.ISysCream.entities;

public class TipoSorvete {
	
	private Integer id_TipoSorvete;
    private String tipo;
    private Integer quantBolas;
    private Double peso;
    private String descricao;
    private Double valor;
	
	public TipoSorvete() {
		
	}
	
	public TipoSorvete(Integer id, String tipo, Integer quantBolas, Double peso, String descricao, Double valor) {
		this.id_TipoSorvete = id;
		this.tipo = tipo;
		this.quantBolas = quantBolas;
		this.peso = peso;
		this.descricao = descricao;
		this.valor = valor;
	}

	
	public Integer getId() {
	    return id_TipoSorvete != null ? id_TipoSorvete : 0;
	}

	public void setId(Integer id) {
		this.id_TipoSorvete = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantBolas() {
		return quantBolas;
	}

	public void setQuantBolas(Integer quantBolas) {
		this.quantBolas = quantBolas;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setId_TipoSorvete(int int1) {
		// TODO Auto-generated method stub
		
	}
}
