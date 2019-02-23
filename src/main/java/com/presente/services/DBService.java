package com.presente.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.presente.domains.Telefone;
import com.presente.domains.enums.TipoTelefone;
import com.presente.dto.AlunoInsertDTO;


/**
 * @author Charlles Bandeira
 *
 */
@Service
public class DBService {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Random random = new Random();
	
	public void initDbDev() {
		for (int i = 0; i < 11; i++) {
			AlunoInsertDTO dto = new AlunoInsertDTO();
			dto.setNome("Aluno Teste "+String.valueOf(i));
			dto.setNomeResponsavel("Resp Test "+String.valueOf(i));
			dto.setEnviarEmail(true);
			dto.setSala(String.valueOf(this.random.nextInt(20) + 1));
			dto.setSerie(String.valueOf(this.random.nextInt(3) + 1));
			dto.setTurno(this.random.nextInt(3));
			dto.setTurma(new String(new char[] {(char) (this.random.nextInt(26) + 65)}));
			if (i == 0) {
				dto.setCpf("184.423.879-27");
				dto.setEmail("charlles_df@hotmail.com");
				dto.setEmail2("charlles_df2@hotmail.com");
				dto.setMatricula("123");
				dto.addTelefone(new Telefone(TipoTelefone.CELULAR.ordinal(), "(61)99999-9999", null));
				dto.addTelefone(new Telefone(TipoTelefone.FIXO.ordinal(), "(61)3999-9999", null));
			} else {
				dto.setEmail("aluno_"+String.valueOf(i)+"@email.com");
				dto.setMatricula(String.valueOf(i));
			}
			dto.setSenha(this.encoder.encode("12345"));
			alunoService.save(dto, null);
		}
	}

}
