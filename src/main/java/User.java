package trabalho4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class User implements Serializable
{
	private String nome;
	private String senha;
	private String email;
	private String afiliacao;
	private ArrayList<Publicacoes> listaPublicacoes = new ArrayList<Publicacoes>();

	public User(String nome, String email, String senha, String afiliacao)
	{
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.afiliacao = afiliacao;
		this.listaPublicacoes = listaPublicacoes;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAfiliacao()
	{
		return afiliacao;
	}

	public void setAfiliacao(String afiliacao)
	{
		this.afiliacao = afiliacao;
	}

	public ArrayList<Publicacoes> getPublicacoes()
	{
		return listaPublicacoes;
	}

	public void setPublicacoes(ArrayList<Publicacoes> listaPublicacoes)
	{
		this.listaPublicacoes = listaPublicacoes;
	}

	public void addUserPublicacoes(Publicacoes publicacao)
	{
		listaPublicacoes.add(publicacao);
	}

	public String listarOrdemDAno()
	{
		ArrayList<Publicacoes> pub = arrayOrdemAno();
		String printPub = "";
		
		for (int i=0; i<pub.size(); i++)
		{
			printPub += i+"-> "+pub.get(i).printPublicacao()+"\n";
		}
		return printPub;
	}

	
	public String listarOrdemCitacoes()
	{
		ArrayList<Publicacoes> pub = arrayOrdemCitacoes();
		String printPub = "";
		
		for (int i=0; i<pub.size(); i++)
		{
			printPub += i+"-> "+pub.get(i).printPublicacao()+"\n";
		}
		return printPub;
	}
	
	public ArrayList<Publicacoes> listaOrdemAno()
	{
		ArrayList<Integer> anos = new ArrayList<Integer>();

		for (int i = 0; i < listaPublicacoes.size(); i++)
		{
			anos.add(listaPublicacoes.get(i).getAno());
		}
		
		for (int i = 0; i < anos.size(); i++)
		{
			for (int j = 0; j < anos.size(); j++)
			{
				int i1 = anos.get(i);
				int j1 = anos.get(j);
				if (i1==j1 && i!=j)
				{
					anos.remove(i);
				}
			}
		}
		
		Collections.sort(anos, Collections.reverseOrder());

		ArrayList<Publicacoes> pub = new ArrayList<Publicacoes>();
		
		for (int i = 0; i < anos.size(); i++)
		{
			for (int j = 0; j < listaPublicacoes.size(); j++)
			{
				if (anos.get(i) == listaPublicacoes.get(j).getAno())
				{
					pub.add(listaPublicacoes.get(j));
				}
			}
		}
		return pub;
	}
	
	public ArrayList<Publicacoes> arrayOrdemAno()
	{
		ArrayList<Integer> anos = new ArrayList<Integer>();

		for (int i = 0; i < listaPublicacoes.size(); i++)
		{
			anos.add(listaPublicacoes.get(i).getAno());
		}
		
		for (int i = 0; i < anos.size(); i++)
		{
			for (int j = 0; j < anos.size(); j++)
			{
				int i1 = anos.get(i);
				int j1 = anos.get(j);
				if (i1==j1 && i!=j)
				{
					anos.remove(i);
				}
			}
		}
		
		Collections.sort(anos, Collections.reverseOrder());

		ArrayList<Publicacoes> pub = new ArrayList<Publicacoes>();
		
		for (int i = 0; i < anos.size(); i++)
		{
			for (int j = 0; j < listaPublicacoes.size(); j++)
			{
				if (anos.get(i) == listaPublicacoes.get(j).getAno())
				{
					pub.add(listaPublicacoes.get(j));
				}
			}
		}
		return pub;
	}
	
	public ArrayList<Publicacoes> arrayOrdemCitacoes()
	{
		ArrayList<Integer> cita = new ArrayList<Integer>();

		for (int i = 0; i < listaPublicacoes.size(); i++)
		{
			cita.add(listaPublicacoes.get(i).getNumeroCitacoes());
		}
		
		for (int i = 0; i < cita.size(); i++)
		{
			for (int j = 0; j < cita.size(); j++)
			{
				int i1 = cita.get(i);
				int j1 = cita.get(j);
				if (i1==j1 && i!=j)
				{
					cita.remove(i);
				}
			}
		}
		
		Collections.sort(cita, Collections.reverseOrder());

		ArrayList<Publicacoes> pub = new ArrayList<Publicacoes>();
		
		for (int i = 0; i < cita.size(); i++)
		{
			for (int j = 0; j < listaPublicacoes.size(); j++)
			{
				if (cita.get(i) == listaPublicacoes.get(j).getNumeroCitacoes())
				{
					pub.add(listaPublicacoes.get(j));
				}
			}
		}
		return pub;
	}
	
	public void removePublicacao (String DOI) 
	{
		
		for(int i =0 ; i<listaPublicacoes.size(); i++) 
		{
			if(listaPublicacoes.get(i).getDoi().equals(DOI)) 
			{
				listaPublicacoes.remove(i);
			}
		}
	}
	

}
