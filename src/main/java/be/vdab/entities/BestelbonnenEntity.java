package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import be.vdab.valueobjects.BestelbonLijnen;

@Entity
@Table(name = "bestelbonnen", schema = "wereldwijnen")
public class BestelbonnenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private Timestamp besteld;
	private String naam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	private int bestelwijze;
	private int versie;
	private Set<BestelbonLijnen> bestelbonLijnen;

	public BestelbonnenEntity(Timestamp besteld, String naam, String straat, String huisNr, String postCode, String gemeente, int bestelwijze) {
		this.besteld = besteld;
		this.naam = naam;
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postCode;
		this.gemeente = gemeente;
		this.bestelwijze = bestelwijze;
		bestelbonLijnen = new HashSet<>();
	}

	public BestelbonnenEntity() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "besteld")
	public Timestamp getBesteld() {
		return besteld;
	}

	public void setBesteld(Timestamp besteld) {
		this.besteld = besteld;
	}

	@Basic
	@Column(name = "naam")
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Basic
	@Column(name = "straat")
	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	@Basic
	@Column(name = "huisNr")
	public String getHuisNr() {
		return huisNr;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	@Basic
	@Column(name = "postCode")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Basic
	@Column(name = "gemeente")
	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	@Basic
	@Column(name = "bestelwijze")
	public int getBestelwijze() {
		return bestelwijze;
	}

	public void setBestelwijze(int bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

	@Version
	public int getVersie() {
		return versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	public Set<BestelbonLijnen> getBestelbonLijnen() {
		return bestelbonLijnen;
	}

	public void setBestelbonLijnen(Set<BestelbonLijnen> bestelbonLijnen) {
		this.bestelbonLijnen = bestelbonLijnen;
	}

	public void addBestelbonLijnen(BestelbonLijnen bestelbonLijn){
		bestelbonLijnen.add(bestelbonLijn);
	}

	public void removeBestelbonLijnen(BestelbonLijnen bestelbonLijn){
		bestelbonLijnen.remove(bestelbonLijn);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BestelbonnenEntity that = (BestelbonnenEntity) o;

		if (id != that.id) return false;
		if (bestelwijze != that.bestelwijze) return false;
		if (besteld != null ? !besteld.equals(that.besteld) : that.besteld != null) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;
		if (straat != null ? !straat.equals(that.straat) : that.straat != null) return false;
		if (huisNr != null ? !huisNr.equals(that.huisNr) : that.huisNr != null) return false;
		if (postCode != null ? !postCode.equals(that.postCode) : that.postCode != null) return false;
		if (gemeente != null ? !gemeente.equals(that.gemeente) : that.gemeente != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (besteld != null ? besteld.hashCode() : 0);
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		result = 31 * result + (straat != null ? straat.hashCode() : 0);
		result = 31 * result + (huisNr != null ? huisNr.hashCode() : 0);
		result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
		result = 31 * result + (gemeente != null ? gemeente.hashCode() : 0);
		result = 31 * result + bestelwijze;
		return result;
	}
}
