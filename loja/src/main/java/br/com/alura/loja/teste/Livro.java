package br.com.alura.loja.teste;

import javax.persistence.Entity;

import br.com.alura.loja.modelo.Produto;
@Entity
public class Livro extends Produto {

	private String autor;
	private Integer numeroDePaginas;
	
	public Livro() {
	}

	public Livro(String autor, Integer numeroDePaginas) {
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}
	
	
}
