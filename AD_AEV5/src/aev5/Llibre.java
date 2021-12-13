package aev5;

public class Llibre {
	int id;
	String titol;
	String autor;
	String any_Naixement;
	String any_Publicacio;
	String editorial;
	String nombrePagines;
	
	public Llibre(String tit,String aut,String any_naix,String any_pub,String edit,String nombrePag) {
		this.titol=tit;
		this.autor=aut;
		this.any_Naixement=any_naix;
		this.any_Publicacio=any_pub;
		this.editorial=edit;
		this.nombrePagines=nombrePag;
		
	}

	public Llibre() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAny_Naixement() {
		return any_Naixement;
	}

	public void setAny_Naixement(String any_Naixement) {
		this.any_Naixement = any_Naixement;
	}

	public String getAny_Publicacio() {
		return any_Publicacio;
	}

	public void setAny_Publicacio(String any_Publicacio) {
		this.any_Publicacio = any_Publicacio;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getNombrePagines() {
		return nombrePagines;
	}

	public void setNombrePagines(String nombrePagines) {
		this.nombrePagines = nombrePagines;
	}


}
