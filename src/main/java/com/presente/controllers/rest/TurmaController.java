package com.presente.controllers.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.dto.TurmaDTO;
import com.presente.dto.TurmaInsertDTO;
import com.presente.dto.TurmaUpdateDTO;
import com.presente.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	public TurmaService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<TurmaDTO> search(
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "serie", defaultValue = "") String serie,
			@RequestParam(value = "sala", defaultValue = "") String sala,
			@RequestParam(value = "turno", defaultValue = "") Integer turno,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy) {

		TurmaDTO dto = new TurmaDTO(descricao, serie, sala, turno);
		return this.service.search(dto, page, size, direction, orderBy);
	}
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public Page<TurmaDTO> searchByTerm(@RequestParam(value = "term", defaultValue = "") String term) {
		return this.service.searchByTerm(term);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer insert(@Valid @RequestBody TurmaInsertDTO dto) {
		return this.service.save(dto);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Integer update(@Valid @RequestBody TurmaUpdateDTO dto) {
		return this.service.save(dto);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TurmaDTO findById(@PathVariable Integer id) {
		return this.service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void disableById(@PathVariable Integer id) {
		this.service.disableById(id);
	}
}
