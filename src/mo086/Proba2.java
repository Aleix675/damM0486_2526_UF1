package mo086;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Proba2 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String file=  "text.txt";
		File f= new File("file");


		try {
			FileOutputStream fos = new FileOutputStream(f);
			
			
			fos.write("HOLA GS".getBytes());
			fos.write("\n".getBytes());
			String t = "som 2jz";
			for(char c:t.toCharArray()) {
				fos.write(c);
			}
			fos.close();

			FileInputStream fis= new FileInputStream(f);
			int b=0;

			while(b!=-1) {
				b=fis.read();
				if(b!=1)System.out.print((char)b);
				
			}

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
