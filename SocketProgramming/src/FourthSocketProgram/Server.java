package FourthSocketProgram;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import ThirdSocketProgram.Packet;


public class Server {
	
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for Client...");
			ServerSocket ss = new ServerSocket(6859);
			Socket s = ss.accept();
			System.out.println("Client is connected");
			
			ObjectOutputStream obOutStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream obInStream = new ObjectInputStream(s.getInputStream());
			
			for(int i=1;i<=10;i++) {
				Packetp rcvPacket = (Packetp)obInStream.readObject();
				System.out.println("Header: "+ rcvPacket.header + " Message : " + rcvPacket.msg + " Protocol ID : " + rcvPacket.protocolID + "Acknowledgement : " + rcvPacket.ack);
				int ack = rcvPacket.ack;
				int h = rcvPacket.header;
				int pid = rcvPacket.protocolID;
				String st = rcvPacket.msg;
				int t = rcvPacket.tailer;
				int checkTailer = Packetp.ParityMethodp(h, st, pid , ack);
				
				String ans = "";
				PrintWriter outs = new PrintWriter(s.getOutputStream(),true);
				if(checkTailer == 0) {
					if(t==0) {
						ack = h;
						ans = "Server : Your object has EVEN parity & Packet is received successfully...";
						outs.println(ans);
						Packetp packetf = new Packetp(h,st,pid,ack,t);
						obOutStream.writeObject(packetf);
					}
					if(t==1) {
						ack = 0;
						ans = "Server : Parity mismatched ,so Something is wrong...";
						outs.println(ans);
						Packetp packets = new Packetp(h,st,pid,ack,t);
						obOutStream.writeObject(packets);
					}
				}
				else {
					if(t==0) {
						ack = 0;
						ans = "Server : Parity mismatched ,so Something is wrong...";
						outs.println(ans);
						Packetp packetd = new Packetp(h,st,pid,ack,t);
						obOutStream.writeObject(packetd);
					}
					if(t==1) {
						ack = h;
						ans = "Server : Your object has ODD parity & Packet is received successfully..";
						outs.println(ans);
						Packetp packete = new Packetp(h,st,pid,ack,t);
						obOutStream.writeObject(packete);
					}
				}
			}
			ss.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
