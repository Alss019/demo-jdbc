package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {

		FournisseurDao fournisseurDao = new FournisseurDaoJdbc("jdbc:mariadb://localhost:8889/compta2", "admin","aaaa");
		Fournisseur nouveauFournisseur = new Fournisseur();

		nouveauFournisseur.setNom("France de matériaux");
		fournisseurDao.insert(nouveauFournisseur);
		nouveauFournisseur.setNom("L'Espace Création");
		fournisseurDao.insert(nouveauFournisseur);

		System.out.println("\n---- Liste de fournisseur mise à jour (INSERT)----");

		List<Fournisseur> fournisseurs = fournisseurDao.extraire();
		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println("Fournisseur : " + fournisseur.getNom());
		}

		System.out.println("\n---- Liste de fournisseur mise à jour (UPDATE)----");

		fournisseurDao.update("France de matériaux", "France matériaux");

		fournisseurs = fournisseurDao.extraire();
		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println("Fournisseur : " + fournisseur.getNom());
		}

		Fournisseur fournisseurASupprimer = new Fournisseur();
		fournisseurASupprimer.setNom("France matériaux");
		fournisseurDao.delete(fournisseurASupprimer);

		System.out.println("\n---- Liste de fournisseur mise à jour (DELETE)----");
		fournisseurs = fournisseurDao.extraire();
		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println("Fournisseur : " + fournisseur.getNom());
		}
	}
}
