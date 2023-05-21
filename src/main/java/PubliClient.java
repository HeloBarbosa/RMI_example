package trabalho4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class PubliClient
{

	public static void main(String[] args)
	{

		int counter = 0;
		boolean serverOn = true;
		while (counter < 10 && serverOn == true)
		{

			try
			{
				Registry registry = LocateRegistry.getRegistry("localhost", 8000);

				IPubli publication = (IPubli) registry.lookup("Publicacoes");


				while (serverOn == true)
				{

					System.out.println("======== Sistema Escolar ========");
					System.out.println("\n" + "1-Registrar um autor novo" + "\n" + "2-Login" + "\n" + "3-Sair" + "\n"
							+ "Insira sua opção:");

					Scanner scan = new Scanner(System.in);

					if (scan.hasNextInt())
					{
						int resp = scan.nextInt();
						scan.nextLine();

						if (resp == 1)
						{
							System.out.println(
									"Você decidiu se registrar" + "\n" + "Por favor insira o nome (Ex: Nome Apelido):");
							String nome = scan.nextLine();

							System.out.println("Insira seu e-mail:");
							String email = scan.nextLine();

							System.out.println("Insira uma senha:");
							String senha = scan.nextLine();

							System.out.println("Insira a sua afiliação (entidade empregadora):");
							String afiliacao = scan.nextLine();

							boolean regisUsuario = publication.registrarUsuario(nome, email, senha, afiliacao);

							if (regisUsuario == true)
							{
								System.out.println("Cadastro efetuado com sucesso!");
								publication.guardarListaUsuarios();
							}
							else
							{
								System.out.println("Este usuário já existe!");
							}

						}
						else if (resp == 2)
						{
							System.out.println("Por favor insira o login: ");
							String login = scan.nextLine();
							System.out.println("Por favor insira a sua password ");
							String password = scan.nextLine();

							boolean menuAutor = publication.verificarCredenciais(login, password);
							if (menuAutor == false)
							{
								System.out.println("Credenciais incorretas, tente novamente!" + "\n");
							}
							else
							{
								// Se as verificações das credenciais estiverem corretas, abre-se o printMenuAutor
								System.out.println("Bem vindo " + publication.nomeUsuario(login));
							}

							while (menuAutor == true)
							{

								System.out.println("\n" + "O que deseja fazer?" + "\n" + "1-Listar publicações por ano"
										+ "\n" + "2-Listar publicações por citações" + "\n"
										+ "3-Adicionar uma publicação" + "\n" + "4-Remover uma publicação" + "\n"
										+ "5-Procurar e adicionar publicações" + "\n" + "6-Ver estatísticas" + "\n"
										+ "7-Sair");
								scan = new Scanner(System.in);

								if (scan.hasNextInt())
								{
									int respostaMenuAutor = scan.nextInt();
									scan.nextLine();

									if (respostaMenuAutor == 1) // Ordem decrescente ano
									{
										System.out.println("------------> Publicações <------------");
										if (publication.printListarOrdemDAno(login).length() == 0)
										{
											System.out.println("Você ainda não possui publicações.");
										}
										System.out.println(publication.printListarOrdemDAno(login));
									}
									else if (respostaMenuAutor == 2) // Ordem decrescente citacoes
									{
										System.out.println("------------> Publicações <------------");
										if (publication.printListarOrdemCitacoes(login).length() == 0)
										{
											System.out.println("Você ainda não possui publicações.");
										}
										System.out.println(publication.printListarOrdemCitacoes(login));
									}
									else if (respostaMenuAutor == 3)
									{
										System.out.println(
												"Por favor insira os nomes dos autores (Separado por vírgula Ex: Apelido1,Nome1;Apelido2,Nome2;...) :");
										String nomes = scan.nextLine();

										System.out.println("Insira o título:");
										String titulo = scan.nextLine();

										System.out.println("Insira a revista:");
										String revista = scan.nextLine();

										System.out.println("Insira o doi:");
										String doi = scan.nextLine();

										System.out.println("Insira o volume:");
										String volume = scan.nextLine();

										System.out.println("Insira o numero:");
										String numero = scan.nextLine();

										System.out.println("Insira o intervalo de páginas (Ex: 10-20):");
										String intervalo = scan.nextLine();

										boolean numC = true;
										int numeroCitacoes = 0;

										while (numC == true)
										{
											System.out.println("Insira o numero de citações");
											if (scan.hasNextInt())
											{
												numeroCitacoes = scan.nextInt();
												numC = false;
											}
											else
											{
												System.out.println("Input incorreto, tente novamente!");
												numC = true;
											}
										}

										boolean numC1 = true;
										int ano = 0;

										while (numC1 == true)
										{
											System.out.println("Insira o ano:");

											if (scan.hasNextInt())
											{
												ano = scan.nextInt();
												scan.nextLine();

												numC1 = false;
											}
											else
											{
												System.out.println("Input incorreto, tente novamente!");
												numC1 = true;
											}
										}

										boolean verificarPublicacaoDOI = publication.compararDOI(doi);
										boolean verificarPublicacaoNome = publication.compararNomes(login, nomes);

										if (verificarPublicacaoDOI == true && verificarPublicacaoNome == true)
										{
											publication.addPublication(login, nomes, titulo, revista, doi, volume,
													numero, intervalo, numeroCitacoes, ano);
											System.out.println("A publicação foi adicionada!");
										}
										else if (verificarPublicacaoDOI == false)
										{
											System.out.println("A publicação já existe!");
										}
										else
										{
											System.out.println("Seu nome de usuario não consta nesta publicação!");
										}

									}
									else if (respostaMenuAutor == 4)
									{
										System.out.println("------------> Publicações <------------");
										if (publication.printListarOrdemDAno(login).length() == 0)
										{
											System.out.println("Você não possui publicações para serem removidas!");
										}
										else
										{
											System.out.println(publication.printListarOrdemDAno(login));
											System.out.println(
													"\n" + "Qual publicação você deseja remover?(Escolha o índice)");

											boolean publicacaoRemovida = false;
											if (scan.hasNextInt())
											{
												int resposta = scan.nextInt();
												publicacaoRemovida = publication.removerPublicacao(login, resposta);
											}
											else
											{
												System.out.println("Por favor, insira um índice!");
											}
											if (publicacaoRemovida == true)
											{
												System.out.println("A publicação foi removida com sucesso!");
											}
											else
											{
												System.out.println("Input inválido.");
											}
										}
									}
									else if (respostaMenuAutor == 5)
									{
										System.out.println("------------> Publicações Sugeridas <------------");
										// System.out.println(publication.printPublicacoesPossiveis(login).length());
										if (publication.printPublicacoesPossiveis(login).length() == 0)
										{
											System.out.println("Não há publicações sugeridas com o seu nome");
										}
										else
										{
											System.out.println(publication.printPublicacoesPossiveis(login));
											System.out.println(
													"Por favor selecione o/s índice/s que deseja adicionar (Se mais de um, separar por vírgula):");
											String escolha = scan.nextLine();

											boolean veriEscolha = publication.adicionarPublicacaoDoSistema(login,
													escolha);

											if (veriEscolha == true)
											{

												System.out.println("Opção " + escolha + " adicionada com sucesso.");
											}
											else
											{
												System.out.println("Input inválido! ");
											}
										}

									}
									else if (respostaMenuAutor == 6)
									{

										System.out.println("------------> Estatísticas <------------");

										System.out.println(publication.printEstatisticas(login));
									}
									else if (respostaMenuAutor == 7)
									{
										System.out.println("Você escolheu sair da aplicação.");
										menuAutor = false;
										serverOn = false;
									}
									else
									{
										System.out.println("Você deve inserir um input correto.");
									}
								}
								else
								{
									System.out.println("Por favor insira um input correto.");
								}
							}
						}
						else if (resp == 3)
						{
							System.out.println("Você escolheu sair da aplicação.");
							serverOn = false;
						}
						else
						{
							System.out.println("Você deve inserir um input correto.");
							serverOn = true;
						}
					}
					else
					{
						System.out.println("Você deve inserir um input correto.");
						serverOn = true;
					}
				}

			}

			catch (Exception e)
			{
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException e1)
				{

				}

				System.err.println("Ocorreu um erro: ");
				e.printStackTrace();

				counter += 1;

				System.out.println(
						"Servidor não encontrado, tentaremos novamente em 3 segundos (" + counter + " tentativas!!!)");
			}
		}
		if (counter >= 10)
		{
			System.out.println("A aplicação foi encerrada após 10 tentativas!");
		}
	}

}
