package mo086;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EscriptorRandom {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		Pais[] paisos = new Pais[5];

		paisos[0] = new Pais("Albània", "ALB", "Tirana");
		paisos[1] = new Pais("Bòsnia i Hercegovina", "BIH", "Sarajevo");
		paisos[2] = new Pais("Croàcia", "HRV", "Zagreb");
		paisos[3] = new Pais("Montenegro", "MNE", "Podgorica");
		paisos[4] = new Pais("Sèrbia", "SRB", "Belgrad");
		paisos[0].setPoblacio(3582205);
		paisos[1].setPoblacio(4498976);
		paisos[2].setPoblacio(4800000);
		paisos[3].setPoblacio(630548);
		paisos[4].setPoblacio(8196411);

		//mostrar paises




		//pedir posicion-buscar registro-pedir datos nuevos
		StringBuilder b = null;
		try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw");) {
			//fitxer.seek(0); elige posicion del archivo al tener archivos de tamaño fijo se necesita calcular la posicion
			//posicion en concreto int pos=(pos-1)*174; archivo.seek(pos);


			for (int i=0; i<paisos.length; i++) {
				b = new StringBuilder(paisos[i].getNom());
				b.setLength(40); //Asigna mida de 40 caracters al contingut de StringBuilder
				fitxer.writeInt(i+1);						//id  ------> int  (4 bytes)
				fitxer.writeChars(b.toString());			//nom ------> char (2 bytes) * 40 caràcters
				fitxer.writeChars(paisos[i].getCodiISO());	//Codi ISO -> char (2 bytes) * 3 caràcters
				b = new StringBuilder(paisos[i].getCapital());
				b.setLength(40);
				fitxer.writeChars(b.toString());			//Capital --> char (2 bytes) * 40 caràcters
				fitxer.writeInt(paisos[i].getPoblacio());	//població -> int  (4 bytes)
				//total per país: 174 bytes
			} // Total: 174 bytes * 5 països = 870 bytes
		} catch (IOException e) {
			System.err.println(e);
		}

		Pais p;
		String nom,capital,codiIso;
		int poblacio;

		try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw");) {
			//fitxer.seek(0); elige posicion del archivo al tener archivos de tamaño fijo se necesita calcular la posicion
			//posicion en concreto int pos=(pos-1)*174; archivo.seek(pos);

			while(fitxer.getFilePointer()!= fitxer.length()) {
				System.out.println("Pais: "+fitxer.readInt());
				nom= readChars(fitxer,40);
				codiIso=readChars(fitxer,3);
				capital =readChars(fitxer,40);
				poblacio = fitxer.readInt();
				p=new Pais(nom,codiIso,capital);
				p.setPoblacio(poblacio);
				System.out.println(p);

			}

		} catch (IOException e) {
			System.err.println(e);
		}

		int index;
		System.out.println("INTRODUIX EL INDEX DEL PAIS QUE VOLS CAMBIAR.");
		index=sc.nextInt();
		sc.nextLine();
		int pos=(index-1)*174;
		String nouPais,novaIso,novaCapital;
		int novaPoblacio;
		System.out.println("INTRODUEIX NOU PAIS");
		nouPais=sc.nextLine();
		System.out.println("INTRODUEIX NOU CODI ISO");
		novaIso=sc.nextLine();
		System.out.println("INTRODUEIX NOVA CAPITAL");
		novaCapital=sc.nextLine();
		System.out.println("INTRODUEIX NOVA POBLACIO");
		novaPoblacio=sc.nextInt();
		sc.nextLine();

		try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw");) {
			//fitxer.seek(0); elige posicion del archivo al tener archivos de tamaño fijo se necesita calcular la posicion
			//posicion en concreto int pos=(pos-1)*174; archivo.seek(pos);

				fitxer.seek(pos);
				fitxer.writeInt(index);
				writeFixedString(fitxer,nouPais,40);
				writeFixedString(fitxer,novaIso,3);
				writeFixedString(fitxer,novaCapital,40);
				fitxer.writeInt(novaPoblacio);

				System.out.println("se ha modificado el pais: "+paisos[index-1]+"\nMOSTRANDO LISTA DE PAISES ACTUALUZADA\n");

		} catch (IOException e) {
			System.err.println(e);
		}
		
		
		try (RandomAccessFile fitxer = new RandomAccessFile("paisos.dat", "rw");) {
			while(fitxer.getFilePointer()!= fitxer.length()) {
				System.out.println("Pais: "+fitxer.readInt());
				nom= readChars(fitxer,40);
				codiIso=readChars(fitxer,3);
				capital =readChars(fitxer,40);
				poblacio = fitxer.readInt();
				p=new Pais(nom,codiIso,capital);
				p.setPoblacio(poblacio);
				System.out.println(p);

			}
		} catch (IOException e) {
			System.err.println(e);
		}
		

	}


	private static void writeFixedString(RandomAccessFile file, String text, int length) throws IOException {
		int i = 0;
		for (; i < text.length() && i < length; i++) {
			file.writeChar(text.charAt(i));
		}
		for (; i < length; i++) {
			file.writeChar('\0');
		}
	}
	private static String readChars(RandomAccessFile fitxer, int nChars) throws IOException {
		StringBuilder b = new StringBuilder();
		char ch = ' ';
		for (int i=0; i<nChars; i++) {
			ch=fitxer.readChar();
			if (ch != '\0')
				b.append(ch);
		}
		return b.toString();
	}

}

