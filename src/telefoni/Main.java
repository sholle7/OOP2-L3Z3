package telefoni;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Frame {
	private Telefon t1, t2;
	private Tastatura tast1, tast2;
	private Imenik im1, im2;
	private Label lBr1, lIme1, lBr2, lIme2;
	
	public Main(){
		super("Telefoni");
		setLayout(new GridLayout(1,2));
		lBr1 = new Label("");
		lIme1 = new Label("");
		lBr2 = new Label("");
		lIme2 = new Label("");
		
		tast1 = new Tastatura(lBr1, lIme1);
		tast2 = new Tastatura(lBr2, lIme2);
		
		im1= new Imenik();
		im2 = new Imenik();
		
		t1 = new Telefon(new Broj("3816311"),Color.green);
		t2 = new Telefon(new Broj("3876433"),Color.yellow);
		
		t1.postaviImenTast(im1, tast1);
		t2.postaviImenTast(im2, tast2);
		
		add(t1);
		add(t2);
		dodajOsluskivace();
		setSize(800,600);
		setResizable(false);
		setVisible(true);
		//pack();
		setLocationRelativeTo(null);
	}
	private void dodajOsluskivace() {
		
		//osluskivac za gasenje programa
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				tast1.zaustavi();
				tast2.zaustavi();
				dispose();		
			}
		});
		
	}
	public static void main(String[] args) {
		new Main();
	}

}
