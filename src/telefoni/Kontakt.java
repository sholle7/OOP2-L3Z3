package telefoni;

public class Kontakt extends Stavka {
	private String ime;
	private Broj broj;
	public Kontakt(String ime, Broj broj) {
		super(ime, broj.toString());
		this.ime = ime;
		this.broj = broj;
	}
	public Broj dohvBroj() { return broj; }
	public String dohvIme() { return ime; }
}
