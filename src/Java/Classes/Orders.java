package Java.Classes;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private int id;
	private List<Person> personsList = new ArrayList<Person>();
	private String date;
	private int quantity;
	private int user_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Orders() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public List<Person> getPersonsList() {
		return personsList;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addPerson(Person person) {
		personsList.add(person);

	}

}
