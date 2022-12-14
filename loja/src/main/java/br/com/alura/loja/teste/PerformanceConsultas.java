package br.com.alura.loja.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.itemPedido;
import br.com.alura.loja.util.JPAutil;

public class PerformanceConsultas {

	public static void main(String[] args) {

		popularBancoDeDados(); 	
		
		EntityManager em = JPAutil.getEntityManager();
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(2l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "PlayStation 5", new BigDecimal("4000"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("10000"), informatica);

		Cliente cliente = new Cliente("Ricardo", "123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new itemPedido(10, pedido, celular));
		pedido.adicionarItem(new itemPedido(40, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new itemPedido(2, pedido, macbook));

		EntityManager em = JPAutil.getEntityManager();
		ProdutoDAO produtodao = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ClienteDAO clienteDao = new ClienteDAO(em);
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(videogames);
		categoriaDAO.cadastrar(informatica);
		
		produtodao.cadastrar(celular);
		produtodao.cadastrar(videogame);
		produtodao.cadastrar(macbook);
		
		clienteDao.cadastrar(cliente);
		
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		em.close();
	}

}
