package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SupplierDao;
import model.entities.Supplier;

public class MenuSupplier {	
	
	
	public static void menu() throws ParseException {
		
		System.out.println("\n SUPPLIER MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Insert a new supplier: "
				+ "\n(2): Update supplier: " 
				+ "\n(3): Delete supplier: "
				+ "\n(4): List all suppliers: "
				+ "\n(5): Find one supplier: "
				+ "\n(6): Exit. ");

		suppliersellector();
	}

	private static void suppliersellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 6 || choice < 1) {
				IllegalArgumentException error = new IllegalArgumentException();
				System.out.println(error.getMessage());
				suppliersellector();
			} else {
				controller(choice);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please ,try one of the option in the list above!");
			suppliersellector();
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
	
	
	public static SupplierDao instantiating() {
		SupplierDao supDao = DaoFactory.createSupplierDao();
		return supDao;
	}
	
	
	public static void inserting() throws ParseException{		
		Scanner input = new Scanner(System.in);
		
		SupplierDao supDao = instantiating();		
		System.out.println("\n=== supplier insert =====");
		System.out.print("Supplier name: ");
		String name = input.nextLine();		
		Supplier sup = new Supplier(null, name);		
		supDao.insertSupplier(sup);
		System.out.println("Inserted!");
		
		menu();
		input.close();
	}
	
	
	public static void updating() throws ParseException{	
		Scanner input = new Scanner(System.in);
		
		SupplierDao supDao = instantiating();
		System.out.println("\n=== supplier update =====");
		System.out.print("Enter the supplier ID: ");
		Integer id = input.nextInt();
		input.nextLine();
		System.out.print("New name: ");
		String newName = input.nextLine();
		Supplier upsup = new Supplier(id, newName);
		supDao.updateSupplier(upsup);
		System.out.println("Updated!");
		
		menu();
		input.close();
	}
	
	
	public static void deleting() throws ParseException{	
		Scanner input = new Scanner(System.in);
		
		SupplierDao supDao = instantiating();		
		System.out.println("\n=== supplier delete =====");
		System.out.print("Supplier ID: ");
		Integer id1 = input.nextInt();
		supDao.deleteSupplier(id1);
		System.out.println("Supplier deleted!");
		
		menu();
		input.close();
	}
		
	
	public static void listing() throws ParseException{	
		Scanner input = new Scanner(System.in);
		
		SupplierDao supDao = instantiating();			
		System.out.println("\n=== find all suppliers =====");
		List<Supplier> lsup = supDao.findSuppliers(); 
		for (Supplier sp : lsup) {
			System.out.println("ID :"+sp.getIdSupplier()
					+ " == Name: "+sp.getNameSupplier()
					);
		}
		
		menu();
		input.close();
	}
		
	
	public static void finding() throws ParseException{	
		Scanner input = new Scanner(System.in);
		
		SupplierDao supDao = instantiating();			
		System.out.println("\n=== find one supplier =====");
		System.out.print("Supplier name: ");
		Integer idSupplier = input.nextInt();
		List<Supplier> result = supDao.findOne(idSupplier);
		result.forEach(sup -> System.out.println("ID: "+sup.getIdSupplier()
								+ " == Name: "+sup.getNameSupplier()));
		
		menu();		
		input.close();
	}		

}
