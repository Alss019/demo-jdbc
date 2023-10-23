package fr.diginamic.recensement.dao;

import java.io.IOException;
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

public class DepartementDaoJdbc implements DepartementDao {
    private String url; 
    private String user; 
    private String password; 
	public DepartementDaoJdbc(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	@Override
	public List<Departement> extraire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(List<Departement> dept) {
        Connection co = null;
        PreparedStatement preparedStatement = null;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			
			String sql =  "INSERT INTO DEPTS (id, code) VALUES (?,?)";
            preparedStatement = co.prepareStatement(sql);
            for(Departement depts: dept) {
            	preparedStatement.setInt(1, depts.getId());
            	preparedStatement.setString(2, depts.getCode());
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted == 0) {
                    System.err.println("L'insertion du département a échoué : " + depts.getCode());
                } else {
                    System.out.println("Département insérée avec succès : " + depts.getCode());
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
	public boolean delete(Departement departement) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Departement> extraireCsv(Path path) {
		List<Departement> depts = new ArrayList<>();
		String[] ligneSplit = new String[0];

		try {
			List<String> lines = Files.readAllLines(path);
			int id = 0;

			for (int i = 1; i < lines.size(); i++) {
				ligneSplit = lines.get(i).split(";");
				String codeDept = ligneSplit[2];

				// Vérifie si le département existe déjà dans depts
				boolean departementExiste = false;
				for (Departement departement : depts) {
					if (departement.getCode().equals(codeDept)) {
						departementExiste = true;
						break;
					}
				}

				if (!departementExiste) {
					id = id + 1;
					depts.add(new Departement(id, codeDept));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return depts;
	}
}
