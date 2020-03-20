package ThirdSocketProgram;

import java.io.Serializable;

public class Packet implements Serializable {
	int header;
	String msg ;
	int protocolID ;
	int tailer;
	
	public Packet(int header , String msg , int protocolID , int tailer) {
		this.header = header;
		this.msg = msg;
		this.protocolID = protocolID;
		this.tailer = tailer;
	}
	static int ParityMethod(int h,String st, int pid)
	{	
		int t ;
		String fs = Integer.toBinaryString(h);
		String ls = Integer.toBinaryString(pid);
		
		int fslen = 0 ; 
		int lslen = 0;
		fslen = fs.length();
		lslen = ls.length();
		int one = 0;
		for (int i=0; i<fslen; i++) {
			if(fs.charAt(i)== '1')
				one++;
		}
		for (int i=0; i<lslen; i++) {
			if(ls.charAt(i)== '1')
				one++;
		}
		byte[] bytes = st.getBytes();
		for(int i=0; i<bytes.length;i++){
		    String temps = Integer.toBinaryString(bytes[i]);
		    int tempslen = temps.length();
		    for (int j=0; j<tempslen; j++) {
				if(temps.charAt(j)== '1')
					one++;
			}
		}
		//System.out.println(one);
		if(one%2 == 0) {
			t = 0;
		}
		else {
			t = 1;
		}
		return t;
	}
	
}
