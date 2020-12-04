package com.usj.cadastro.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usj.cadastro.dominio.modelo.Cliente;
import com.usj.cadastro.dominio.servico.ClienteServico;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteServico clienteServico;

	ModelAndView mv;

	@GetMapping
	public ModelAndView getClientes() {
		mv = new ModelAndView("cliente-listar");
		List<Cliente> clientes = clienteServico.read();
		mv.addObject("clientes", clientes);
		return mv;
	}

	@GetMapping("/cadastrar")
	public String getClienteForm() {
		return "cliente-cadastrar";

	}

	@PostMapping("/cadastrar")
	public String saveCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/cliente/cadastrar";
		}
		clienteServico.create(cliente);

		return "redirect:/clientes";
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView getClienteFormUpdate(@PathVariable Long id) {
		mv = new ModelAndView("cliente-alterar");
		Cliente cliente = clienteServico.readById(id).get();
		mv.addObject("cliente", cliente);
		return mv;

	}

	@PostMapping("/alterar/{id}")
	public String updateCliente(@PathVariable Long id, @Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/clientes/alterar/" + id;
		}
		clienteServico.update(id, cliente);

		return "redirect:/clientes";
	}

	@PostMapping("/deletar/{id}")
	public String deleteCliente(@PathVariable Long id) {

		clienteServico.delete(id);

		return "redirect:/clientes";
	}
}
