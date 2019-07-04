package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.StockDao;
import model.entities.Stock;

public class MenuStock {

	public static void menu() throws ParseException {

		System.out.println("\n STOCK MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Return total in stock: "
				+ "\n(2): Check product in stock: " 
				+ "\n(3): Exit. ");

		stocksellector();
	}

	private static void stocksellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 3 || choice < 1) {
				IllegalArgumentException error = new IllegalArgumentException();
				System.out.println(error.getMessage());
				stocksellector();
			} else {
				controller(choice);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please ,try one of the option in the list above!");
			stocksellector();
		}
		input.close();
	}

	public static void controller(int choice) throws ParseException {
		switch (choice) {
		case 1:
			returning();
		case 2:
			checking();
		case 3:
			MainProgram.mainMenu();
		default:
			break;
		}

	}

	public static StockDao instantiating() {
		StockDao stkDao = DaoFactory.createStockDao();
		return stkDao;
	}

	public static void returning() throws ParseException {
		Scanner input = new Scanner(System.in);
		StockDao stkDao = instantiating();

		System.out.println("\n=== return total in stock =====");
		Double total = stkDao.totalInStock();
		System.out.println("Total in stock: $"+total);
		menu();
		
		input.close();
	}

	public static void checking() throws ParseException {
		Scanner input = new Scanner(System.in);
		StockDao stkDao = instantiating();

		System.out.println("\n=== return one product in stock =====");
		System.out.println("Enter the id product: ");
		Integer id = input.nextInt();
		List<Stock> stock = stkDao.checkProductInStock(id);
		stock.forEach(stk -> {
			System.out.println("Product: " + stk.getProduct().getNameProduct()
			+" == Quantity: " +stk.getQuantity()
			+" == Total : $"+stk.getTotal());
		});
		menu();

		input.close();
	}

}
