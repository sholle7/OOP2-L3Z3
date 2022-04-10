package telefoni;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tastatura extends Panel implements Runnable {
	
	private Thread nit;
	private Label natpisBr, natpisIme ;
	private Panel panelDugmad, pSever;
	private Button[] dugmad; 
	private int rezimRada;
	private boolean radi;
	private long prethodni = -1, sadasnji;
	private int br;
	private Button prethodnoDugme, trenutnoDugme;
	private String st;
	
	public Tastatura(Label nBr, Label nIme) {
		setLayout(new BorderLayout());
		rezimRada = 1;
		prethodnoDugme = new Button("");
		trenutnoDugme = new Button("");
		panelDugmad = new Panel(new GridLayout(4,3));
		dugmad = new Button[12];
		pSever = new Panel(new GridLayout(2,1));
		for(int i=0; i<9 ; i++) panelDugmad.add(dugmad[i]=new Button(String.valueOf(i)));	
		panelDugmad.add(dugmad[9] = new Button("*"));
		panelDugmad.add(dugmad[10] = new Button("0"));
		panelDugmad.add(dugmad[11] = new Button("+"));
		
		nit=new Thread(this);
		//startovanje niti
		nit.start();
		
		//podesavanje velicine i fonta buttona i dodavanje listenera		
		for(int i=0; i<12 ; i++) {
			dugmad[i].setFont(new Font("TimesRoman", Font.BOLD, 14));
			dugmad[i].addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
					//dohvatanje pritisnutog dugmeta
					trenutnoDugme = (Button)e.getSource();
					
					//tekst trenutnog dugmeta
					st = trenutnoDugme.getLabel();
					//ako je u prvom rezimu rada
					if(rezimRada==1) {
						natpisBr.setText(natpisBr.getText() + st);
					}
					//ako je u drugom rezimu rada
					else {
						sadasnji = System.currentTimeMillis();
						nastavi();
					}									
				}
			});
		}
		natpisBr = nBr;
		natpisBr.setPreferredSize(new Dimension(400,20));
		nBr.setPreferredSize(new Dimension(400,20));
		natpisIme = nIme;
		pSever.add(natpisBr);
		pSever.add(natpisIme);
		add(pSever, BorderLayout.NORTH);
		add(panelDugmad, BorderLayout.CENTER);
	}
	
	//vracanje rezima rada na pocetni rezim
	public void vratiRezimRada() {
		rezimRada=1;
		repaint();
	}
	//promena rezima rada
	public void promeniRezimRada() {
		if(rezimRada == 1) {
			rezimRada = 2;
			repaint();
		}
		else {
			rezimRada = 1;
			repaint();	
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(rezimRada==1) {
			for(int i=0; i<9 ; i++)dugmad[i].setLabel(String.valueOf(i));
			dugmad[9].setLabel("*");
			dugmad[10].setLabel("0");
			dugmad[11].setLabel("+");
		}
		else {
			dugmad[0].setLabel("");
			dugmad[1].setLabel("ABC");
			dugmad[2].setLabel("DEF");
			dugmad[3].setLabel("GHI");
			dugmad[4].setLabel("JKL");
			dugmad[5].setLabel("MNO");
			dugmad[6].setLabel("PQRS");
			dugmad[7].setLabel("TUV");	
			dugmad[8].setLabel("WXYZ");
			dugmad[9].setLabel("");
			dugmad[10].setLabel("_");
			dugmad[11].setLabel("");
		}
	}
	//dohvatanje broja i imena
	public String dohvBroj() {
		return natpisBr.getText();
	}
	public String dohvIme() {
		return natpisIme.getText();
	}
	//postavljanje broja i imena
	public void postBroj(String t) {
		natpisBr.setText(t);
	}
	public void postIme(String t) {
		natpisIme.setText(t);
	}
	
	@Override
	public void run() {
			try {
				while(!Thread.interrupted()) {
					
					//cekanje dok se ne pritisne neko dugme tastature
					synchronized (this) {
						while(!radi)wait();
					}
					
					//ako je prosla sekunda ili je pritisnuto drugo dugme
					if(sadasnji - prethodni > 1000 || !st.equals(prethodnoDugme.getLabel())) {
						br=0;
						natpisIme.setText(natpisIme.getText() + st.charAt(0));
					}
					
					//ako nije prosla sekunda ali je pritisnuto drugo dugme
					else if(sadasnji - prethodni < 1000 && !st.equals(prethodnoDugme.getLabel())){
						br=0;
						natpisIme.setText(natpisIme.getText() + st.charAt(0));
					}
					
					//ako je pritisnuto isto dugme ali nije prosla jedna sekunda
					else if(sadasnji - prethodni < 1000 && st.equals(prethodnoDugme.getLabel())){
						br++;
						if (br >= st.length()) br = 0;
						String s = natpisIme.getText().substring(0,natpisIme.getText().length()-1) + st.charAt(br);
						natpisIme.setText(s);
					}
					revalidate();
					//prethodno vreme se postavlja na sadasnje		
					prethodni=sadasnji;
					//prethodno dugme se postavlja na sadasnje
					prethodnoDugme=trenutnoDugme;
					//privremeno zaustvljanje niti
					radi=false;
				}
			}catch (InterruptedException e) {}		
	}
	public synchronized void zaustavi() { nit.interrupt();}
	public synchronized void nastavi() { radi=true; notify();}
}
