package com.presente.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presente.backend.domains.HistoricoAlteracaoAluno;
import com.presente.backend.domains.Registro;
import com.presente.backend.domains.enums.TipoRegistro;
import com.presente.backend.exceptions.StandardValidationException;
import com.presente.backend.repositories.RegistroRepository;

@Service
public class RegistroService {
	
	@Autowired
	private RegistroRepository repository;
	
	@Autowired
	private HistoricoAlteracaoAlunoService historicoAlteracaoAlunoService;

	public void registrar(String matricula, TipoRegistro tipo) {
		Registro registro = new Registro();
		Optional<HistoricoAlteracaoAluno> historico = historicoAlteracaoAlunoService.findLastByMatricula(matricula);
		if (historico.isPresent()) {			
			registro.setHistoricoAlteracaoAluno(historico.get());	
			registro.setTipoRegistro(tipo);
			this.repository.save(registro);
		} else {
			throw new StandardValidationException("Matrícula não encontrada");
		}
	}

}
