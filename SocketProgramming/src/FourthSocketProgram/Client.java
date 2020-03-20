package FourthSocketProgram;

import java.io.* ;
import java.net.Socket;

import ThirdSocketProgram.Packet;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("Client is Requesting");
			Socket s = new Socket("localhost",6859);
			
			ObjectOutputStream obOutStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream obInStream = new ObjectInputStream(s.getInputStream());
			int header = 1;
			String st = "RUET" ;
			int Protocol = 1;
			int tailer ;
			int ack;
			int temph = 0;
			for(int i=1; i<=10; i++) {
				if(i==1) {
					ack = 0;
					tailer = Packetp.ParityMethodp(header,st,Protocol,ack);
					Packetp packet = new Packetp(header,st,Protocol,ack,tailer);
					obOutStream.writeObject(packet);
					temph = header;
				}
				else {
					BufferedReader inans = new BufferedReader(new InputStreamReader(s.getInputStream()));
					System.out.println(inans.readLine());
					Packetp rcvPacket = (Packetp)obInStream.readObject();
					if(temph == rcvPacket.ack) {
						ack = 0;
						header = header +1;
						tailer = Packetp.ParityMethodp(header,st,Protocol,ack);
						Packetp packet2 = new Packetp(header,st,Protocol,ack,tailer);
						obOutStream.writeObject(packet2);
						temph = header;
					}
					else {
						System.out.println("Object should not be sent!");
						break;
					}
				}
			}
			obOutStream.close();
			s.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

