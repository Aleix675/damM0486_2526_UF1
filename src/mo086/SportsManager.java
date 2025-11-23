package com.example.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class SportsManager {
	public static void main(String[] args) {
		Connection conn = ConnectionManager.getConnection();
		Scanner sc = new Scanner(System.in);
		View view = new View();
		EsportDAO esportDAO = new EsportDAO(conn);
		AtletaDAO atletaDAO = new AtletaDAO(conn);

		boolean exit = false;
		while(!exit) {
			System.out.println("\n1. Añadir deporte\n2. Añadir atleta\n3. Listar atletas por deporte\n4. Buscar atleta por nombre\n0. Salir");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				Esport e = view.esportForm();
				if (e != null) esportDAO.add(e);
				break;
			case 2:
				List<Esport> deportes = esportDAO.getAll();
				Atleta a = view.atletaForm(deportes, sc);
				if (a != null) atletaDAO.add(a);
				break;
			case 3:
				deportes = esportDAO.getAll();
				int id = view.demanaEsport(deportes, sc);
				List<Atleta> list = atletaDAO.getByDeporteId(id);
				view.llistaAtletes(list);
				break;
			case 4:
				System.out.println("Introduce nombre del atleta a buscar:");
				String name = sc.nextLine();
				List<Atleta> res = atletaDAO.searchByName(name);
				view.llistaAtletes(res);
				break;
			case 0:
				exit = true;
				break;
			default:
				System.out.println("Opción inválida.");
			}
		}
		sc.close();
	}
}


