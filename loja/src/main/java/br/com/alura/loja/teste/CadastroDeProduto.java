package br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAutil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em = JPAutil.getEntityManager();
		ProdutoDAO produtodao = new ProdutoDAO(em);
		
		Produto p = produtodao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtodao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtodao.buscarPrecoPorNome("Xiomi Redmi");
		System.out.println("O preço do produto é: " + precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("Xiomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
	

		EntityManager em = JPAutil.getEntityManager();
		ProdutoDAO produtodao = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		produtodao.cadastrar(celular);
		
		em.getTransaction().commit();
		
		em.find(Categoria.class, new CategoriaId("CELULARES", "tipoJpa"));
		em.close();
	}
}
