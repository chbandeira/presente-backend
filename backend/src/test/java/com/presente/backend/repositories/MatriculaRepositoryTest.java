package com.presente.backend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.presente.backend.domains.Matricula;
import com.presente.backend.supplies.AlunoTestSupplier;
import com.presente.backend.supplies.MatriculaTestSupplier;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class MatriculaRepositoryTest {

	@Autowired
	private MatriculaRepository repository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	private MatriculaTestSupplier matriculaSupplier = new MatriculaTestSupplier();
	private AlunoTestSupplier alunoSupplier = new AlunoTestSupplier();

	@Test
	public void shouldSaveMatriculaAndReturnByIdMatricula() {
		Matricula matricula = this.matriculaSupplier.get();
		matricula.setAluno(this.alunoRepository.save(this.alunoSupplier.get()));		
		matricula = this.repository.save(matricula);
		Optional<Matricula> mat = this.repository.findById(matricula.getId());
		assertThat(mat.get().getMatricula()).isEqualTo(this.matriculaSupplier.get().getMatricula());
		assertThat(mat.get().getAluno().getNome()).isEqualTo(this.alunoSupplier.get().getNome());
	}

}
