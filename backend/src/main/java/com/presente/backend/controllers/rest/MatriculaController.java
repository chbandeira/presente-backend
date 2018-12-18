package com.presente.backend.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.services.MatriculaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaService service;

	@GetMapping("/{idMatricula}")
	@ResponseStatus(HttpStatus.OK)
	public AlunoCadastroDTO findByIdMatricula(@PathVariable Long idMatricula) {
		return this.service.findByIdMatricula(idMatricula);
	}
	
	@DeleteMapping("/{idMatricula}")
	@ResponseStatus(HttpStatus.OK)
	public void disableByIdMatricula(@PathVariable Long idMatricula) {
		this.service.disableByIdMatricula(idMatricula);
	}

}
