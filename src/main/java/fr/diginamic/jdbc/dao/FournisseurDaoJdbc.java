package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao{
	
    private String url; 
    private String user; 
    private String password; 

    public FournisseurDaoJdbc(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
	@Override
	public List<Fournisseur> extraire() {
        Connection co = null;
        Statement stat = null;
        ResultSet res = null;
        
		ArrayList<Fournisseur> liste = new ArrayList<>();
		
		try {
			DriverManager.registerDriver(new Driver());
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
			
		}
		try {
			co= DriverManager.getConnection(url, user, password );
			stat = co.createStatement();
			res = stat.executeQuery("SELECT * FROM FOURNISSEUR");
			
			while(res.next()) {
				int id = res.getInt("ID");
				String nom = res.getString("NOM");
				Fournisseur f = new Fournisseur(id,nom);
				liste.add(f);
			}	
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}finally {			
			try {
				if(co!=null && !co.isClosed()) {				
					co.close();
				}if(stat!= null && !stat.isClosed()) {
					stat.close();
				}if(res != null && res.isClosed()) {
					res.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return liste;
	}

	
	
	@Override
	public void insert(Fournisseur fournisseur) {
        Connection co = null;
        Statement stat = null;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			stat = co.createStatement();
			String nomSql = fournisseur.getNom().replaceAll("'", "''");
			String sql =  "INSERT INTO fournisseur (nom) VALUES ('"+ nomSql +"')";
            stat.executeUpdate(sql);
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}finally {
				try {
					if(stat != null) {stat.close();}
					if(co != null) {co.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
        Connection co = null;
        Statement stat = null;
		int rowsAffected = 0;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			stat = co.createStatement();

			String sql = "UPDATE fournisseur SET nom = '"+nouveauNom+"' WHERE nom = '"+ancienNom+"'";


			rowsAffected = stat.executeUpdate(sql);
			
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}finally {
			try {
				if(stat != null) {stat.close();}
				if(co != null) {co.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return rowsAffected;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
        Connection co = null;
        Statement stat = null;
        boolean delete = false;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			stat = co.createStatement();
			String sql = "DELETE FROM fournisseur WHERE nom = '"+fournisseur+"'";
			delete = stat.executeUpdate(sql) > 0;
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
			return false;
		}finally {
			try {
				if(stat != null) {stat.close();}
				if(co != null) {co.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return delete;
	}

}
