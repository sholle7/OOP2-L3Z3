package telefoni;

public class Broj {
	private int kodDrzave;
	private int pozivniBroj;
	private int brojPretplatnika;
	
	public Broj(int kodDrzave, int pozivniBroj, int brojPretplatnika){
		this.kodDrzave = kodDrzave;
		this.pozivniBroj = pozivniBroj;
		this.brojPretplatnika = brojPretplatnika;
	}
	public Broj(String b) {
		kodDrzave = Integer.valueOf(b.substring(0, 3));
		pozivniBroj = Integer.valueOf(b.substring(3, 5));
		brojPretplatnika = Integer.valueOf(b.substring(5, b.length()));
	}
	
	public boolean istaDrzava(Broj br) {
		return kodDrzave == br.kodDrzave;
	}
	
	public boolean istaMreza(Broj br) {
		return istaDrzava(br) && pozivniBroj==br.pozivniBroj;
	}
	
	public boolean istiBroj(Broj br) {
		return toString().equals(br.toString());
	}
	
	@Override
	public String toString() {
		return "+" + kodDrzave + " "+ pozivniBroj+ " "+ brojPretplatnika;
	}
	
}
