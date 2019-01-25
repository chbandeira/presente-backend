package com.presente.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.dto.AlunoCadastroDTO;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class DBService {
	
	@Autowired
	private AlunoService alunoService;
	
	public void initDbDev() {
		AlunoCadastroDTO dto = new AlunoCadastroDTO();
		dto.setNome("Charlles");
		//dto.setMatricula("123");
		dto.setNomeResponsavel("Oliveira");
		dto.setEmail("charllesbandeira@gmail.com");
		dto.setEnviarEmail(true);
		dto.setTurma("H");
		for (int i = 0; i < 98; i++) {
			dto.setMatricula(String.valueOf(i));
			alunoService.save(dto);
		}
	}

}
