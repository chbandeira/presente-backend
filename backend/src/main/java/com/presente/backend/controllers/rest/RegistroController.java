package com.presente.backend.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.backend.dto.RegistroDTO;
import com.presente.backend.services.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController extends BaseController {
	
	@Autowired
	private RegistroService registroService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegistroDTO> registrar(@RequestBody RegistroDTO dto) {
		return ResponseEntity.ok(this.registroService.registrar(dto));
	}

}
