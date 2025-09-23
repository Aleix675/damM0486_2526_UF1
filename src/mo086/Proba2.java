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
		System.out.println("ESCRIU EL NOM DEL FITXER:");
		String file= sc.nextLine();
		
		File f= new File(file);
		int cont=0;
		
		try {
			FileInputStream fis= new FileInputStream(f);
			int byteLlegit;

			while( (byteLlegit=fis.read()) !=-1) {
				char c=(char)byteLlegit;
				if(c=='A' || c =='a') {
					cont++; 
				}
			}
			fis.close();
			System.out.println("NUMERO DE LLETRAS 'A':"+cont);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
