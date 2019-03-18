package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_GERENTE")
@SequenceGenerator(name="gerente", sequenceName="SQ_T_GERENTE", allocationSize=1)
public class Gerente {
	
	private int codigo;
	
	private Departamento departamento;
	
	private String nome;
	
	private Nivel nivel;

}
