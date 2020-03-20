package ThirdSocketProgram;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for Client...");
			ServerSocket ss = new ServerSocket(4969);
			Socket s = ss.accept();
			System.out.println("Client is connected");
			
			//ObjectOutputStream obOutStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream obInStream = new ObjectInputStream(s.getInputStream());
			
			Packet rcvPacket = (Packet)obInStream.readObject();
			System.out.println("Header: "+ rcvPacket.header + " Message : " + rcvPacket.msg + " Protocol ID : " + rcvPacket.protocolID + "Tailer :" + rcvPacket.tailer);
			int h = rcvPacket.header;
			int pid = rcvPacket.protocolID;
			String st = rcvPacket.msg;
			int t = rcvPacket.tailer;
			
			String go = "Server : Working on your object... ";
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			out.println(go);
			
			int checkTailer = Packet.ParityMethod(h, st, pid);
			
			String ans = "";
			PrintWriter outs = new PrintWriter(s.getOutputStream(),true);
			if(checkTailer == 0) {
				if(t==0)
					ans = "Server : Your object has EVEN parity & Packet is received successfully...";
				if(t==1)
					ans = "Server : Parity mismatched ,so Something is wrong...";
			}
			else {
				if(t==0)
					ans = "Server : Parity mismatched ,so Something is wrong...";
				if(t==1)
					ans = "Server : Your object has ODD parity & Packet is received successfully..";
			}
			
			//System.out.println(ans);
			outs.println(ans);
			
			ss.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
