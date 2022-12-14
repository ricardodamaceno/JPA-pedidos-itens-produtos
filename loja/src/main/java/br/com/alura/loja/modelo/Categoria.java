package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "categorias")

public class Categoria {
	
	@EmbeddedId //usado para saber que dentro dessa classse estão os atributos que formam a chave primaria
	private CategoriaId id;
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "tipoJpa");
	}
	public String getNome() {
		return this.id.getNome();
	}
	
	
}
