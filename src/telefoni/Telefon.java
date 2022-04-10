package telefoni;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Telefon extends Panel {
	private Broj broj;
	private Color boja;
	private Imenik im;
	private Tastatura t;
	private Button statusUkljucen, dodajKontakt;
	private Panel pJugPom, pJug;
	private Label juznaLabela;
	public Telefon(Broj broj, Color boja) {
		setLayout(new BorderLayout());
		statusUkljucen = new Button("Iskljuci telefon");
		statusUkljucen.setBackground(Color.gray);
		dodajKontakt = new Button("Dodaj kontakt");
		pJugPom = new Panel(new GridLayout(1,2));
		pJug = new Panel(new GridLayout(2,1));
		dodajOsluskivace();
		this.broj = broj;
		this.boja = boja;
		juznaLabela = new Label(broj.toString(),Label.CENTER);
		juznaLabela.setFont(new Font("TimesRoman", Font.BOLD, 14));
		
		setBackground(this.boja);
		pJugPom.add(dodajKontakt);
		pJugPom.add(statusUkljucen);
		pJug.add(pJugPom);
		pJug.add(juznaLabela);
		add(pJug,BorderLayout.SOUTH);
	}
	
	//postavljanje imenika i tastature
	public void postaviImenTast(Imenik im, Tastatura t) {
		this.im=im;
		this.t=t;
		t.setPreferredSize(new Dimension(350,300));
		add(im, BorderLayout.CENTER);
		add(t, BorderLayout.NORTH);
	}
	public void dodajOsluskivace() {
		
		statusUkljucen.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				promeniUkljuci();
				revalidate();
			}
		});
		
		dodajKontakt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.promeniRezimRada();
				if(!t.dohvIme().equals("") && !t.dohvBroj().equals("")) {
					im.dodajStavku(new Kontakt(t.dohvIme(),new Broj(t.dohvBroj().substring(1,t.dohvBroj().length()))));
					revalidate();
					t.postIme("");
					t.postBroj("");
					t.vratiRezimRada();
				}
			}
		});
		
	}
	public Broj dohvBroj() { return broj; }
	
	//promena stanja 
	public void promeniUkljuci() {
		if(statusUkljucen.getLabel().equals("Iskljuci telefon")) {
			statusUkljucen.setLabel("Ukljuci telefon");
			statusUkljucen.setBackground(Color.red);
			
		}
		else {
			statusUkljucen.setBackground(Color.gray);
			statusUkljucen.setLabel("Iskljuci telefon");
		}
	}
}
