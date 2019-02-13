package com.presente.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.presente.dto.ResponsavelDTO;
import com.presente.services.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

	@Autowired
	private ResponsavelService service;

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public Page<ResponsavelDTO> searchByTerm(@RequestParam(value = "term", defaultValue = "") String term) {
		return this.service.searchByTerm(term);
	}

}
