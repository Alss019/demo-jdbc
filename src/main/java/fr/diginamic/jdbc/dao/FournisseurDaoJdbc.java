package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        PreparedStatement preparedStatement = null;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			
			String sql =  "INSERT INTO fournisseur (nom) VALUES (?)";
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1, fournisseur.getNom());
            preparedStatement.executeUpdate();
			
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
        Connection co = null;
        PreparedStatement preparedStatement = null;
		int rowsAffected = 0;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			
			String sql =   "UPDATE fournisseur SET nom = (?) WHERE nom = (?)";
            preparedStatement = co.prepareStatement(sql);
            preparedStatement.setString(1, nouveauNom);
            preparedStatement.setString(2, ancienNom);
            preparedStatement.executeUpdate();

			rowsAffected = preparedStatement.executeUpdate();
			
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
		return rowsAffected;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
        Connection co = null;
        PreparedStatement preparedStatement = null;
        boolean delete = false;
		try {			
			DriverManager.registerDriver(new Driver());
			co= DriverManager.getConnection(url, user, password );
			
			String sql = "DELETE FROM fournisseur WHERE nom = ?";
			preparedStatement = co.prepareStatement(sql);
			preparedStatement.setString(1, fournisseur.getNom());
			preparedStatement.executeUpdate();
			delete = preparedStatement.executeUpdate() > 0;
		}catch(SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
			return false;
		}finally {
			try {
				if(preparedStatement != null) {preparedStatement.close();}
				if(co != null) {co.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return delete;
	}

}
