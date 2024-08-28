package net.model;

public class EntradaSaida {
	
	private int codigo_transacao;
	private int codigo_produto;
	private int quantidade;
	private String tipo;
	private long valor_total;
	
	public int getCodigo_transacao() {
		return codigo_transacao;
	}
	public void setCodigo_transacao(int codigo_transacao) {
		this.codigo_transacao = codigo_transacao;
	}
	public int getCodigo_produto() {
		return codigo_produto;
	}
	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public long getValor_total() {
		return valor_total;
	}
	public void setValor_total(long valor_total) {
		this.valor_total = valor_total;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
