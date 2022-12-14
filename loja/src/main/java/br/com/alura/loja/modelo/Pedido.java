package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	@Column(name = "valor_total")//isso serve pra formatar o nome
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	@OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL) //aqui precisa dizer que há um bidirecionamento para não gerar tabelas a mais
	//cascade all faz um efeito cascata nos bidirecionais, o que acontece em um tbm acontece no outro
	private List<itemPedido> itens = new ArrayList<itemPedido>(); //para já iniciar com uma lista vazia

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionarItem(itemPedido item) { //para vincular os dois lados.
		item.setPedidos(this);
		this.getItens().add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());//aqui vai sendo incrementado o valor ao valor total
	}

	public Pedido() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<itemPedido> getItens() {
		return itens;
	}

}
