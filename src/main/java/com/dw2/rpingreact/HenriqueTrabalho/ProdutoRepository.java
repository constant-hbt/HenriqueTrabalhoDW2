package com.dw2.rpingreact.HenriqueTrabalho;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByDescricao(String nome);
	List<Produto> findByQuantidade(Double quantidade);
	List<Produto> findByValor(Double valor);
}
