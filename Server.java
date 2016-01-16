import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	static ServerSocket listener;
	static Socket other;
	static ObjectOutputStream write;
	static ObjectInputStream read; 
	
	public static void main(String[] args) throws IOException {
		Runnable input=new IncomingReader();
		Thread name=new Thread(input);
		name.start();
		Message m = new Message("server","client","Hello");
		write.writeObject(m);
	}
	public static class IncomingReader implements Runnable{
		public void run(){
			try {
				listener= new ServerSocket(6789);
				other=listener.accept();
				write= new ObjectOutputStream(other.getOutputStream());
				read = new ObjectInputStream(other.getInputStream());
				while(true){
					Message test = null;
					test=(Message) read.readObject();
					if(test!=null){
						System.out.println((String)test.message);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
