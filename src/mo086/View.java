package com.example.jdbc;

import java.util.List;
import java.util.Scanner;

public class View {

	public Esport esportForm() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nombre del nuevo deporte:");
		String nombre = sc.nextLine();
		return new Esport(0, nombre);
	}

	public Atleta atletaForm(List<Esport> deportes, Scanner sc) {
		System.out.println("Introduce nombre del nuevo atleta:");
		String nombre = sc.nextLine();
		int id = demanaEsport(deportes, sc);
		Esport e = deportes.stream().filter(d -> d.getCod() == id).findFirst().orElse(null);
		if (e == null) {
			System.out.println("ID de deporte inválido.");
			return null;
		}
		return new Atleta(0, nombre, e);
	}

	public int demanaEsport(List<Esport> deportes, Scanner sc) {
		System.out.println("Listado de deportes:");
		for (Esport e : deportes) {
			System.out.println(e.getCod() + ". " + e.getNombre());
		}
		System.out.println("Introduce el ID del deporte:");
		return sc.nextInt();
	}

	public void llistaAtletes(List<Atleta> list) {
		System.out.println("\n--- LISTA DE ATLETAS ---");
		for (Atleta a : list) {
			System.out.println(a.getCod() + " | " + a.getNombre() + " | " + a.getDeporte().getNombre());
		}
	}

	public void llistaEsports(List<Esport> list) {
		System.out.println("\n--- LISTA DE DEPORTES ---");
		for (Esport e : list) {
			System.out.println(e.getCod() + " | " + e.getNombre());
		}
	}
}

