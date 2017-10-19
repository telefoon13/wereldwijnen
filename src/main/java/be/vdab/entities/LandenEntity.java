package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "landen", schema = "wereldwijnen")
public class LandenEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private int versie;
	private Set<SoortenEntity> soorten;

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
	@Column(name = "naam")
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Version
	public int getVersie() {
		return versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	@OneToMany
	@JoinColumn(name = "landid")
	@OrderBy("naam")
	public Set<SoortenEntity> getSoorten() {
		return soorten;
	}

	public void setSoorten(Set<SoortenEntity> soorten) {
		this.soorten = soorten;
	}

	public void add(SoortenEntity soort){
		soorten.add(soort);
	}

	public void remove(SoortenEntity soort){
		soorten.remove(soort);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LandenEntity that = (LandenEntity) o;

		if (id != that.id) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}
}
