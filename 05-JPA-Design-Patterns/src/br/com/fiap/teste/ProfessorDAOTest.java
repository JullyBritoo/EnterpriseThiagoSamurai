package br.com.fiap.teste;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ProfessorDAO;
import br.com.fiap.dao.impl.ProfessorDAOImpl;
import br.com.fiap.entity.Professor;
import br.com.fiap.exception.CommitException;

class ProfessorDAOTest {

	private ProfessorDAO dao;

	//M�todo que ser� executado antes de todos os testes
	@BeforeAll
	public void inicializar() {
		//Arrange - instanciar os objetos
		//N�o consigo instanciar uma interface. Ent�o eu instacio o DAOImpl(em).

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("teste");
		EntityManager em = fabrica.createEntityManager();
		dao = new ProfessorDAOImpl(em);
	}

	@Test
	void cadastroTest() {
		//Act - realizar a a��o (chamar m�todo para teste)
		//Pra isso, precisa instanciar professor
		Professor prof = new Professor("Parducci", null, "123.456.789-00");

		try {
			dao.cadastrar(prof);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste...");
		}

		//Assert - validar o resultado
		//Valida se foi gerado um c�digo pela sequence
		assertNotEquals(0, prof.getCodigo());
	}

	//Teste de atualizar
	@Test
	public void atualizarTest() {
		//Arrange
		Professor prof = new Professor();
		
		//Act
		//Cadastrar um professor
		//Atualizar
		
		//Assert
	}

}
