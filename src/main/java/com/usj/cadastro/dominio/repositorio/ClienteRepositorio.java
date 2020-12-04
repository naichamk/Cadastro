package com.usj.cadastro.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usj.cadastro.dominio.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
