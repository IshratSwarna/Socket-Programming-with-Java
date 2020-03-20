package FirstSocketProgram;

import java.io.*;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client Started");
			Socket s = new Socket("localhost",9845);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a string ");
			String str = userInput.readLine();
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			out.println(str);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
