package telefoni;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Stavka extends Panel {
	private Label naslov;
	private Label tekst;
	
	public Stavka(String naslov, String tekst) {
		this.naslov = new Label("");
		this.tekst = new Label("");
		setLayout(new GridLayout(2,1));
		setPreferredSize(new Dimension(400,40));
		this.naslov.setFont(new Font("TimesRoman", Font.BOLD, 14));
		this.tekst.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		this.naslov.setText(naslov);
		this.tekst.setText(tekst);	
		
		add(this.naslov);
		add(this.tekst);
	}

	public void promeniNaslov(String n) {
		naslov.setText(n);
		revalidate();
	}
	public void promeniTekst(String n) {
		tekst.setText(n);
		revalidate();
	}
	
	public Label dohvTekst() { return tekst; }
	public Label dohvNaslov() { return naslov; }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);	
	}
}
