package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		ArrayList<Fournisseur> liste = new ArrayList<>();
		Connection co = null;
		
		try {
			DriverManager.registerDriver(new Driver());
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
			
		}
		try {
			co= DriverManager.getConnection("jdbc:mariadb://localhost:8889/compta2", "admin", "aaaa");
			Statement stat = co.createStatement();
			ResultSet res = stat.executeQuery("SELECT * FROM FOURNISSEUR");
			while(res.next()) {
				int id = res.getInt("ID");
				String nom = res.getString("NOM");
				Fournisseur f = new Fournisseur(id,nom);
				liste.add(f);
			}
			res.close();
			stat.close();
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
//			throw new ExceptionTechnique("un probleme est survenu lors de la connexion");
		}finally {			
			try {
				if(co!=null && !co.isClosed()) {				
					co.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("------TABLE FOURNISSEUR------\n");
		for(Fournisseur f: liste) {
			System.out.println("ID = " + f.getId() + "\nNOM = " + f.getNom()+"\n------------------------------");
		}
	}

}
