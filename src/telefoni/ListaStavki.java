package telefoni;

import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;

public class ListaStavki extends Panel {
	protected ArrayList<Stavka> stavke = new ArrayList<>();
	public void dodajStavku(Stavka s) {
		stavke.add(s);
		add(s);
		revalidate();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
