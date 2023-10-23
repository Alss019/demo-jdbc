package fr.diginamic.recensement;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.dao.entite.Departement;
import fr.diginamic.dao.entite.Region;
import fr.diginamic.dao.entite.Ville;
import fr.diginamic.recensement.dao.DepartementDaoJdbc;
import fr.diginamic.recensement.dao.RegionDaoJdbc;
import fr.diginamic.recensement.dao.VilleDaoJdbc;

public class Application {

	public static void main(String[] args) {
		ResourceBundle conf  = ResourceBundle.getBundle("conf");
		String url = conf.getString("url");
		String user = conf.getString("user");
		String password = conf.getString("password");
		long start = System.currentTimeMillis();
		Path path = Paths.get("/Users/corentin/Documents/workspace-spring-tool-suite-4-4.20.0.RELEASE/recensement.csv");

//		RegionDaoJdbc regionDao = new RegionDaoJdbc(url, user, password);
//		DepartementDaoJdbc departementDao = new DepartementDaoJdbc(url, user, password);
//		VilleDaoJdbc villeDao = new VilleDaoJdbc(url, user, password);
		
		RegionDaoJdbc regionDao = new RegionDaoJdbc("jdbc:mariadb://localhost:8889/recensement", "admin", "aaaa");
		DepartementDaoJdbc departementDao = new DepartementDaoJdbc("jdbc:mariadb://localhost:8889/recensement", "admin", "aaaa");
		VilleDaoJdbc villeDao = new VilleDaoJdbc("jdbc:mariadb://localhost:8889/recensement", "admin", "aaaa");

		// Lire le fichier CSV et obtenir une liste de villes
		List<Region> region = regionDao.extraireCsv(path);
		List<Departement> depts = departementDao.extraireCsv(path);
		List<Ville> ville = villeDao.extraireCsv(path);
		
		long endExtraire = System.currentTimeMillis();
	       long durationExtraire = (endExtraire - start)/1000;
	       System.out.printf( "Duration : "+ durationExtraire + " s\n");
		// Insérer les Département et Région en base de données
		regionDao.insert(region);
		departementDao.insert(depts);
		//insert les villes en base de donées
		for(Ville v: ville) {
			for(Departement d:depts) {
				if(v.getIdDepts().equals(d.getCode())) {
					String str = Integer.toString( d.getId());
					v.setIdDepts(str);
				}
			}
			villeDao.insert(v);
		}
		
		
	       long end = System.currentTimeMillis();
	        
	       long duration = (end - start)/1000;
	       System.out.printf( "Duration : "+ duration + " s\n");
	}
}
