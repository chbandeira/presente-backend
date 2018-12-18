package com.presente.backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.presente.backend.controllers.rest.AlunoController;
import com.presente.backend.dto.AlunoCadastroDTO;
import com.presente.backend.supplies.AlunoTestSupplier;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest
public class AlunoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AlunoController alunoController;
	
	private AlunoTestSupplier alunoSupplier = new AlunoTestSupplier();

	@Test
	public void shouldReturnAlunos() throws Exception {
		this.mvc.perform(get("/alunos/search")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldSaveNewAluno() throws Exception {
		String objJson = new ObjectMapper().writeValueAsString(this.alunoSupplier.getAlunoCadastroDto());
		this.mvc.perform(
				post("/alunos")
					.content(objJson)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}
	
	@Test
	public void shouldValidadedNewAluno() throws Exception {
		String objJson = new ObjectMapper().writeValueAsString(new AlunoCadastroDTO());
		this.mvc.perform(
				post("/alunos")
					.content(objJson)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
}
