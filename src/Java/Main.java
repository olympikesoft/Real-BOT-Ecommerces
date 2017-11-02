package Java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.itextpdf.text.Document;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Java.Classes.Database;
import Java.Classes.Messages;
import Java.Classes.Orders;
import Java.Classes.Person;
import Java.Classes.Product;

public class Main {
	private static Scanner scan;
	private static List<Messages> listMessages;
	private static List<Product> products;
	private static List<Person> persons;
	private static List<Orders> ordersList;
	static String type;

	public static void main(String[] args) {

		scan = new Scanner(System.in);
		// Define Lists ;
		listMessages = new ArrayList<Messages>();
		products = new ArrayList<Product>();
		persons = new ArrayList<Person>();
		ordersList = new ArrayList<Orders>();
		int control;

		ApresentarMenuPrincipal();

		do {

			switch (control = scan.nextInt()) {

			case 1: // Insert to Json File String comment;
				System.out
						.println("Insira o número de messagens que quer inserir"); //
				List<String> messages = new ArrayList<String>();
				int insert = scan.nextInt();
				System.out.println("Insira a messagens que quer inserir");

				for (int i = 0; i <= insert; i++) {

					messages.add(i, scan.next());
					Messages message = new Messages();
					String comment;
					message.setMessage(comment = scan.nextLine());

					message.setDate(new SimpleDateFormat("yyyyMMdd_HHmmss")
							.format(Calendar.getInstance().getTime()));
					listMessages.add(message);

				}

				try {
					InsertToJSONFile(listMessages);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 2:
				Database dbs = new Database();
				System.out.println("Escrever mensagem");

				do {
					type = scan.next();
					ResponseFromBot(listMessages);
					dbs.ListProducts(products);
					ChatProcess(type, listMessages, products);

				} while (!type.equals("X"));
				// Read File from Json
				break;

			case 3:
				Database db = new Database();
				db.ListProducts(products);
				try {
					DBFilePDF(products);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 4:
				Database DB = new Database();
				DB.ListPersons(persons);
				try {
					DBUserFilePDF(persons);
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				Database DBS = new Database();
				DBS.ListOrders(ordersList);

				// for (Orders orders : ordersList) {
				for (int i = 0; i < ordersList.size(); i++) {

					if (ordersList.get(i).getUser_id() == ordersList.get(i)
							.getPersonsList().get(0).getId()) {
						System.out.println("Order: "
								+ ordersList.get(i).getId()
								+ "Person \n"
								+ ordersList.get(i).getPersonsList().get(0)
										.getName());
					}
				}

			}

		} while (control != 4);

	}

	public static void InsertToJSONFile(List<Messages> messages)
			throws IOException {
		// Create a new JSONObject
		JSONObject jsonObject = new JSONObject();

		// Add the values to the jsonObject
		jsonObject.put("Messages", "Messages for Bot");
		// jsonObject.put("Age", "999");

		// Create a new JSONArray object
		JSONArray jsonArray = new JSONArray();

		// Add values to the jsonArray
		for (int index = 0; index < listMessages.size(); index++) {
			jsonArray.add(listMessages.get(index).getMessage());
		}

		// Add the jsoArray to jsonObject
		jsonObject.put("Text", jsonArray);

		// Create a new FileWriter object
		FileWriter fileWriter = new FileWriter("C:\\Logs\\sample.json");

		// Writting the jsonObject into sample.json
		try {
			fileWriter.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileWriter.close();

		System.out.println("JSON Object Successfully written to the file!!");

	}

	public static void ResponseFromBot(List<Messages> ListMessage) {

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("C:\\Logs\\sample.json"));

			JSONObject jsonObject = (JSONObject) obj;
			Messages message = new Messages();

			System.out.println("States are :");
			JSONArray listOfStates = (JSONArray) jsonObject.get("Text");
			Iterator iterator = listOfStates.iterator();
			int i = 0;
			List<String> strings = new ArrayList<String>();
			String value = "";
			while (iterator.hasNext()) {

				value = (String) iterator.next();
				strings.add(value);

				i++;
			}
			if (ListMessage.size() == 0) {

				for (int j = 0; j < strings.size(); j++) {
					message.setMessage(strings.get(j));
					ListMessage.add(message);

				}

			}
			System.err.println(ListMessage.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void ApresentarMenuPrincipal() {

		System.out.println("Menu Principal");
		System.out.println("1  - Inserir conversas ao Bot");
		System.out.println("2 - Falar com bot");
		System.out.println("3 - Ver stock produtos");
		System.out.println("4 - Ver utilizadores");

		/*
		 * System.out.println("4 - Efectuar Pagamento");
		 * System.out.println("5 - Ver serviços do Banco");
		 * System.out.println("7 - Ver registo de Actividade da Conta");
		 */

	}

	public static void ChatProcess(String answer, List<Messages> listMessages,
			List<Product> products) {
		Random rand = new Random();
		int max = 10, min = 1;

		int decider = (rand.nextInt((max - min) + 2) * min);

		// System.out.println(decider);

		// for(int i = 0; i < listMessages.size(); i++)

		if (decider == 0) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 1) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 2) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 3) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 4) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 5) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 6) {
			System.out.println(listMessages.get(decider).getMessage());
		} else if (decider == 7) {
			System.out.println(listMessages.get(decider).getMessage());
		} else {
			decider = 8;
		}

		if (answer.startsWith(answer))
			for (int index = 0; index < products.size(); index++) {
				if (products.get(index).getName().startsWith(answer)) {
					System.out.println("Temos " + products.get(index).getName()
							+ " " + products.get(index).getPrice() + " €");
				}
			}
	}

	public static void DBFilePDF(List<Product> products)
			throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell("Name");
		table.addCell("Price");
		table.addCell("Quantity");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int j = 0; j < cells.length; j++) {
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}
		for (int i = 0; i < products.size(); i++) {
			table.addCell("Name:" + products.get(i).getName());
			table.addCell("Price:" + products.get(i).getPrice());
			table.addCell("Quantity:" + products.get(i).getQuantity());
		}

		DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
		Date dateobj = new Date();
		PdfWriter
				.getInstance(document,
						new FileOutputStream("C:\\Logs\\" + df.format(dateobj)
								+ ".pdf"));
		document.open();
		document.add(table);
		document.close();
		System.out.println("Done");
	}

	public static void DBUserFilePDF(List<Person> persons)
			throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell("Name");
		table.addCell("Sobrename");
		table.addCell("Telephone");
		table.addCell("Email");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int j = 0; j < cells.length; j++) {
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}
		for (int i = 0; i < persons.size(); i++) {
			table.addCell("Name:" + persons.get(i).getName());
			table.addCell("Sobrename:" + persons.get(i).getSobrename());
			table.addCell("Telephone:" + persons.get(i).getCellphone());
			table.addCell("Email:" + persons.get(i).getEmail());
		}
		DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
		Date dateobj = new Date();

		PdfWriter
				.getInstance(document,
						new FileOutputStream("C:\\Logs\\" + df.format(dateobj)
								+ ".pdf"));
		document.open();
		document.add(table);
		document.close();
		System.out.println("Done");
	}
}
