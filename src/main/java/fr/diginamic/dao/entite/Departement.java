package fr.diginamic.dao.entite;

public class Departement {
	
	private int id;
	private String code;
	
	public Departement(int id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	@Override
	public String toString() {
		return "Departement [idDepts=" + id + ", code=" + code + "]";
	}
	
	
	/** Getter
	 * @return the idDepts
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param idDepts the idDepts to set
	 */
	public void setId(int idDepts) {
		this.id = idDepts;
	}

	/** Getter
	 * @return the nom
	 */
	public String getCode() {
		return code;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
