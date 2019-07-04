package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.dao.SalesDao;
import model.dao.StockDao;
import model.dao.SupplierDao;
import model.entities.Client;
import model.entities.Product;
import model.entities.Sales;
import model.entities.Stock;

public class MenuSales {

	public static void menu() throws ParseException {
		
		System.out.println("\n SALES MENU " 
				+ "\nEnter one of the option below: " 
				+ "\n(1): Insert a new sale: "
				+ "\n(2): Select sale by ID: " 
				+ "\n(3): Salect sales between dates: "
				+ "\n(4): Select client sales: "				
				+ "\n(5): Exit. ");

		suppliersellector();
	}

	private static void suppliersellector() throws ParseException {
		Scanner input = new Scanner(System.in);
		System.out.print("Option: ");
		try {
			int choice = input.nextInt();
			if (choice > 5 || choice < 1) {
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
			selectingId();
		case 3:
			selectingDates();
		case 4:
		    selectingClient();		
		case 5:
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
	
	public static ClientDao instantiatingClient() {
		ClientDao cltDao = DaoFactory.createClientDao();
		return cltDao;
	}
		
	public static SalesDao instantiatingSales() {
		SalesDao slDao = DaoFactory.createSalesDao();
		return slDao;
	}
		
	
	public static void inserting() throws ParseException{	
		Scanner input = new Scanner(System.in);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");		 		 

		System.out.println("\n=== sales insert =====");
		
		// Seleção do produto a ser vendido;
		System.out.println("Select one product: ");
		System.out.print("Product ID: ");
		Integer idP = input.nextInt();
		ProductDao prdDao = instantiatingProduct();
		List<Product> prd = prdDao.findOneProduct(idP);
		
		// Buscar o produto do estoque;
		Product idProduct = prd.get(0);
		StockDao stkDao = instantiatingStock();
		List<Stock> stock = stkDao.returnProduct(idProduct.getIdProduct());
		
		// Sort dos itens pela data da compra;
		Collections.sort(stock, (date1, date2) -> {
			return date1.getPurchase().getDatePurchase().compareTo(date2.getPurchase().getDatePurchase());
		});
		
		// Filtro dos itens disponíveis em estoque;
		Stream<Stock> filtered_stock = stock.stream().filter(p -> p.getQuantity() > 0);
				
		// Lista para escolha a partir do numero do lote;
		filtered_stock.forEach(item -> {			
			System.out.print("Product: " + item.getProduct().getNameProduct());
			System.out.print(" -- Lot number: " + item.getLot_number());
			System.out.print(" -- Quantity available: " + item.getQuantity());	
			System.out.print(" -- Unit cost: " +item.getProduct().getCostProduct());
			System.out.println(" -- Purchase date: " + item.getPurchase().getDatePurchase());
		});
		
		// Seleção do cliente;		
		System.out.println("Select the client: ");
		System.out.print("Client ID: ");
		Integer idS = input.nextInt();
		input.nextLine();
		ClientDao cltDao = instantiatingClient();
		List<Client> clt = cltDao.findOneClient(idS);			
		Client idClient = clt.get(0);
		clt.forEach(client -> {
			System.out.print("ID: " + client.getIdClient());
			System.out.println(" -- Name: " + client.getName());
		});
		
		// Determinar o preço, a quatidade, a data da venda e qual o lote;
		System.out.print("Price: ");
		Double price = input.nextDouble();		
		System.out.print("Quantity: ");
		float quant = input.nextFloat();	
		System.out.print("Product lot number: ");
		String lot_number = input.next();
		Double inStock = stkDao.totalQtdLot(lot_number);
		Double new_value = inStock - quant;
						
		System.out.print("Date (DD/MM/YYYY): ");
		Date dt = date.parse(input.next());
		Double total = quant * price;
		
		Sales sales = new Sales(null, price, dt, idClient, idProduct, quant, total);	
		System.out.println(sales);
		
		// Update stock;
		stkDao.removeFromStock(new_value, lot_number);
		
		// Register sale;
		SalesDao slDao = instantiatingSales();
		slDao.insertSales(sales);
		
		System.out.println("Inserted!");
		menu();		
		
	}
	
	
	public static void selectingId() throws ParseException{	
		Scanner input = new Scanner(System.in);
		System.out.println("\n=== select one sale =====");
		System.out.println("Id sale: ");
		Integer id = input.nextInt();
		SalesDao slDao = instantiatingSales();
		List<Sales> sale = slDao.listOnesale(id);
		sale.forEach(each -> { 
			System.out.println("Product : "+ each.getidProduct().getNameProduct()
					+ " == Client: "+each.getClient().getName()
					+ " == Quantity: " +each.getQuantity()
					+ " == Sale date: "+each.getSaleDate()
					+ " == Total: $"+each.getTotal());
			}
		);
		menu();
		input.close();
	}
	
	
	public static void selectingDates() throws ParseException {	
		Scanner input = new Scanner(System.in);
		System.out.println("\n=== sales list between dates =====");		
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");	
		
		System.out.print("Begin Date (DD/MM/YYYY): ");
		Date bdt = date.parse(input.next());
		System.out.print("End Date (DD/MM/YYYY): ");
		Date edt = date.parse(input.next());
		SalesDao slDao = instantiatingSales();
		List<Sales> sales2 = slDao.listSales(bdt, edt);
		sales2.forEach(each -> 
				System.out.println("Product : "+ each.getidProduct().getNameProduct()
				+ " == Client: "+each.getClient().getName()
				+ " == Quantity: " +each.getQuantity()
				+ " == Sale date: "+each.getSaleDate()
				+ " == Total: $"+each.getTotal())
				);		
		menu();
		input.close();
	}
		
	
	public static void selectingClient() throws ParseException {		
		Scanner input = new Scanner(System.in);
		System.out.println("\n=== select client sales =====");
		System.out.println("Id client: ");
		Integer id2 = input.nextInt();
		SalesDao slDao = instantiatingSales();
		List<Sales> sale2 = slDao.listClientSales(id2);
		sale2.forEach(each -> 
			System.out.println("Product : "+ each.getidProduct().getNameProduct()
					+ " == Client: "+each.getClient().getName()
					+ " == Quantity: " +each.getQuantity()
					+ " == Sale date: "+each.getSaleDate()
					+ " == Total: $"+each.getTotal())
					);		
		
		menu();		
		input.close();
			
	}

}
