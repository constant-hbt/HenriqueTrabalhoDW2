package com.dw2.rpingreact.HenriqueTrabalho;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Produto> findAllProducts(){
		return produtoRepo.findAll();
	}
	
	@GetMapping(path = "product/{id}")
	public @ResponseBody ResponseEntity<Produto> findProduct(@PathVariable(required = true, name = "id") Long id){
		Optional<Produto> product = produtoRepo.findById(id);
		if(product.isPresent()) {
			return ResponseEntity.ok(product.get());
		}
		return ResponseEntity.status(404).build();
	}
	
	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<Produto> insertProduct(@RequestBody Produto newProduct){
		if(newProduct != null) {
			return ResponseEntity.ok(produtoRepo.save(newProduct));
		}
		return ResponseEntity.status(404).build();
	}
	
	@PutMapping(path = "/update/{id}")
	public @ResponseBody ResponseEntity<Produto> updateProduct(@PathVariable(required = true, name = "id") Long id, @RequestBody Produto newProduct){
		Optional<Produto> p = produtoRepo.findById(id);
		if(p.isPresent()) {
			p.get().setDescricao(newProduct.getDescricao());
			p.get().setQuantidade(newProduct.getQuantidade());
			p.get().setValor(newProduct.getValor());
			return ResponseEntity.ok(produtoRepo.save(p.get()));
		}
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public @ResponseBody String deleteProduct(@PathVariable(required = true, name = "id") Long id){
		Optional<Produto> p = produtoRepo.findById(id);
		if(p.isPresent()) {
			produtoRepo.deleteById(id);
			return "Produto deletado com sucesso!";
		}
		return "Produto não encontrado!";
	}
	
}
