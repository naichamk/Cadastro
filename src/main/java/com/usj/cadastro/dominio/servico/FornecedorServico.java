package com.usj.cadastro.dominio.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.cadastro.dominio.modelo.Fornecedor;
import com.usj.cadastro.dominio.repositorio.FornecedorRepositorio;

@Service

public class FornecedorServico {

	@Autowired
	FornecedorRepositorio fornecedorRepositorio;

	public Fornecedor create(Fornecedor fornecedor) {
		return fornecedorRepositorio.save(fornecedor);
	}

	public List<Fornecedor> read() {
		return fornecedorRepositorio.findAll();
	}

	public Optional<Fornecedor> readById(Long id) {
		return fornecedorRepositorio.findById(id);
	}

	public Fornecedor update(Long id, Fornecedor fornecedor) {
		if (fornecedorRepositorio.findById(id).isPresent()) {
			fornecedor.setId(id);
			return fornecedorRepositorio.save(fornecedor);
		}
		return null;
	}

	public void delete(Long id) {
		fornecedorRepositorio.deleteById(id);
	}

}
