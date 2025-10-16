package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registrar {

	
	
	
	@SuppressWarnings("unchecked")
	public static User menu(Scanner sc) {
		List<User>registre;
		File file= new File("Registre.dat");
		
		boolean val= false;
	
		
		while(!val) {
			
			if(file.exists()) {
				ObjectInputStream f;
				try {
					f = new ObjectInputStream (new FileInputStream(file));
					registre=(List<User>)f.readObject();
					f.close();
					for(User u:registre) {
						System.out.println(u.getUser()+""+u.getPassword());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					registre = new ArrayList<>();
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					registre = new ArrayList<>();
				}
			}else {
				registre = new ArrayList<>();
				//la lista se mantiene vacia porque el fichero no existe
			}
			
			System.out.println("1.REGISTRAR || 2.ACCEDIR || 3.SORTIR");
			int opt=sc.nextInt();
			switch(opt) {
			case 1:
				User nouUsuari= new User(demanarNom(sc),demanarUser(sc),demanarPassword(sc),0);

				// Verificar si usuario ya existe
				boolean existe = registre.stream().anyMatch(u -> u.getName().equals(nouUsuari.getName()));
				if(!existe) {
					registre.add(nouUsuari);
					System.out.println("registrant usuari...");
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(registre);
						oos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("ERROR USUARI JA REGISTRAT...");
				}
				System.out.println(registre.toString());
				break;

			case 2: 

				String user = demanarUser(sc);
				String password = demanarPassword(sc);

				boolean logOk= registre.stream().anyMatch(u-> u.getUser().equals(user) && u.getPassword().equals(password));
				
				
				if(logOk) {
					for(User u:registre) {
						if(u.getUser().equals(user))
							return u;
					}
					System.out.println("inciant sesio...");
					
					
				}else {
					System.out.println("USUARI NO TROBAT.");
				}
				break;

			case 3:
				System.out.println("sortint...");
				val=true;
				break;
			default:
				System.out.println("OPCIO INCORRECTE");
			}
		}
		
		return null;
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		menu(sc);
		

	}

	
	public static String demanarPassword(Scanner sc) {
		boolean val=false;
		String pass="";

		while(!val) {
			System.out.println("INTRODUCE UNA CONTRASEÑA ( MÍNIMO 7 CARACTERES ).");
			if(sc.hasNext()) {
				pass= sc.nextLine();
			}else {
				System.out.println("ENTRADA VACIA.");	
			}
			if(pass.matches("[a-zA-Z0-9]+") && pass.length()>7) {
				return pass;
			}


		}

		sc.nextLine();
		return null;
	}


	public static String demanarUser(Scanner sc) {
		boolean val=false;
		String pass="";

		while(!val) {
			System.out.println("INTRODUCE UN NOMBRE DE USUARIO.");
			if(sc.hasNext()) {
				pass= sc.nextLine();
			}else {
				System.out.println("ENTRADA VACIA.");	
			}
			if(pass.matches("[a-zA-Z0-9]+")) {
				return pass;
			}


		}

		sc.nextLine();
		return null;
	}


	public static String demanarNom(Scanner sc) {
		boolean val=false;
		String pass="";

		while(!val) {
			System.out.println("INTRODUCE TU NOMBRE.");
			if(sc.hasNext()) {
				pass= sc.nextLine();
			}else {
				System.out.println("ENTRADA VACIA.");	
			}
			if(pass.matches("[a-zA-Z]+")) {
				return pass;
			}

		}

		sc.nextLine();
		return null;
	}
}
