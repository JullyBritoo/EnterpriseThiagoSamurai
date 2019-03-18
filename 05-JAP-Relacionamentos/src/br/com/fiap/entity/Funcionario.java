package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="T_FUNCIONARIO")
@SequenceGenerator(name="func", sequenceName="SQ_T_FUNCIONARIO", allocationSize=1)
public class Funcionario {

	
	private int codigo;
	
	private Departamento departamento;
	
	private String nome;
	
	private Calendar dtNasc;
	
	private float salario;
}
