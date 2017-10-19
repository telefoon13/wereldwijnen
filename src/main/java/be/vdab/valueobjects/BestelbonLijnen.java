package be.vdab.valueobjects;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class BestelbonLijnen implements Serializable {

	private static final long serialVersionUID = 1L;
	private long wijnid;
	private int aantal;
	private BigDecimal prijs;

	public BestelbonLijnen(long wijnid, int aantal, BigDecimal prijs) {
		this.wijnid = wijnid;
		this.aantal = aantal;
		this.prijs = prijs;
	}

	public BestelbonLijnen() {
	}

	@Basic
	@Column(name = "wijnid")
	public long getWijnid() {
		return wijnid;
	}

	public void setWijnid(long wijnid) {
		this.wijnid = wijnid;
	}

	@Basic
	@Column(name = "aantal")
	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	@Basic
	@Column(name = "prijs")
	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BestelbonLijnen that = (BestelbonLijnen) o;
		return aantal == that.aantal &&
				Objects.equals(wijnid, that.wijnid) &&
				Objects.equals(prijs, that.prijs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wijnid, aantal, prijs);
	}
}
