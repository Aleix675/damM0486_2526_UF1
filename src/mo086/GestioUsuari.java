package mo086;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class GestioUsuari {

	public static String validarNom(Scanner sc) {
		boolean val=false;
		String str="";
		while(!val) {
			System.out.println("INTRODUEIX NOM:");
			str=sc.nextLine();
			if(str.matches("[A-Za-z]+")) {
				val=true;
			}else {
				System.out.println("ENTRADA VUIDA.\nTornar a introduir un nom");
			}
		}
		return str;
	}

	public static String validarContrasenya(Scanner sc) {
		boolean val=false;
		String str="";
		while(!val) {
			System.out.println("INTRODUEIX CONTRASENYA (només lletres i números):");
			str=sc.nextLine();
			if(!str.isEmpty() && str.matches("[A-Za-z0-9]+")) {
				val=true;
			}else {
				System.out.println("ENTRADA VUIDA.\nTornar a introduir un nom");
			}
		}
		return str;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Benvingut al joc!");

		String nom=validarNom(sc);
		String contra=validarContrasenya(sc);
//No abre el archivo, solo crea una referencia a él.
//Puedes usarlo para saber si el archivo existe, crearlo, borrarlo, obtener su nombre, etc.
		File fitxer = new File(nom+".usr");

		if(fitxer.exists()) {
			try { 
//Se usa para leer objetos previamente guardados con ObjectOutputStream.
//Object obj = ois.readObject(); y luego haces un cast al tipo adecuado:User u = (User) obj;
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer));
				User u = (User) ois.readObject();

				if(u.pass.equals(contra)) {
					System.out.println("Accés correcte al sistema\nUSUARI:"+u.nom+"\nCONTRASENYA:"+ u.pass);
				}else {
					System.out.println("Accés no concedit: La contrasenya no és correcta");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			int elec;
			System.out.println("USUARI NO TROBAT.\nVOLS REGISTRARTE? \nPrem ' 0 ' per registrarte i ' 1 ' per cancelar.");
			elec=sc.nextInt();
			sc.nextLine();
			
			if(elec==1) {
				System.out.println("Cancelant registre...");
			}else if(elec==0) {
				System.out.println("Registrant el següent usuari: "+nom);
				try {
//Se usa para guardar objetos en un fichero.
//Debe ir acompañado de FileOutputStream, que se encarga de escribir en el archivo.					
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxer));
					User u = new User(nom,contra);
					oos.writeObject(u);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}


	}
}

