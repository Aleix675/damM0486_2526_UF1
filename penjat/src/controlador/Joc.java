package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import vista.Registrar;
import vista.*;

public class Joc {
public static User sesioUsuari;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Registrar j= new Registrar();
		
		 sesioUsuari=j.menu(sc);
		if(sesioUsuari!=null) {
			System.out.println(	sesioUsuari.toString());
			menuDespresLogin(sc);
		}



	}

	public static void menuDespresLogin(Scanner sc) {
		
		while (true) {
			System.out.println("SESIO DE:"+sesioUsuari.getUser()+"PUNTS: "+sesioUsuari.getPunts());
			
			System.out.println("\n--- Menú ---");
			System.out.println("1. Afegir paraules");
			System.out.println("2. Jugar");
			System.out.println("3. Sortir");
			System.out.print("Tria una opció: ");

			String opcio = sc.nextLine();
			switch (opcio) {
			case "1":
				mostrarIAfegirParaules(sc);
				break;
			case "2":
				jugarPenjat(sc);
				break;
			case "3":
				System.out.println("Fins aviat!");
				return;
			default:
				System.out.println("Opció no vàlida.");
			}
		}
	}


	public static List<String> llegirParaules() {
		List<String> paraules = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("paraules.txt"))) {
			String linia;
			while ((linia = br.readLine()) != null) {
				paraules.add(linia.trim().toLowerCase());
			}
		} catch (IOException e) {
			System.out.println("No s'ha pogut llegir el fitxer de paraules.");
		}
		return paraules;
	}

	public static void plenarFitxerParaules() {
		File file = new File("paraules.txt");
		List<String> paraulesFitxer= new ArrayList<>();
		String[] paraules= {
				"gato", "perro", "sol", "luna", "cielo",
				"mar", "tierra", "fuego", "aire", "flor",
				"árbol", "montaña", "río", "nube", "estrella"
		};
		if(!file.exists()) {// si el fichero no existe lo creamos y lo llenamos de palabras predefinidas.
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(String p:paraules) {
			bw.write(p);
			bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {//si existeix contenia paraules les recuperem i aixo no perdem la llista de paraules
			try {
				ObjectInputStream f = new ObjectInputStream(new FileInputStream(file));
				 byte[] bytes = Files.readAllBytes(Paths.get("/paraules.txt"));
					 String cont=new String (bytes);
					 paraulesFitxer.add(cont);
					 for(String p:paraulesFitxer) {
					 System.out.println(p);			 
					 }
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(String p:paraulesFitxer) {
				bw.write(p);
				bw.newLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

	}
	
	public static void afegirParaula(String novaParaula) {
		List<String> paraules = llegirParaules();
		novaParaula = novaParaula.trim().toLowerCase();

		if (!paraules.contains(novaParaula)) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("paraules.txt", true))) {
				bw.write(novaParaula);
				bw.newLine();
				System.out.println("Paraula afegida correctament.");
			} catch (IOException e) {
				System.out.println("Error en afegir la paraula.");
			}
		} else {
			System.out.println("La paraula ja existeix al fitxer.");
		}
	}

	public static void mostrarIAfegirParaules(Scanner sc) {
		List<String> paraules = llegirParaules();

		System.out.println("Llistat de paraules actuals:");
		for (String p : paraules) {
			System.out.println("- " + p);
		}

		System.out.print("Vols afegir una nova paraula? (si/no): ");
		String resposta = sc.nextLine().trim().toLowerCase();

		if (resposta.equals("si")) {
			System.out.print("Introdueix la nova paraula: ");
			String nova = sc.nextLine();
			afegirParaula(nova);
		}
	}

	public static void jugarPenjat(Scanner sc) {
		List<String> paraules = llegirParaules();
		if (paraules.isEmpty()) {
			System.out.println("No hi ha paraules per jugar.");
			return;
		}

		String paraula = paraules.get(new Random().nextInt(paraules.size()));
		Set<Character> lletresEncertades = new HashSet<>();
		int intents = 6;

		while (intents > 0) {
			System.out.print("Paraula: ");
			boolean completada = true;

			for (char c : paraula.toCharArray()) {
				if (lletresEncertades.contains(c)) {
					System.out.print(c + " ");
				} else {
					System.out.print("_ ");
					completada = false;
				}
			}
			System.out.println();

			if (completada) {
				System.out.println("Has guanyat! La paraula era: " + paraula);
				sesioUsuari.setPunts(+1);
				return;
			}

			System.out.print("Introdueix una lletra: ");
			String input = sc.nextLine().toLowerCase();
			if (input.length() != 1) {
				System.out.println("Només pots introduir una lletra.");
				continue;
			}

			char lletra = input.charAt(0);
			if (paraula.indexOf(lletra) >= 0) {
				lletresEncertades.add(lletra);
				System.out.println("Correcte!");
			} else {
				intents--;
				System.out.println("Incorrecte. Et queden " + intents + " intents.");
			}
		}

		System.out.println("Has perdut. La paraula era: " + paraula);
	}


	public static void CrearFicheroPalabras(String par[]) {
		File file= new File("paraules.txt");
		if(!file.exists()) {
			try {
				ObjectInputStream f= new ObjectInputStream(new FileInputStream(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {

		}

	}
}
