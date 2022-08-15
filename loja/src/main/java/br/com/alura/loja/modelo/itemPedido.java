package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedidos")
public class itemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "preco_unitário")//isso serve pra formatar o nome
	private BigDecimal precoUnitario;

	private int quantidade;
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedidos;
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	public itemPedido() {
	}

	public itemPedido(int quantidade, Pedido pedidos, Produto produto) {
		this.quantidade = quantidade;
		this.pedidos = pedidos;
		this.precoUnitario = produto.getPreco();
		this.produto = produto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedido pedidos) {
		this.pedidos = pedidos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}

}
