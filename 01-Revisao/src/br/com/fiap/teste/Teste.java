package br.com.fiap.teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.fiap.bean.Carro;
import br.com.fiap.bean.Transmissao;

public class Teste {

	@SuppressWarnings("all")
	public static void main(String args[]) {
		Carro carro = new Carro();
		carro.setCambio(Transmissao.SEMI_AUTOMATICO);
		
		Calendar data = Calendar.getInstance(); //DATA ATUAL
											 //ANO,MES,DIA
		Calendar data2 = new GregorianCalendar(2000,Calendar.JANUARY,19);
		
		carro.setDataLancamento(data);
		
		//Formatar a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, hh:mm");
		System.out.println(sdf.format(data.getTime()));
		System.out.println(sdf.format(data2.getTime()));
		
		new Date("");
		
	}
	
}





