import java.util.Scanner;


public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String id,reciver,message;
		
		System.out.println("Enter your name");
		id=sc.next();
		
		MOM middle = new MOM(id);
		middle.addListener(new EL());
		
		
		while(true){
			System.out.println("Enter name of the receiver");
			reciver=sc.next();
			System.out.println("Enter the message");
			message=sc.nextLine();
			middle.send(reciver,message);
		}
	}
	public static class EL implements MOMListener{
		@Override
		public void incomingmessage(String sender, Object message) {
			String text=(String)message;
			System.out.println(sender+":-"+text);
		}
	}
}
