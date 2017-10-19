package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "soorten", schema = "wereldwijnen")
public class SoortenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private int versie;
	private LandenEntity landid;
	private Set<WijnenEntity> wijnen;

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

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "landid")
	public LandenEntity getLandid() {
		return landid;
	}

	public void setLandid(LandenEntity landid) {
		this.landid = landid;
	}

	@OneToMany
	@JoinColumn(name = "soortid")
	@OrderBy("jaar")
	public Set<WijnenEntity> getWijnen() {
		return wijnen;
	}

	public void setWijnen(Set<WijnenEntity> wijnen) {
		this.wijnen = wijnen;
	}

	public void add(WijnenEntity wijn){
		wijnen.add(wijn);
	}

	public void remove(WijnenEntity wijn){
		wijnen.remove(wijn);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SoortenEntity that = (SoortenEntity) o;

		if (id != that.id) return false;
		if (naam != null ? !naam.equals(that.naam) : that.naam != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) id;
		result = 31 * result + (naam != null ? naam.hashCode() : 0);
		return result;
	}
}
