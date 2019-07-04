package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.PurchaseDao;
import model.dao.SalesDao;
import model.dao.StockDao;
import model.dao.SupplierDao;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.Stock;
import model.entities.Supplier;

public class MenuPurchase {

	public static void menu() throws ParseException {
		
		System.out.println("\n PURCHASE MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Insert a new purchase: "
				+ "\n(2): Select purchases between dates: " 
				+ "\n(3): Salect a purchase by ID: "								
				+ "\n(4): Exit. ");

		suppliersellector();
	}

	private static void suppliersellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 4 || choice < 1) {
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
			selectingDates();
		case 3:
			selectingId();				
		case 4:
			MainProgram.mainMenu();
		default:
			break;
		}

	}
	
	
	public static ProductDao instantiatingProduct() {
		ProductDao prdDao = DaoFactory.createProductDao();
		return prdDao;
	}
	
	public static StockDao instantiatingStock() {
		StockDao stkDao = DaoFactory.createStockDao();
		return stkDao;		
	}
	
	public static SupplierDao instantiatingSupplier() {
		SupplierDao supDao = DaoFactory.createSupplierDao();
		return supDao;
	}
		
	public static PurchaseDao instantiatingPurchase() {
		PurchaseDao prDao = DaoFactory.createPurchaseDao();
		return prDao;
	}
		
	
	
	public static void inserting() throws ParseException {	
		Scanner input = new Scanner(System.in);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");			

		System.out.println("\n=== purchase insert =====");
		System.out.println("Select one product: ");
		System.out.print("Product ID: ");
		Integer idP = input.nextInt();
		ProductDao prdDao = instantiatingProduct();
		List<Product> prd = prdDao.findOneProduct(idP);
		Product idProduct = prd.get(0);
		Double price = prd.get(0).getCostProduct();
		
		System.out.println("Select the supplier: ");
		System.out.print("Supplier ID: ");
		Integer idS = input.nextInt();
		SupplierDao supDao = instantiatingSupplier();
		List<Supplier> spl = supDao.findOne(idS);
		Supplier idSupplier = spl.get(0);
		input.nextLine();
		
		System.out.print("Lot number: ");
		String lot = input.nextLine();
		
		System.out.print("Quantity: ");
		float quant = input.nextFloat();		
		System.out.print("Date (DD/MM/YYYY): ");
		Date dt = date.parse(input.next());
		Double total = quant * price;
		
		PurchaseDao prDao = instantiatingPurchase();
		Purchase pch = new Purchase(null, quant, dt, idProduct, idSupplier, total);		
		prDao.insertPurchase(pch);	
		
		StockDao stkDao = instantiatingStock();
		Stock stk = new Stock(null, idProduct, pch, quant, total, lot);
		stkDao.insertInStock(stk);
		
		menu();
		input.close();
	}
	
	
	public static void selectingDates() throws ParseException {	
		Scanner input = new Scanner(System.in);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");	
		
		System.out.println("\n=== purchase list between dates =====");
		System.out.print("Begin Date (DD/MM/YYYY): ");
		Date bdt = date.parse(input.next());
		System.out.print("End Date (DD/MM/YYYY): ");
		Date edt = date.parse(input.next());
		PurchaseDao prDao = instantiatingPurchase();
		List<Purchase> purchase = prDao.listPurchase(bdt, edt);
		purchase.forEach(each -> 
					System.out.println("Product: "+each.getProduct().getNameProduct()
					+ " == Quantity: "+each.getQuantity()
					+ " == Supplier: "+each.getSupplier().getNameSupplier()
					+ " == Purchase date: "+each.getDatePurchase()
					+ " == Total: $"+each.getTotal())
					);
		menu();
		input.close();
	}
	
	
	public static void selectingId() throws ParseException {		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n=== Select purchase by id =====");
		System.out.println("Id purchase: ");
		Integer id = input.nextInt();
		PurchaseDao prDao = instantiatingPurchase();
		List<Purchase> purchase2 = prDao.listOnePurchase(id);
		purchase2.forEach(each -> 
			System.out.println("Product: "+each.getProduct().getNameProduct()
					+ " == Quantity: "+each.getQuantity()
					+ " == Supplier: "+each.getSupplier().getNameSupplier()
					+ " == Purchase date: "+each.getDatePurchase()
					+ " == Total: $"+each.getTotal())
					);		
		menu();
		input.close();

	}

}
