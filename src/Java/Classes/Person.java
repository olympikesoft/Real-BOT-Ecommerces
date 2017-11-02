package Java.Classes;

public class Person {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCellphone() {
		return cellphone;
	}

	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}

	public String getSobrename() {
		return sobrename;
	}

	public void setSobrename(String sobrename) {
		this.sobrename = sobrename;
	}

	private String name;
	private String password;
	private String email;
	private int id;
	private int cellphone;
	private String sobrename;

	public Person() {

	}
}
