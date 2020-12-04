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

import com.usj.cadastro.dominio.modelo.Fornecedor;
import com.usj.cadastro.dominio.servico.FornecedorServico;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	FornecedorServico fornecedorServico;

	ModelAndView mv;

	@GetMapping
	public ModelAndView getFornecedores() {
		mv = new ModelAndView("fornecedor-listar");
		List<Fornecedor> fornecedores = fornecedorServico.read();
		mv.addObject("fornecedores", fornecedores);
		return mv;
	}

	@GetMapping("/cadastrar")
	public String getFornecedorForm() {
		return "fornecedor-cadastrar";

	}

	@PostMapping("/cadastrar")
	public String saveFornecedor(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/fornecedor/cadastrar";
		}
		fornecedorServico.create(fornecedor);

		return "redirect:/fornecedores";
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView getFornecedorFormUpdate(@PathVariable Long id) {
		mv = new ModelAndView("fornecedor-alterar");
		Fornecedor fornecedor = fornecedorServico.readById(id).get();
		mv.addObject("fornecedor", fornecedor);
		return mv;

	}

	@PostMapping("/alterar/{id}")
	public String updateFornecedor(@PathVariable Long id, @Valid Fornecedor fornecedor, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/fornecedores/alterar/" + id;
		}
		fornecedorServico.update(id, fornecedor);

		return "redirect:/fornecedores";
	}

	@PostMapping("/deletar/{id}")
	public String deleteFornecedor(@PathVariable Long id) {

		fornecedorServico.delete(id);

		return "redirect:/fornecedores";
	}
}
