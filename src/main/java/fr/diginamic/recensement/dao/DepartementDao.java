package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.dao.entite.Departement;


public interface DepartementDao {

	List<Departement> extraire();

	void insert(List<Departement> region);

	int update(String ancienNom, String nouveauNom);

	boolean delete(Departement departement);
}
