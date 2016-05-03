package objectSending;

import java.io.Serializable;

public class Message implements Serializable
{
	private String message;
	private int type;
	private String name;
	public Message(int type, String message, String name) 
	{
		this.message = message;
		this.name = name;
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Message()
	{
		message = null;
		name = null;
		type = 0;
	}
}
