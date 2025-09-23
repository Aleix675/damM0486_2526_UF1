package mo086;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LectorBin {

	public static void main(String[] args) throws IOException {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream("secret.bin"));
			while(in.available()>0) {
				int codi=in.readInt();
				char c1=in.readChar();
				char c2=in.readChar();
				char c3=in.readChar();
				String codiRecuperat=" "+c1+c2+c3;
				System.out.print(codi+":"+codiRecuperat);
			}
			in.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}

}
