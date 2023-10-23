package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.dao.entite.Ville;


public interface VilleDao {
	List<Ville> extraire();

	void insert(Ville villes);

	int update(String ancienNom, String nouveauNom);

	boolean delete(Ville ville);
}
