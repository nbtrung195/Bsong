package model.bean;

public class categories {
	
	private int idCat;
	private String NameCat;
	
	public categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public categories(int idCat, String nameCat) {
		super();
		this.idCat = idCat;
		NameCat = nameCat;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public String getNameCat() {
		return NameCat;
	}

	public void setNameCat(String nameCat) {
	}
	
}
