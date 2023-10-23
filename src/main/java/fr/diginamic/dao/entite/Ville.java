package fr.diginamic.dao.entite;

public class Ville {
	
	private int id;
	private String nom;
	private int population;
	private String idDepts;
	private String idRegion;
	
	public Ville(int id, String nom, int population, String idDepts, String idRegion) {
		super();
		this.id = id;
		this.nom = nom;
		this.population = population;
		this.idDepts = idDepts;
		this.idRegion = idRegion;
	}
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", population=" + population + ", idDepts=" + idDepts
				+ ", idRegion=" + idRegion + "]";
	}
	
	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/** Setter
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/** Getter
	 * @return the idDepts
	 */
	public String getIdDepts() {
		return idDepts;
	}

	/** Setter
	 * @param idDepts the idDepts to set
	 */
	public void setIdDepts(String idDepts) {
		this.idDepts = idDepts;
	}

	/** Getter
	 * @return the idRegion
	 */
	public String getIdRegion() {
		return idRegion;
	}

	/** Setter
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}
	
}
