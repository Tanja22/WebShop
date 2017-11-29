package jwd21.modul3test1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Pivo {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private String vrsta;
	@Column
	private Double alkohol;
	@Column
	private Double ibu;
	@Column
	private Integer stanje;
	@ManyToOne(fetch=FetchType.EAGER)
	private Pivara pivara;
	
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
	public Pivara getPivara() {
		return pivara;
	}
	
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public void setPivara(Pivara pivara) {
		this.pivara = pivara;
		if(pivara!=null && !pivara.getPiva().contains(this)){
			pivara.getPiva().add(this);
		}
	}
	
	

}
