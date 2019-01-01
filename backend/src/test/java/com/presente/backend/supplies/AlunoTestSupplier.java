package com.presente.backend.supplies;

import java.util.Calendar;
import java.util.function.Supplier;

import com.presente.backend.domains.Aluno;
import com.presente.backend.dto.AlunoCadastroDTO;

public class AlunoTestSupplier implements Supplier<Aluno> {

	@Override
	public Aluno get() {
		Aluno aluno = new Aluno();
		aluno.setNome("Nome Aluno");
		aluno.setDataNascimento(new Calendar.Builder().setDate(1988, 2, 20).build().getTime());
		aluno.setUrlFoto("C:/local/algumacoisa.jpg");
		return aluno;
	}

	public AlunoCadastroDTO getAlunoCadastroDto() {
		AlunoCadastroDTO dto = new AlunoCadastroDTO(this.get());
		dto.setMatricula("123");
		return dto;
	}

}
