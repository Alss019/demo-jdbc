package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.dao.entite.Region;

public interface RegionDao {
	
	List<Region> extraire();

	void insert(List<Region> region);

	int update(String ancienNom, String nouveauNom);

	boolean delete(Region region);
}
