package com.example.jdbc;

public class Atleta {
	private int cod;
	private String nombre;
	private Esport deporte;

	public Atleta(int cod, String nombre, Esport deporte) {
		this.cod = cod;
		this.nombre = nombre;
		this.deporte = deporte;
	}

	public int getCod() {
		return cod;
	}

	public String getNombre() {
		return nombre;
	}

	public Esport getDeporte() {
		return deporte;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDeporte(Esport deporte) {
		this.deporte = deporte;
	}
}

