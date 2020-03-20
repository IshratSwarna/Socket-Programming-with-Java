package ThirdSocketProgram;

import java.io.* ;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("Client is Requesting");
			Socket s = new Socket("localhost",4969);
			
			ObjectOutputStream obOutStream = new ObjectOutputStream(s.getOutputStream());
			//ObjectInputStream obInStream = new ObjectInputStream(s.getInputStream());
			
			int header = 3;
			String st = "RUET" ;
			int Protocol = 1;
			int tailer ;
			tailer = Packet.ParityMethod(header,st,Protocol);
			
			Packet packet = new Packet(header,st,Protocol,tailer);
			obOutStream.writeObject(packet);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			
			BufferedReader inans = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(inans.readLine());
			
			obOutStream.close();
			s.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
