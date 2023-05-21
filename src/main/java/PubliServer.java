package trabalho4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PubliServer
{
	public static void main(String[] args)
	{
		try
		{			
			PubliImpl obj = new PubliImpl();

			Registry registry = LocateRegistry.createRegistry(8000);

			registry.bind("Publicacoes", obj);

			System.out.println("Pronto para server-te ;)");

		}
		catch (Exception e)
		{
			System.err.println("Ocorreu um erro:");
			e.printStackTrace();
		}
	}
}
