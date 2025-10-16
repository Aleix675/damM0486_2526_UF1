package vista;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{

	private String name; 
	private String user; 
	private String password;
	private int punts;


	public User(String name,String user,String password,int punts) {
		this.name=name;
		this.user=user;
		this.password=password;
		this.punts=punts;
	}


	@Override
	public String toString() {
		return "Vista [name=" + name + ", user=" + user + ", password=" + password
				+ ", punts=" + punts + "]";
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public int getPunts() {
		return punts;
	}


	public void setPunts(int punts) {
		this.punts = punts;
	}

}
