package mo086;

import java.io.File;
import java.util.Scanner;

public class Prova1 {

	public static void main(String[] args) {
		System.out.println("proba fitxer");
		String ruta= args[0] ;
		
		File path= new File(ruta);
		File[]files = path.listFiles();
		int cantidad=files.length;
		while(fileRecursivo(cantidad)) {
			cantidad--;
			for(File file: files) {

				System.out.println();
				if(file.isFile()) {

					if(file.canRead()) {
						System.out.print("-R-");
					}else {
						System.out.print("-");
					}

					if(file.canWrite()) {
						System.out.print("W-"+" "+file.getName());
					}else {
						System.out.print("-"+file.getName());
					}
				}

				if(file.isDirectory()) { 
					if(file.canRead()) {
						System.out.print("DR-");
					}else {
						System.out.print("-");
					}

					if(file.canWrite()) {
						System.out.print("W-"+" "+file.getName());
					}else {
						System.out.print("-"+file.getName());
					}
				}
			}

		}
		
	}
	public static boolean fileRecursivo(int cantidad) {
		if (cantidad<0) {
			return false;
		}
		return true;

	}
}
