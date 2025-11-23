package com.example.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EsportDAO implements DAO<Esport> {
	private Connection conn;

	public EsportDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void add(Esport e) {
		String sql = "INSERT INTO deportes(nombre) VALUES(?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, e.getNombre());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Esport> getAll() {
		List<Esport> list = new ArrayList<>();
		String sql = "SELECT * FROM listardeportes()";
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(new Esport(rs.getInt("cod"), rs.getString("nombre")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}

