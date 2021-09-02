package model.bean;

import java.sql.Timestamp;

public class song {
	
	private int id;
	private String name;
	private String previewText;
	private String detailText;
	private Timestamp dateCreate;
	private String picture;
	private int counter;
	private categories Category;
	private String youtube;
	
	public song() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public song(int id, String name, String previewText, String detailText, Timestamp dateCreate, String picture,
			int counter, categories category, String youtube) {
		super();
		this.id = id;
		this.name = name;
		this.previewText = previewText;
		this.detailText = detailText;
		this.dateCreate = dateCreate;
		this.picture = picture;
		this.counter = counter;
		Category = category;
		this.youtube = youtube;
	}
	
	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviewText() {
		return previewText;
	}
	public void setPreviewText(String previewText) {
		this.previewText = previewText;
	}
	public String getDetailText() {
		return detailText;
	}
	public void setDetailText(String detailText) {
		this.detailText = detailText;
	}
	public Timestamp getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public categories getCategory() {
		return Category;
	}
	public void setCategory(categories category) {
		Category = category;
	}
	
}
