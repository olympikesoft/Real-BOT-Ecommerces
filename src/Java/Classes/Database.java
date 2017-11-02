package Java.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Java.Classes.Orders;

public class Database {

	static Connection MysqlConn;
	static PreparedStatement MysqlPrepareStat;

	public Database() {
		try {
			// DriverManager: The basic service for managing a set of JDBC
			// drivers.
			MysqlConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shop", "root", "");
			if (MysqlConn != null) {
				System.out
						.println("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}
	}

	/*
	 * public void UserBuyProduct(List<Product> products) {
	 * 
	 * }
	 */

	public void ListProducts(List<Product> Products) {
		try {
			String insertQueryStatement = "Select distinct id, name, quantity, price from product";

			MysqlPrepareStat = MysqlConn.prepareStatement(insertQueryStatement);

			ResultSet result = MysqlPrepareStat.executeQuery();
			while (result.next()) {

				Product Product = new Product();
				Product.setId(result.getInt("id"));
				Product.setName(result.getString("name"));
				Product.setQuantity(result.getInt("quantity"));
				Product.setPrice(result.getDouble("price"));
				Products.add(Product);

			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	public void ListPersons(List<Person> Persons) {
		try {
			String insertQueryStatement = "Select distinct id, name, sobrename, email, telefone FROM user";

			MysqlPrepareStat = MysqlConn.prepareStatement(insertQueryStatement);

			ResultSet result = MysqlPrepareStat.executeQuery();
			while (result.next()) {

				Person Person = new Person();
				Person.setId(result.getInt("id"));
				Person.setName(result.getString("name"));
				Person.setSobrename(result.getString("sobrename"));
				Person.setCellphone(result.getInt("telefone"));
				Person.setEmail(result.getString("email"));
				Persons.add(Person);

			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	public void ListOrders(List<Orders> orderList) {
		try {
			String insertQueryStatement = "Select distinct u.name, u.sobrename, u.email, u.id,  u.telefone, o.id,  o.date_time, o.quantity FROM orders o, user u where o.user_id = u.id";

			MysqlPrepareStat = MysqlConn.prepareStatement(insertQueryStatement);

			ResultSet result = MysqlPrepareStat.executeQuery();
			while (result.next()) {

				Orders orders = new Orders();
				orders.setId(result.getInt("o.id"));
				orders.setDate(result.getString("o.date_time"));
				orders.setQuantity(result.getInt("o.quantity"));
				Person persons = new Person();
				orders.setUser_id(result.getInt("u.id"));
				persons.setName(result.getString("u.name"));
				persons.setSobrename(result.getString("u.sobrename"));
				persons.setEmail(result.getString("u.email"));
				persons.setId(result.getInt("u.id"));
				persons.setCellphone(result.getInt("u.telefone"));
				orders.addPerson(persons);

				orderList.add(orders);

			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

}
