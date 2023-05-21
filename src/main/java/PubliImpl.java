package trabalho4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class PubliImpl extends UnicastRemoteObject implements IPubli
{
	ArrayList<User> listaUsuarios = new ArrayList<User>();
	ArrayList<Publicacoes> listaPublicacoes = new ArrayList<Publicacoes>();

	public PubliImpl() throws IOException
	{
		File arquivo = new File("listaAutores.tmp");
		if (!arquivo.exists()) // Ver se o arquivo existe
		{
			System.out.println("arquivo da lista de usuarios criado");
			arquivo.createNewFile(); // Cria um arquivo novo
		}
		else if (arquivo.length() > 0) // Ver se o arquivo não está vazio
		{
			// Ler o arquivo caso não esteja vazio
			abrirListaUsuarios();
		}

		File arquivo2 = new File("listaPublicacoes.tmp");
		if (!arquivo2.exists()) // Ver se o arquivo existe
		{
			System.out.println("arquivo da lista de publicações criado");
			arquivo2.createNewFile(); // Cria um arquivo novo
		}
		else if (arquivo2.length() > 0) // Ver se o arquivo não está vazio
		{
			// Ler o arquivo caso não esteja vazio
			abrirListaPublicacoes();
		}
	}

	public ArrayList<User> getUserList() throws RemoteException
	{
		return null;
	}

	public ArrayList<Publicacoes> getPublicacoesList() throws RemoteException
	{

		return null;
	}

	public boolean verificarCredenciais(String login, String senha) throws RemoteException
	{
		for (int i = 0; i < listaUsuarios.size(); i++)
		{
			if (listaUsuarios.get(i).getEmail().equals(login) && listaUsuarios.get(i).getSenha().equals(senha))
			{
				System.out.println(login + " entrou");
				return true;
			}
		}
		return false;
	}

	public User getUser(String login, String senha) throws RemoteException
	{
		User usuario = null;
		for (int i = 0; i < listaUsuarios.size(); i++)
		{
			if (listaUsuarios.get(i).getEmail().equals(login) && listaUsuarios.get(i).getSenha().equals(senha))
			{
				usuario = listaUsuarios.get(i);
			}
		}
		return usuario;
	}

	public boolean registrarUsuario(String nome, String email, String senha, String afiliacao) throws RemoteException
	{
		for (int i = 0; i < listaUsuarios.size(); i++) //Se o email do usuario ja estiver cadastrado retorna falso
		{
			String buscarEmail = listaUsuarios.get(i).getEmail();
			if (buscarEmail.equals(email))
			{
				return false;
			}

		}

		User usuario = new User(nome, email, senha, afiliacao);
		listaUsuarios.add(usuario);
		System.out.println(nome + " registrado");

		guardarListaUsuarios();
		guardarListaPublicacoes();

		return true;
	}

	public void addPublication(String email, String autores, String titulo, String revista, String doi, String volume,
			String numero, String intervalo, int numeroCitacoes, int ano) throws RemoteException
	{
		Publicacoes publicacao = new Publicacoes(autores, titulo, revista, doi, volume, numero, intervalo,
				numeroCitacoes, ano);
		listaPublicacoes.add(publicacao);
		System.out.println(doi + " adicionada ao sistema");

		User usuario = emailToUser(email);
		usuario.addUserPublicacoes(publicacao);
		System.out.println(doi + " adicionada ao usuario " + usuario.getNome());

		guardarListaUsuarios();
		guardarListaPublicacoes();
	}

	public void guardarListaUsuarios() throws RemoteException
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("listaAutores.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(listaUsuarios);
			oos.flush();

			oos.close();
			fos.close();
			System.out.println("lista de usuarios guardada");

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}

	public void abrirListaUsuarios() throws RemoteException
	{
		try
		{
			FileInputStream fis = new FileInputStream("listaAutores.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);

			listaUsuarios = (ArrayList<User>) ois.readObject();

			ois.close();
			fis.close();

			System.out.println("lista de usuarios foi importada");
			System.out.println(listaUsuarios.size() + " usuarios registrados no sistema:");
			for (int i = 0; i < listaUsuarios.size(); i++)
			{
				System.out.println(listaUsuarios.get(i).getNome());
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}

	public void guardarListaPublicacoes() throws RemoteException
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("listaPublicacoes.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(listaPublicacoes);
			oos.flush();

			oos.close();
			fos.close();
			System.out.println("lista de publicacoes guardada");

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}

	public void abrirListaPublicacoes() throws RemoteException
	{
		try
		{
			FileInputStream fis = new FileInputStream("listaPublicacoes.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);

			listaPublicacoes = (ArrayList<Publicacoes>) ois.readObject();

			ois.close();
			fis.close();

			System.out.println("lista de publicacoes foi importada");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}

	public String nomeUsuario(String email) throws RemoteException
	{
		User usuario = emailToUser(email);
		String nome = usuario.getNome();
		return nome;
	}

	public String printListarOrdemDAno(String email) throws RemoteException
	{
		User usuario = emailToUser(email);
		return usuario.listarOrdemDAno();
	}

	public String printListarOrdemCitacoes(String email) throws RemoteException
	{
		User usuario = emailToUser(email);
		return usuario.listarOrdemCitacoes();
	}

	public User emailToUser(String email)
	{
		User usuario = null;
		for (int i = 0; i < listaUsuarios.size(); i++)
		{
			if (listaUsuarios.get(i).getEmail().equals(email))
			{
				usuario = listaUsuarios.get(i);
			}
		}
		return usuario;
	}

	public boolean removerPublicacao(String email, int indice) throws RemoteException
	{
		User usuario = emailToUser(email);
		ArrayList<Publicacoes> pub = usuario.listaOrdemAno();

		if (pub.size() >= indice && indice >= 0)
		{
			usuario.removePublicacao(pub.get(indice).getDoi());
			System.out.println(
					"A publicacao" + pub.get(indice).getDoi() + "foi removida do usuario " + usuario.getNome());

			guardarListaUsuarios();
			guardarListaPublicacoes();

			return true;
		}
		return false;
	}

	public boolean compararDOI(String doi) throws RemoteException
	{
		// verificar se doi ja existe no sistema
		for (int i = 0; i < listaPublicacoes.size(); i++)
		{
			String doiPub = listaPublicacoes.get(i).getDoi();
			if (doiPub.equals(doi))
			{
				return false;
			}
		}
		return true;
	}

	public boolean compararNomes(String email, String autores) throws RemoteException
	{
		User usuario = emailToUser(email);
		String[] Nomes = autores.split(";");

		for (int i = 0; i < Nomes.length; i++)
		{
			
			String[] nomeCompletoNoArtigo = Nomes[i].split(",");
			String apelidoNoArtigo = nomeCompletoNoArtigo[0];
			String nomeNoArtigo = nomeCompletoNoArtigo[1];

			String[] nomeCompletoUsuario = usuario.getNome().split(" ");
			String apelidoUsuario = nomeCompletoUsuario[nomeCompletoUsuario.length - 1];
			String nomeUsuario = "";
			for (int j = 0; j < nomeCompletoUsuario.length - 1; j++)
			{
				nomeUsuario += nomeCompletoUsuario[j];
			}
			if (apelidoUsuario.equals(apelidoNoArtigo) && nomeUsuario.equals(nomeNoArtigo))
			{
				
				return true;
			}
		}
		
		return false;
	}

	public ArrayList<Publicacoes> listaPublicacoesPossiveis(String email) throws RemoteException
	{
		User usuario = emailToUser(email);
		ArrayList<Publicacoes> listaNovaPublicacoes = new ArrayList<Publicacoes>();

		for (int i = 0; i < listaPublicacoes.size(); i++)
		{
			boolean i_adicionado = false;

			for (int j = 0; j < usuario.getPublicacoes().size(); j++)
			{
				if (listaPublicacoes.get(i).getDoi().equals(usuario.getPublicacoes().get(j).getDoi()))
				{
					i_adicionado = true;
				}
			}
			if (i_adicionado == false)
			{
				for (int j = 0; j < usuario.getPublicacoes().size(); j++)
				{
					if (compararNomes(email, listaPublicacoes.get(i).getAutoresString()) == true
							&& i_adicionado == false)
					{
						listaNovaPublicacoes.add(listaPublicacoes.get(i));
						i_adicionado = true;
					}
				}
			}
		}
		return listaNovaPublicacoes;
	}

	public String printPublicacoesPossiveis(String email) throws RemoteException
	{
		ArrayList<Publicacoes> pub = listaPublicacoesPossiveis(email);
		String printPub = "";

		for (int i = 0; i < pub.size(); i++)
		{

			printPub += i + "-> " + pub.get(i).printPublicacao() + "\n";
		}
		return printPub;
	}

	public boolean adicionarPublicacaoDoSistema(String email, String escolha) throws RemoteException
	{
		User usuario = emailToUser(email);
		String[] listaIndices = escolha.split(",");
		ArrayList<Publicacoes> pub = listaPublicacoesPossiveis(email);
		for (int i = 0; i < listaIndices.length; i++)
		{
			// Verificar se é um inteiro
			String input = listaIndices[i];
			for (int k = 0; k < input.length(); k++)
			{
				if (Character.isDigit(input.charAt(k)) == false)
				{
					return false;
				}
			}

			// Caso selecione um numero maior do que existe
			if (Integer.parseInt(listaIndices[i]) > pub.size() || Integer.parseInt(listaIndices[i]) < 0)
			{
				return false;
			}

		}

		for (int i = 0; i < listaIndices.length; i++)
		{
			for (int j = 0; j < pub.size(); j++)
			{

				int indice = Integer.parseInt(listaIndices[i]);

				if (indice == j)
				{
					usuario.addUserPublicacoes(pub.get(j));
					System.out.println(pub.get(j).getDoi() + " adicionada ao usuario " + usuario.getNome());
				}
			}
		}
		guardarListaUsuarios();
		guardarListaPublicacoes();

		return true;
	}

	public String printEstatisticas(String email) throws RemoteException
	{
		User usuario = emailToUser(email);
		ArrayList<Publicacoes> pub = usuario.arrayOrdemCitacoes();

		int sumCitacoes = 0; // Numero total citações
		for (int i = 0; i < pub.size(); i++)
		{
			sumCitacoes += pub.get(i).getNumeroCitacoes();
		}

		int h = 0; // Indice h
		while (h < pub.size() && pub.get(h).getNumeroCitacoes() >= h + 1)
		{
			h += 1;
		}

		int indi = 0; //Indice i
		for (int i = 0; i < pub.size(); i++)
		{
			if (pub.get(i).getNumeroCitacoes() >= 10)
			{
				indi += 1;
			}
		}

		return "Suas estatísticas no momento são: "+"\n"+"Numero total de citações: "+sumCitacoes+"\n"+"Índice H = "+h+"\n"+"Índice i10 = "+indi;

	}

}