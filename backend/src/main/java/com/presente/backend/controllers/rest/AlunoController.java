package com.presente.backend.controllers.rest;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.dto.AlunoDTO;
import com.presente.backend.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public AlunoCadastroDTO findByIdMatricula(@RequestParam(value = "idMatricula") Long idMatricula) {
		return this.service.findByIdMatricula(idMatricula);
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public Page<AlunoDTO> search(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "matricula", defaultValue = "") String matricula,
			@RequestParam(value = "anoLetivo", defaultValue = "") Integer anoLetivo,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		return this.service.search(nome, matricula, anoLetivo, page, size, direction);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@Valid @RequestBody AlunoCadastroDTO dto) {
		return this.service.save(dto);
	}

}
