package com.filipehenrique.ISysCream.entities;

public class TipoSorvete {
	
	private Integer id;
	private String tipo;
	private Integer quantBolas;
	private Double peso;
	private String descricao;
	private Double valor;
	
	public TipoSorvete() {
		
	}
	
	public TipoSorvete(Integer id, String tipo, Integer quantBolas, Double peso, String descricao, Double valor) {
		this.id = id;
		this.tipo = tipo;
		this.quantBolas = quantBolas;
		this.peso = peso;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
