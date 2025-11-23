package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Consulta {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Cambia estos valores por tu configuración
		String url = "jdbc:postgresql://localhost:5432/esports";
		String user = "postgres";
		String password = "1234";

		try {
			Connection con = DriverManager.getConnection(url, user, password);
			menu(con,sc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void mostrarDeportistas(Connection con) {
		try {
			Statement stmt = con.createStatement();
			System.out.println("TABLA DEPORTISTAS");
			ResultSet rs = stmt.executeQuery("SELECT * FROM deportistas");

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();

			// Mostrar encabezados
			for (int i = 1; i <= columnas; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();

			// Mostrar filas
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void mostrarDeportes(Connection con) {
		try {
			Statement stmt = con.createStatement();
			System.out.println("TABLA DEPORTES");
			ResultSet rs = stmt.executeQuery("SELECT * FROM listardeportes()");

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();

			// Mostrar encabezados
			for (int i = 1; i <= columnas; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();

			// Mostrar filas
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//funcion info x id
	public static void infoDeportes(Connection con,Scanner sc) {
		System.out.println("FUNCION INFO DEPORTISTAS POR ID DEPORTE");
		int id=pedirId(sc);
		String sql="SELECT * FROM listar_atletas_por_deporte(?)";

		try (PreparedStatement ps=con.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();

			ResultSetMetaData rsmd = ps.getMetaData();
			int columnas = rsmd.getColumnCount();

			// Mostrar encabezados
			for (int i = 1; i <= columnas; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();

			// Mostrar filas
			while (rs.next()) {
				for (int i = 1; i <= columnas; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int pedirId(Scanner sc) {
		System.out.println("INTRODUCE EL ID DEL DEPORTE.");
		int n=sc.nextInt();
		sc.nextLine();
		return n;
	}

	public static void menu(Connection con,Scanner sc) {
		boolean val=true;
		int opt;

		while(val) {
			System.out.println("elige una opcion"
					+ "\n1.añadir deporte:"
					+ "\n2.añadir atleta:"
					+ "\n3.buscar por id deporte informacion de quien lo practica:"
					+ "\n4.Busqueda por nombre de atleta:"
					+ "\n5.SALIR.");
			opt=sc.nextInt();
			switch(opt) {
			case 1:
				mostrarDeportes(con);
				añadirDeporte(con,sc);
				break;
			case 2:
				mostrarDeportistas(con);
				añadirDeportista(con,sc);
				break;
			case 3:
				//funcion mostrar info por id
				infoDeportes(con, sc);
				break;
			case 4:
				buscarAtletaPorNombre(con,sc);
				break;
			case 5:
				val=false;
				break;
			default:
				System.out.println("OPCION no valida.");
				break;
			}
		}
	}
	public static void añadirDeportista(Connection con,Scanner sc) {
		sc.nextLine();
		System.out.println("Añade un nuevo deportista:");
		String deportistaNuevo=sc.nextLine();//añadir funcion que devuelva int sera id deporte
		mostrarDeportes(con);//se muestran los deportes junto su id correspondiente
		boolean idVal=false;
		int id;
		do {
			id=pedirId(sc);//se pide id

			String sql ="select 1 from deportes where cod=?";
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					idVal=true;
				}else {
					System.out.println("ID no existente, intentalo de nuevo");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}while(!idVal);

		String n="INSERT INTO deportistas(nombre,cod_deporte) VALUES(?,?)";

		PreparedStatement añadir;
		try {
			añadir = con.prepareStatement(n);
			añadir.setString(1, deportistaNuevo);//rellena el campo () del insert 'n'
			añadir.setInt(2, id);// al ser solo prueba he introducido por defecto el deporte con codigo 4 si esta bien hare una funciona para que muestre los deportes y eliga uno
			añadir.executeUpdate() ;//ejecuta el insert
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void añadirDeporte(Connection con,Scanner sc) {
		sc.nextLine();
		System.out.println("INTRODUCE UN NUEVO DEPORTE");
		String deporteN=sc.nextLine();
		String n="INSERT INTO deportes (nombre) VALUES (?)";//el () son los campos de la tabla nombre real
		PreparedStatement añadir;
		try {
			añadir = con.prepareStatement(n);
			añadir.setString(1, deporteN);//rellena el campo () del insert 'n'
			añadir.executeUpdate() ;//ejecuta el insert
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void buscarAtletaPorNombre(Connection con, Scanner sc) {
		sc.nextLine(); // limpiar buffer
		System.out.println("Introduce el nombre o parte del nombre del atleta:");
		String fragmento = sc.nextLine();

		String sql = "SELECT d.cod, d.nombre, e.nombre AS deporte " +
				"FROM deportistas d " +
				"JOIN deportes e ON d.cod_deporte = e.cod " +
				"WHERE LOWER(d.nombre) LIKE LOWER(?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + fragmento + "%"); // coincidencia parcial
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnas = rsmd.getColumnCount();

			// Mostrar encabezados
			for (int i = 1; i <= columnas; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();

			boolean encontrado = false;
			while (rs.next()) {
				encontrado = true;
				for (int i = 1; i <= columnas; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}

			if (!encontrado) {
				System.out.println("No se encontraron atletas con ese nombre.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
