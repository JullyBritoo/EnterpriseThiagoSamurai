package br.com.fiap.dao;

import br.com.fiap.exception.CodigoInexistenteException;
import br.com.fiap.exception.CommitException;

public interface GenericDAO <Tabela, Chave>{
	
	void cadastrar(Tabela entidade);
	
	void atualizar(Tabela entidade);
	
	
	//Nem sempre a chave primaria ser� int, ent�o o excluir n�o precisa ser sempre int tamb�m. Pode ser String
	void excluir(Chave id)throws CodigoInexistenteException;
	
	//Pesquisa pelo objeto Tabela
	Tabela pesquisar(Chave id)throws CodigoInexistenteException;
	
	void commit() throws CommitException;
	
}