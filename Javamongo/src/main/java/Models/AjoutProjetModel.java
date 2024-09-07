package Models;

public class AjoutProjetModel {
	String NomProjet;
	String CategorieProjet;
	String TypeProjet;
	String Date_debutProjet;
	String Date_finProjet;
	String DescriptionProjet;
	
	public AjoutProjetModel() {
		
	}
	
	public AjoutProjetModel(String NomProjet, String CategorieProjet, String TypeProjet, String Date_debutProjet,
			String Date_finProjet, String DescriptionProjet) {
		super();
		this.NomProjet = NomProjet;
		this.CategorieProjet = CategorieProjet;
		this.TypeProjet = TypeProjet;
		this.Date_debutProjet = Date_debutProjet;
		this.Date_finProjet = Date_finProjet;
		this.DescriptionProjet = DescriptionProjet;
	}
	public String getNomProjet() {
		return NomProjet;
	}
	public void setNomProjet(String nomProjet) {
		NomProjet = nomProjet;
	}
	public String getCategorieProjet() {
		return CategorieProjet;
	}
	public void setCategorieProjet(String categorieProjet) {
		CategorieProjet = categorieProjet;
	}
	public String getTypeProjet() {
		return TypeProjet;
	}
	public void setTypeProjet(String typeProjet) {
		TypeProjet = typeProjet;
	}
	public String getDate_debutProjet() {
		return Date_debutProjet;
	}
	public void setDate_debutProjet(String date_debutProjet) {
		Date_debutProjet = date_debutProjet;
	}
	public String getDate_finProjet() {
		return Date_finProjet;
	}
	public void setDate_finProjet(String date_finProjet) {
		Date_finProjet = date_finProjet;
	}
	public String getDescriptionProjet() {
		return DescriptionProjet;
	}
	public void setDescriptionProjet(String descriptionProjet) {
		DescriptionProjet = descriptionProjet;
	}

	
}
