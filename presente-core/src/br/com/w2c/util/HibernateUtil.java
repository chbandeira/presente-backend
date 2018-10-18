package br.com.w2c.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.AlunoDisciplina;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.domain.Disciplina;
import br.com.w2c.model.domain.HistoricoAlteracao;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.OcorrenciaImportacao;
import br.com.w2c.model.domain.ParametroGeral;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Relatorio;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;

/**
 * @author charlles
 * @since 01/09/2013
 */
public class HibernateUtil {

	private static Logger log = LogManager.getLogger();
	
	private static final SessionFactory factory;
	private static final Configuration config;

	static {
		config = new Configuration();
		mapeandoEntidades();
		factory = config.configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	private static void mapeandoEntidades() {
		config.addAnnotatedClass(Aluno.class);
		config.addAnnotatedClass(CalendarioEscolar.class);
		config.addAnnotatedClass(ConfiguracaoEscola.class);
		config.addAnnotatedClass(Matricula.class);
		config.addAnnotatedClass(OcorrenciaImportacao.class);
		config.addAnnotatedClass(ParametroGeral.class);
		config.addAnnotatedClass(Perfil.class);
		config.addAnnotatedClass(Registro.class);
		config.addAnnotatedClass(Responsavel.class);
		config.addAnnotatedClass(Sala.class);
		config.addAnnotatedClass(Serie.class);
		config.addAnnotatedClass(Turma.class);
		config.addAnnotatedClass(Usuario.class);
		config.addAnnotatedClass(Relatorio.class);
		config.addAnnotatedClass(HistoricoAlteracao.class);
		config.addAnnotatedClass(Disciplina.class);
		config.addAnnotatedClass(AlunoDisciplina.class);
		config.addAnnotatedClass(TipoOcorrencia.class);
		config.addAnnotatedClass(Ocorrencia.class);
	}
	
	private static String getProperty(String property) {
		return config.getProperty(property);
	}
	
	public static Connection getConnection() {
		Connection con = null;
		String driver = getProperty("hibernate.connection.driver_class");
		String url = getProperty("hibernate.connection.url");
		String login = getProperty("hibernate.connection.username");
		String senha = getProperty("hibernate.connection.password");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, login, senha);
		} catch (ClassNotFoundException e) {
			log.error("Driver não encontrado", e);
		} catch (SQLException e) {
			log.error("Erro na conexão", e);
		}
		return con;
	}
	
	/**
	 * Crie uma nova database com o nome <b>presente</b> e execute o método main
	 * @param args
	 */
	public static void main(String[] args) {
		SchemaExport se = new SchemaExport(config);
		se.create(true, true);
	}

}