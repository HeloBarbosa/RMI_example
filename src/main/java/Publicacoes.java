package trabalho4;

import java.util.*;
import java.io.Serializable;

public class Publicacoes implements Serializable
{
	//private String[][] autores = new String[][] {};// {{Apelido1, Nome1}, {Apelido2, Nome2}, {Apelido3, Nome3}} Sobrenome,Nome a chave é o sobrenome
	
	private String autoresString;
	private String titulo;
	private String revista;
	private String doi;
	private String volume;
	private String numero;
	private String intervaloPaginas;

	private int numeroCitacoes;
	private int ano;

	public Publicacoes(String autoresString, String titulo, String revista, String doi, String volume,
			String numero, String intervaloPaginas, int numeroCitacoes, int ano)
	{
		this.autoresString = autoresString;
		this.titulo = titulo;
		this.revista = revista;
		this.doi = doi;
		this.volume = volume;
		this.numero = numero;
		this.intervaloPaginas = intervaloPaginas;
		this.numeroCitacoes = numeroCitacoes;
		this.ano = ano;
		

	}

	public String getAutoresString()
	{
		return autoresString;
	}

	public void setAutoresString(String autoresString)
	{
		this.autoresString = autoresString;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public String getRevista()
	{
		return revista;
	}

	public void setRevista(String revista)
	{
		this.revista = revista;
	}

	public String getDoi()
	{
		return doi;
	}

	public void setDoi(String doi)
	{
		this.doi = doi;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume(String volume)
	{
		this.volume = volume;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public String getIntervaloPaginas()
	{
		return intervaloPaginas;
	}

	public void setIntervaloPaginas(String intervaloPaginas)
	{
		this.intervaloPaginas = intervaloPaginas;
	}

	public int getNumeroCitacoes()
	{
		return numeroCitacoes;
	}

	public void setNumeroCitacoes(int numeroCitacoes)
	{
		this.numeroCitacoes = numeroCitacoes;
	}

	public int getAno()
	{
		return ano;
	}

	public void setAno(int ano)
	{
		this.ano = ano;
	}

	public String printPublicacao()
	{
		
		return autoresString+", \n"+titulo+", \n"+revista+", "+volume+"("+numero+"), "+intervaloPaginas+", "+ano+", DOI:"+doi+", (Vezes citado: "+numeroCitacoes+ ") \n";
	}



}
