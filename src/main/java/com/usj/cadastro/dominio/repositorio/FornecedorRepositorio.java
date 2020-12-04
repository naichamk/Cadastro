package com.usj.cadastro.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usj.cadastro.dominio.modelo.Fornecedor;

public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {

}
