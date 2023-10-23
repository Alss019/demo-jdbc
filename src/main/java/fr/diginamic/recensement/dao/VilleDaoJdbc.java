package fr.diginamic.recensement.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.dao.entite.Ville;

public class VilleDaoJdbc implements VilleDao {
	private String url;
	private String user;
	private String password;

	public VilleDaoJdbc(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public List<Ville> extraire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Ville ville) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insert(Ville villes) {
		Connection co = null;
		PreparedStatement preparedStatement = null;
		try {
			co = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO VILLE (ID, NOM, POPULATION, ID_DEPTS, ID_REGION) VALUES (?, ?, ?, ?, ?)";
			try {
				preparedStatement = co.prepareStatement(sql);
				preparedStatement.setInt(1, villes.getId());
				preparedStatement.setString(2, villes.getNom());
				preparedStatement.setInt(3, villes.getPopulation());
				preparedStatement.setString(4, villes.getIdDepts());
				preparedStatement.setString(5, villes.getIdRegion());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (co != null) {
					co.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Ville> extraireCsv(Path path) {
		List<Ville> villes = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(path);
			String[] ligneSplit = new String[0];

			for (int i = 1; i < lines.size(); i++) {
				ligneSplit = lines.get(i).split(";");
				int population = Integer.parseInt(ligneSplit[9].trim().replaceAll(" ", ""));

				Ville ville = new Ville(i, ligneSplit[6], population, ligneSplit[2], ligneSplit[0]);
				villes.add(ville);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return villes;
	}
}
