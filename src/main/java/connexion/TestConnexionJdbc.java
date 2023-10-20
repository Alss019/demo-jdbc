package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

public class TestConnexionJdbc {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			DriverManager.registerDriver(new Driver());
			Connection co = DriverManager.getConnection("jdbc:mariadb://localhost:8889/compta", "admin", "aaaa");
			
			Statement stat = co.createStatement();
			
			String requete = "INSERT INTO UTILISATEUR (nom, prenom) VALUES ('PLAA', 'CORENTIN')";
			
			stat.executeUpdate(requete);
			
			stat.close();
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}
		
	}

}
