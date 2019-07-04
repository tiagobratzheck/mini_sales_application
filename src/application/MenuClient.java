package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

public class MenuClient {

	public static void menu() throws ParseException {

		System.out.println("\n CLIENT MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Insert a new client: "
				+ "\n(2): Update client: " 
				+ "\n(3): Delete client: "
				+ "\n(4): List all client: "
				+ "\n(5): Find one client: "
				+ "\n(6): Exit. ");

		clientsellector();
	}

	private static void clientsellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 6 || choice < 1) {
				IllegalArgumentException error = new IllegalArgumentException();
				System.out.println(error.getMessage());
				clientsellector();
			} else {
				controller(choice);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please ,try one of the option in the list above!");
			clientsellector();
		}
		input.close();
	}

	public static void controller(int choice) throws ParseException {
		switch (choice) {
		case 1:
			inserting();
		case 2:
			updating();
		case 3:
			deleting();
		case 4:
			listing();
		case 5:
			finding();
		case 6:
			MainProgram.mainMenu();
		default:
			break;
		}

	}

	public static void inserting() throws ParseException {
		Scanner input = new Scanner(System.in);

		ClientDao cliDao = DaoFactory.createClientDao();

		System.out.println("\n=== client insert =====");
		System.out.print("client name: ");
		String name = input.nextLine();

		Client cli = new Client(null, name);
		cliDao.insertClient(cli);
		System.out.println("Inserted!");
		menu();

		input.close();

	}

	public static void updating() throws ParseException {
		Scanner input = new Scanner(System.in);

		ClientDao cliDao = DaoFactory.createClientDao();
		System.out.println("\n=== client update =====");
		System.out.print("Enter the client ID: ");
		Integer id = input.nextInt();
		input.nextLine();
		System.out.print("New name: ");
		String newName = input.nextLine();
		Client upcli = new Client(id, newName);
		cliDao.updateClient(upcli);
		System.out.println("Updated!");
		menu();

		input.close();

	}

	public static void deleting() throws ParseException {
		Scanner input = new Scanner(System.in);

		ClientDao cliDao = DaoFactory.createClientDao();
		System.out.println("\n=== client delete =====");
		System.out.print("Client ID: ");
		Integer id1 = input.nextInt();
		cliDao.deleteClient(id1);
		System.out.println("Client deleted!");
		menu();

		input.close();

	}

	public static void listing() throws ParseException {

		ClientDao cliDao = DaoFactory.createClientDao();
		System.out.println("\n=== find all clients =====");
		List<Client> lcli = cliDao.findClients();
		lcli.forEach(cl -> System.out.println(cl));
		menu();

		

	}

	public static void finding() throws ParseException {
		Scanner input = new Scanner(System.in);

		ClientDao cliDao = DaoFactory.createClientDao();
		System.out.println("\n=== find one client =====");
		System.out.print("client name: ");
		Integer id3 = input.nextInt();
		List<Client> result = cliDao.findOneClient(id3);
		result.forEach(cl -> System.out.println(cl));
		menu();

		input.close();

	}

}
