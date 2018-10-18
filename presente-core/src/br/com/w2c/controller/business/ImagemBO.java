package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;

import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;

@Component
public class ImagemBO {

	public void carregarFoto(String filename, String diretorio, byte[] foto) {
		try {
			if (!isNuloOuVazio(foto)) {
				File local = new File(diretorio + File.separator + filename + ".jpeg");
				FileImageOutputStream imageOutput = new FileImageOutputStream(local);
				imageOutput.write(foto, 0, foto.length);
				imageOutput.close();
			}
        }
        catch (IOException e) {
        	new AplicacaoException(e);
        }
	}
	
	public String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }
	
}
