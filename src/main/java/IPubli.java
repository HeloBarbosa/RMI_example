package trabalho4;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface IPubli extends Remote
{
	public boolean registrarUsuario(String nome, String email, String senha, String afiliacao) throws RemoteException;

	public void guardarListaUsuarios() throws RemoteException;

	public void abrirListaUsuarios() throws RemoteException;

	public void guardarListaPublicacoes() throws RemoteException;

	public void abrirListaPublicacoes() throws RemoteException;

	public boolean verificarCredenciais(String email, String senha) throws RemoteException;

	public User getUser(String login, String senha) throws RemoteException;

	public String nomeUsuario(String email) throws RemoteException;

	public void addPublication(String email, String nomes, String titulo, String revista, String doi, String volume,
			String numero, String intervalo, int numeroCitacoes, int ano) throws RemoteException;

	public String printListarOrdemDAno(String email) throws RemoteException;

	public String printListarOrdemCitacoes(String email) throws RemoteException;

	public boolean removerPublicacao(String email, int indice) throws RemoteException;

	public boolean compararDOI(String doi) throws RemoteException;

	public boolean compararNomes(String email, String autores) throws RemoteException;

	public String printPublicacoesPossiveis(String email) throws RemoteException;

	public boolean adicionarPublicacaoDoSistema(String email, String escolha) throws RemoteException;
	
	public String printEstatisticas(String email) throws RemoteException;
}
