package Java.Classes;

public class Messages {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String date;
	private int id;

	public Messages() {

	}

	public Messages(String message, String date) {
		message = this.message;
		date = this.date;
	}

}
