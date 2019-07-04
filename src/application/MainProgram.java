package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {

	public static void main(String[] args) throws ParseException {
		
		mainMenu();
		
	  /*
	   * 
	   * 
	   * 
	   * 
	   */
	}	
	
	public static void mainMenu() throws ParseException {		
		System.out.println("\n Welcome to the MINI SALES application! This is the main menu."
				+ "\n Enter one of the option below: "
				+ "\n(1): Client menu: "
				+ "\n(2): Product menu: "
				+ "\n(3): Stock menu: "
				+ "\n(4): Supplier menu: "
				+ "\n(5): Purchase menu: "
				+ "\n(6): Sales menu: "
				+ "");
		sellector();
		
	}
		
	private static void sellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		try {
			System.out.print("Option: ");
			int choice = input.nextInt();	
			
			if(choice > 6 || choice < 1) {
				IllegalArgumentException error = new IllegalArgumentException();
				System.out.println(error.getMessage());
				sellector();					
			}
			controller(choice);
		}					
		catch(InputMismatchException e){
			System.out.println("Please ,try one of the option in the list above!");
		sellector();
		
		
		}
		
		input.close();			
		
	}
	
	public static void controller(int choice) throws ParseException {
		switch (choice) {
		case 1:
			MenuClient.menu();			
		case 2:			
			MenuProduct.menu();		
		case 3:
			MenuStock.menu();
		case 4:
			MenuSupplier.menu();			
		case 5:
			MenuPurchase.menu();			
		case 6:
			MenuSales.menu();
		default:
			break;
		}
				
	}
	
}

