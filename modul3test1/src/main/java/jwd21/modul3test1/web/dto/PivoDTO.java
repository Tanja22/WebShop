package jwd21.modul3test1.web.dto;

public class PivoDTO {
	
	private Long id;
	private String naziv;
	private String vrsta;
	private Double alkohol;
	private Double ibu;
	private Integer stanje;
	private Long pivaraId;
	private String pivaraNaziv;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getAlkohol() {
		return alkohol;
	}
	public void setAlkohol(Double alkohol) {
		this.alkohol = alkohol;
	}
	public Double getIbu() {
		return ibu;
	}
	public void setIbu(Double ibu) {
		this.ibu = ibu;
	}
	public Integer getStanje() {
		return stanje;
	}
	public void setStanje(Integer stanje) {
		this.stanje = stanje;
	}
	public Long getPivaraId() {
		return pivaraId;
	}
	public void setPivaraId(Long pivaraId) {
		this.pivaraId = pivaraId;
	}
	public String getPivaraNaziv() {
		return pivaraNaziv;
	}
	public void setPivaraNaziv(String pivaraNaziv) {
		this.pivaraNaziv = pivaraNaziv;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

}
