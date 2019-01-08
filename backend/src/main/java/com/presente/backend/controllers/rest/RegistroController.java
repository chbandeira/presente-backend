package com.presente.backend.controllers.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.backend.domains.enums.TipoRegistro;
import com.presente.backend.services.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController extends BaseController {
	
	@Autowired
	private RegistroService registroService;
	
	@PostMapping("/entrada")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarEntrada(@RequestBody Map<String, String> requestParams) {
		this.registroService.registrar(requestParams.get("matricula"), TipoRegistro.ENTRADA);
	}
	
	@PostMapping("/saida")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarSaida(@RequestBody Map<String, String> requestParams) {
		this.registroService.registrar(requestParams.get("matricula"), TipoRegistro.SAIDA);
	}

}
