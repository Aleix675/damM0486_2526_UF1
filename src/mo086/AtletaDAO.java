package com.example.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO implements DAO<Atleta> {
	private Connection conn;

	public AtletaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void add(Atleta a) {
		String sql = "INSERT INTO deportistas(nombre,cod_deporte) VALUES(?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, a.getNombre());
			ps.setInt(2, a.getDeporte().getCod());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Atleta> getAll() {
		List<Atleta> list = new ArrayList<>();
		String sql = "SELECT a.cod, a.nombre, d.cod AS cod_deporte, d.nombre AS nombre_deporte " +
				"FROM deportistas a JOIN deportes d ON a.cod_deporte = d.cod";
		try (Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Esport e = new Esport(rs.getInt("cod_deporte"), rs.getString("nombre_deporte"));
				list.add(new Atleta(rs.getInt("cod"), rs.getString("nombre"), e));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public List<Atleta> getByDeporteId(int id) {
		List<Atleta> list = new ArrayList<>();
		String sql = "SELECT a.cod, a.nombre, d.cod AS cod_deporte, d.nombre AS nombre_deporte " +
				"FROM deportistas a JOIN deportes d ON a.cod_deporte = d.cod WHERE d.cod=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Esport e = new Esport(rs.getInt("cod_deporte"), rs.getString("nombre_deporte"));
				list.add(new Atleta(rs.getInt("cod"), rs.getString("nombre"), e));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public List<Atleta> searchByName(String nombre) {
		List<Atleta> list = new ArrayList<>();
		String sql = "SELECT a.cod, a.nombre, d.cod AS cod_deporte, d.nombre AS nombre_deporte " +
				"FROM deportistas a JOIN deportes d ON a.cod_deporte=d.cod WHERE LOWER(a.nombre) LIKE ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + nombre.toLowerCase() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Esport e = new Esport(rs.getInt("cod_deporte"), rs.getString("nombre_deporte"));
				list.add(new Atleta(rs.getInt("cod"), rs.getString("nombre"), e));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}


