package fr.diginamic.recensement.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.dao.entite.Departement;
import fr.diginamic.dao.entite.Region;
import fr.diginamic.dao.entite.Ville;

public class RegionDaoJdbc implements RegionDao {
	private String url;
	private String user;
	private String password;

	public RegionDaoJdbc(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	
	@Override
	public List<Region> extraire() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insert(List<Region> region) {
        Connection co = null;
        PreparedStatement preparedStatement = null;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			
			String sql =  "INSERT INTO REGION (id, nom) VALUES (?,?)";
            preparedStatement = co.prepareStatement(sql);
            for(Region r: region) {
            	preparedStatement.setInt(1, r.getId());
            	preparedStatement.setString(2, r.getNom());
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted == 0) {
                    System.err.println("L'insertion de la région a échoué : " + r.getNom());
                } else {
                    System.out.println("Région insérée avec succès : " + r.getNom());
                }
            	
            }
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}finally {
				try {
					if(preparedStatement != null) {preparedStatement.close();}
					if(co != null) {co.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}



	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Region> extraireCsv(Path path) {
		List<Region> regions = new ArrayList<>();
		String[] ligneSplit = new String[0];
		try {
			List<String> lines = Files.readAllLines(path);

			for (int i = 1; i < lines.size(); i++) {
				ligneSplit = lines.get(i).split(";");
				int id = Integer.parseInt(ligneSplit[0]);
				String nom = ligneSplit[1];
				boolean regionExiste = false;
				for (Region r : regions) {
					if (r.getNom().equals(nom)) {
						regionExiste = true;
						break;
					}
				}
				if (!regionExiste) {
					regions.add(new Region(id, nom));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return regions;
	}
}
