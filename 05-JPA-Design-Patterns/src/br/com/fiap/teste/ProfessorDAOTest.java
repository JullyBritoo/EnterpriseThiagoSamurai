package br.com.fiap.teste;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.fiap.dao.ProfessorDAO;
import br.com.fiap.dao.impl.ProfessorDAOImpl;
import br.com.fiap.entity.Professor;
import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;

class ProfessorDAOTest {

	private ProfessorDAO dao;
	private Professor prof;

	//M�todo que ser� executado antes de cada teste

	@Test
	@DisplayName("Teste de remo��o de professor com sucesso")
		void removeTest() {	
		try {
			dao.excluir(prof.getCodigo());
			dao.commit();
		} catch (CodigoInexistenteException |CommitException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
		
		assertThrows(CodigoInexistenteException.class, ()-> dao.pesquisar(prof.getCodigo()));
	}
	
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
	@DisplayName("Teste de cadastro de professor com sucesso")
	void cadastroTest() {
		//Act - realizar a a��o (chamar m�todo para teste)
		//Pra isso, precisa instanciar professor
		prof = new Professor("Parducci", null, "123.456.789-00");

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
	@DisplayName("Teste de atualiza��o de professor com sucesso")
	public void atualizarTest() {
		//Arrange
		prof = new Professor("", null, "");
		try {
			dao.cadastrar(prof);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Falha no teste");
		}

		//Atualizar o Professor
		Professor prof2 = new Professor(prof.getCodigo(), "Rafael",null,"123.456.789.98");
		try {
			dao.atualizar(prof2);
			dao.commit();
		} catch (CommitException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
		//Assert
		//Pesquise e valida se o valor foi alterado no banco 
		try {
			Professor prof3 = dao.pesquisar(prof2.getCodigo());
			assertEquals("Rafael", prof3.getNome());
			assertEquals(prof2.getCpf(), prof3.getCpf());
		} catch (CodigoInexistenteException e) {
			e.printStackTrace();
			fail("Falha no teste");
		}
	}

	@Test
	@DisplayName("Teste de pesquisa de professor com sucesso")
	void pesquisarTest() {
		try {
		//Pesquisar o professor 
		Professor busca = dao.pesquisar(prof.getCodigo());
		//Assert - validar
		assertNotNull(busca);//encontrou alguem
		assertEquals(busca.getNome(), prof.getNome());
		}catch(CodigoInexistenteException e){
			e.printStackTrace();
			fail("Falha no teste");
		}
	}
	
}
