package mo086;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ex22 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada="";
		boolean val=false;
		StringBuilder sb= new StringBuilder();
		FileWriter fw;
		try {
			fw = new FileWriter("userText.txt");
			BufferedWriter bw = new BufferedWriter (fw);
			while(!val) {
				System.out.println("INTRODEUIX UNA FRASE.");
				entrada=sc.nextLine();

				if(entrada.contains("quit")) {
					System.out.println("SORTINT...");
					val=true;
				}else {
					sb.append(entrada+"\n");

				}

			}
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

		try {
			FileReader fr = new FileReader("userText.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {
				System.out.println(linea);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
