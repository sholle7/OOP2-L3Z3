package telefoni;

public class Imenik extends ListaStavki {
	@Override
	public void dodajStavku(Stavka s) {
		//moguce je dodati stavku samo ako je ona kontakt
		if(s instanceof Kontakt) {
			super.dodajStavku(s);
		}	
	}
	
	//dohvatanje imena stavke na osnovu zadatog broja
	public String dohvIme(Broj b) throws GNePostoji {
		for (Stavka s: stavke) {
			Kontakt k = (Kontakt)s;
			if(b.istiBroj(k.dohvBroj())) return k.dohvIme();
		}
		throw new GNePostoji();
	}
	
	//dohvatanje broja telefona korisnika sa datim imenom
	public Broj dohvBroj(String ime) throws GNePostoji {
		for (Stavka s: stavke) {
			Kontakt k = (Kontakt)s;
			if(k.dohvIme().equals(ime)) return k.dohvBroj();
		}
		throw new GNePostoji();
	}
	
}
