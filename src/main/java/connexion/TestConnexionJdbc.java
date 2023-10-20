package connexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.mariadb.jdbc.Driver;

public class TestConnexionJdbc {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			DriverManager.registerDriver(new Driver());
			Connection co = DriverManager.getConnection("jdbc:mariadb://localhost:8889/compta", "root", "root");
			System.out.println("Connexion établi " + co);
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}
		
	}

}
