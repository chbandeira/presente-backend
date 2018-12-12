package com.presente.backend.supplies;

import java.util.function.Supplier;

import com.presente.backend.domains.Matricula;

public class MatriculaTestSupplier implements Supplier<Matricula> {

	@Override
	public Matricula get() {
		Matricula matricula = new Matricula("1111");
		matricula.setBolsista(false);
		matricula.setAnoLetivo(2018);
		matricula.setEnviarEmailRegistro(false);
		matricula.setEnviarSmsRegistro(false);
		matricula.setAtivo(true);
		return matricula;
	}

}
