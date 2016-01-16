import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;


public class MOM {
	
	String id;
	Socket s;
	ObjectInputStream read;
	ObjectOutputStream write;
	Vector<MOMListener> listeners=new Vector<MOMListener>();
	
	MOM(String name){
		id=name;
		Runnable Reader = new IncomingReader();
		Thread t = new Thread(Reader);
		t.start();
	}
	
	public void addListener(MOMListener toAdd) {
        listeners.add(toAdd);
    }
	
	public void notifyListners(String sender, Object message) {
        // Notify everybody that may be interested.
        for (MOMListener hl : listeners)
            hl.incomingmessage(sender, message);
    }
	
	public class IncomingReader implements Runnable{
		public void run(){
			try {
				s = new Socket("192.168.2.36",6789);
				read = new ObjectInputStream(s.getInputStream());
				write = new ObjectOutputStream(s.getOutputStream());
				while(true){
					Message test = null;
					test=(Message) read.readObject();
					if(test!=null){
						notifyListners(test.getSender(),test.getMessage());
					}
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void send(String reciver, Object input) {
		Message message = new Message(id,reciver,input);
		try {
			write.reset();
			write.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
