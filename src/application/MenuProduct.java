package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;


public class MenuProduct {
	
	
	public static void menu() throws ParseException {

		System.out.println("\n PRODUCT MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Insert a new product: "
				+ "\n(2): Update product: " 
				+ "\n(3): Delete product: "
				+ "\n(4): List all products: "
				+ "\n(5): Find one product: "
				+ "\n(6): Exit. ");

		productsellector();
	}

	private static void productsellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 6 || choice < 1) {
				IllegalArgumentException error = new IllegalArgumentException();
				System.out.println(error.getMessage());
				productsellector();
			} else {
				controller(choice);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please ,try one of the option in the list above!");
			productsellector();
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
	
	
	public static ProductDao instantiating() {
		ProductDao prdDao = DaoFactory.createProductDao();
		return prdDao;
	}
	
	
	public static void inserting() throws ParseException{		
		Scanner input = new Scanner(System.in);		
		ProductDao prdDao = instantiating();
		
		System.out.println("\n=== TEST 1: product insert =====");
		System.out.print("Product name: ");
		String name = input.nextLine();				
		System.out.print("Product cost: ");
		Double cost = input.nextDouble();		
		input.nextLine();
		System.out.print("Product unit: ");
		String un = input.nextLine();		
		
		Product prd = new Product(null, name, cost, un);		
		prdDao.insertProduct(prd);
		System.out.println("Inserted!");
		menu();

		input.close();
	}
	
	
	public static void updating() throws ParseException{			
		Scanner input = new Scanner(System.in);		
		ProductDao prdDao = instantiating();
		
		System.out.println("\n=== TEST 2: product update =====");
		System.out.print("Enter the product ID: ");
		Integer idp = input.nextInt();
		input.nextLine();
		System.out.print("Product name: ");
		String name1 = input.nextLine();			
		System.out.print("Product cost: ");
		Double cost1 = input.nextDouble();		
		input.nextLine();
		System.out.print("Product unit: ");
		String un1 = input.nextLine();		
		
		Product uprd = new Product(idp, name1, cost1, un1);		
		prdDao.updateProduct(uprd);
		System.out.println("Updated!");
		menu();

		input.close();
		
	}
		
	public static void deleting() throws ParseException{			
		Scanner input = new Scanner(System.in);		
		ProductDao prdDao = instantiating();
		
		System.out.println("\n=== TEST 3: product delete =====");
		System.out.print("Product ID: ");
		Integer id1 = input.nextInt();
		prdDao.deleteProduct(id1);
		System.out.println("Product deleted!");
		menu();

		input.close();
	}
	
	
	public static void listing() throws ParseException{		
		ProductDao prdDao = instantiating();		
		
		System.out.println("\n=== TEST 4: find all products =====");
		List<Product> lprd = prdDao.findProducts(); 
		
		for (Product pr : lprd) {
			System.out.println("ID: "+pr.getIdProduct()
			+" == Description: "+pr.getNameProduct()
			+" == UN: "+pr.getUnit()
			+" == Cost: "+pr.getCostProduct());			
		}
		
		menu();

	}
	
	
	public static void finding() throws ParseException{			
		Scanner input = new Scanner(System.in);		
		ProductDao prdDao = instantiating();		
		
		System.out.println("\n=== TEST 5: find one product =====");
		System.out.print("Product ID: ");
		Integer idProduct = input.nextInt();
		List<Product> fprd = prdDao.findOneProduct(idProduct);
		
		fprd.forEach(item -> 
			System.out.println("ID: "+ item.getIdProduct()
			+" == Description: " + item.getNameProduct()
			+" == Price: " + item.getCostProduct()
			)
		);
		
		menu();
		
		input.close();
		
	}		
		
}
