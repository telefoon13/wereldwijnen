package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wijnen", schema = "wereldwijnen")
public class WijnenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private int jaar;
	private int beoordeling;
	private BigDecimal prijs;
	private int inBestelling;
	private int versie;
	private SoortenEntity soortid;

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
	@Column(name = "jaar")
	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	@Basic
	@Column(name = "beoordeling")
	public int getBeoordeling() {
		return beoordeling;
	}

	public void setBeoordeling(int beoordeling) {
		this.beoordeling = beoordeling;
	}

	@Basic
	@Column(name = "prijs")
	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	@Basic
	@Column(name = "inBestelling")
	public int getInBestelling() {
		return inBestelling;
	}

	public void setInBestelling(int inBestelling) {
		this.inBestelling = inBestelling;
	}

	@Version
	public int getVersie() {
		return versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "soortid")
	public SoortenEntity getSoortid() {
		return soortid;
	}

	public void setSoortid(SoortenEntity soortid) {
		this.soortid = soortid;
	}

	public void toevoegenInBestelling(int aantal){
		this.inBestelling += aantal;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WijnenEntity that = (WijnenEntity) o;

		if (id != that.id) return false;
		if (jaar != that.jaar) return false;
		if (beoordeling != that.beoordeling) return false;
		if (inBestelling != that.inBestelling) return false;
		if (prijs != null ? !prijs.equals(that.prijs) : that.prijs != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + jaar;
		result = 31 * result + beoordeling;
		result = 31 * result + (prijs != null ? prijs.hashCode() : 0);
		result = 31 * result + inBestelling;
		return result;
	}
}
