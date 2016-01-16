import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6304300596173383611L;
	Object message;
	String sender,receiver;
	public Message(String s,String r,Object data) {
		sender=s;
		receiver=r;
		message=data;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
