package com.usj.cadastro.dominio.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usj.cadastro.dominio.modelo.Cliente;
import com.usj.cadastro.dominio.repositorio.ClienteRepositorio;

@Service

public class ClienteServico {

	@Autowired
	ClienteRepositorio clienteRepositorio;

	public Cliente create(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	public List<Cliente> read() {
		return clienteRepositorio.findAll();
	}

	public Optional<Cliente> readById(Long id) {
		return clienteRepositorio.findById(id);
	}

	public Cliente update(Long id, Cliente cliente) {
		if (clienteRepositorio.findById(id).isPresent()) {
			cliente.setId(id);
			return clienteRepositorio.save(cliente);
		}
		return null;
	}

	public void delete(Long id) {
		clienteRepositorio.deleteById(id);
	}

}
