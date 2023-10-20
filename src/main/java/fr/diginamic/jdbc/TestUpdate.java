package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

public class TestUpdate {

	public static void main(String[] args) throws SQLException {
		
		try {			
			DriverManager.registerDriver(new Driver());
			Connection co = DriverManager.getConnection("jdbc:mariadb://localhost:8889/compta2", "admin", "aaaa");
			
			Statement stat = co.createStatement();
			
			String requete = "UPDATE fournisseur SET nom = 'La Maison des Peintures' WHERE nom = 'La Maison de la Peinture'";
			
			stat.executeUpdate(requete);
			stat.close();
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}
	}

}
