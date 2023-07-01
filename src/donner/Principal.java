package donner;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Principal extends JFrame{
	
	private JButton On_Off = new JButton("on_off");
	private JButton Br = new JButton("brancher");
	private JButton D_Br = new JButton("De_brancher");
	private JButton Lev_1 = new JButton("1");
	private JButton Lev_2 = new JButton("2");
	private JButton Lev_3 = new JButton("3");
	private JFrame MonFenetre = new JFrame();
	private PanelPhoto panelPhoto = new PanelPhoto(MonFenetre);
	private JLabel Description = new JLabel("Description");
	private JLabel Etat = new JLabel("Etat :");
	private JLabel EtatVal = new JLabel("Débrancher");
	private JLabel Accd = new JLabel("Accélération :");
	private JLabel AccVal = new JLabel("0");
	private Timer Acc;
	private Timer Decc;
	private int a;
	private int A_D = 0;
	private boolean on_off_Acc = false;
	private boolean on_off_Decc = false;

	protected void Acceleration(int fin) {
		if(on_off_Decc == true) {
			Decc.stop();
		}
		else if(on_off_Acc == true) {
			Acc.stop();
		}
		if(A_D == 0) {
			
			Acc = new Timer(600 ,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	
					a++;
					panelPhoto.v = a;
					if(a >= fin) {
						Acc.stop();
						on_off_Acc = false;
					}
					AccVal.setText(String.valueOf(a));
	
				}
			});
		}
		else if (A_D == 1) {
			
			Decc = new Timer(400 ,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					a--;
					panelPhoto.v = a;
					if(a <= fin) {
						Decc.stop();
						on_off_Decc = false;
					}
					AccVal.setText(String.valueOf(a));

				}
			});
		}
	}
	
	
	public Principal(){
			
			//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			MonFenetre.setSize(850,770);
			MonFenetre.setBackground(Color.WHITE);
			MonFenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
			MonFenetre.setTitle("Binome_3");
			
			MonFenetre.setResizable(false);
			MonFenetre.setVisible(true);
			
			//Quleque description 
			Description.setBounds(600, 11, 205, 47);
			Description.setFont(new Font("Tahoma", Font.BOLD, 20));
			MonFenetre.add(Description);
			
			Etat.setBounds(610, 21, 110, 75);
			Etat.setFont(new Font("Tahoma", Font.ITALIC, 13));
			MonFenetre.add(Etat);
			
			EtatVal.setBounds(655, 21, 105, 75);
			EtatVal.setFont(new Font("Tahoma", Font.ITALIC, 12));
			MonFenetre.add(EtatVal);
			
			Accd.setBounds(610, 21, 210, 105);
			Accd.setFont(new Font("Tahoma", Font.ITALIC, 13));
			MonFenetre.add(Accd);
			
			AccVal.setBounds(700, 21, 105, 105);
			AccVal.setFont(new Font("Tahoma", Font.ITALIC, 12));
			MonFenetre.add(AccVal);
			
			//les bouton
			
			On_Off.setLayout(null);
			On_Off.setBounds(181,431, 10, 10);
			On_Off.setBackground(new Color(73,50,50));
			MonFenetre.add(On_Off);
			
			Lev_1.setLayout(null);
			Lev_1.setBounds(181,406, 10, 10);
			Lev_1.setBackground(new Color(102,101,77));
			MonFenetre.add(Lev_1);
			
			Lev_2.setLayout(null);
			Lev_2.setBounds(181,393, 10, 10);
			Lev_2.setBackground(new Color(99,124,112));
			MonFenetre.add(Lev_2);
			
			Lev_3.setLayout(null);
			Lev_3.setBounds(181,380, 10, 10);
			Lev_3.setBackground(new Color(114,161,165));
			MonFenetre.add(Lev_3);
			
			Br.setLayout(null);
			Br.setBounds(560,670, 15, 10);
			Br.setBackground(new Color(20,20,20));
			MonFenetre.add(Br);
			
			D_Br.setLayout(null);
			D_Br.setBounds(661,649, 15, 10);
			D_Br.setBackground(new Color(20,20,20));
			MonFenetre.add(D_Br);
			D_Br.setVisible(false);
			
			MonFenetre.add(panelPhoto);
			
					
			Br.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

						panelPhoto.bracher = true;
						if(panelPhoto.Tmp_sess > 0) {
							a = 1;
							Acceleration(panelPhoto.Tmp_sess);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
						}
						Br.setVisible(false);
						D_Br.setVisible(true);
						EtatVal.setText(String.valueOf("Brancher"));
						repaint();

				}

			});
			
			D_Br.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
						panelPhoto.bracher = false;
						panelPhoto.Tmp_sess = panelPhoto.v;
						A_D = 1;
						if(panelPhoto.v == 0) {
							a=0;
						}
						else {
							Acceleration(0);
							Decc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = false;
							on_off_Decc = true;
						}	
						panelPhoto.v = a;
						D_Br.setVisible(false);
						Br.setVisible(true);
						EtatVal.setText(String.valueOf("Débrancher"));
						repaint();

				}

			});
			
			On_Off.addActionListener(new ActionListener() {
				 

				public void actionPerformed(ActionEvent e) {
					if(panelPhoto.bracher == true) {
						if(panelPhoto.v == 0) {
							a = 0;
							Acceleration(15);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							
							
						}else {
							A_D = 1;
							Acceleration(0);
							Decc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = false;
							on_off_Decc = true;
							panelPhoto.v = a;
							
							
						}
						
						Lev_1.setEnabled(true);
						Lev_2.setEnabled(true);
						Lev_3.setEnabled(true);
						panelPhoto.repaint();
					}
				}

			});
			
			Lev_1.addActionListener(new ActionListener() {
				 

				public void actionPerformed(ActionEvent e) {
					if(panelPhoto.bracher == true) {
						
						if(panelPhoto.v == 0) {
							a = 1;
							
							Acceleration(25);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							repaint();

						}
						else if((panelPhoto.v == a) && (a < 25)){
							
							Acceleration(25);
							on_off_Acc = true;
							Acc.start();
							AccVal.setText(String.valueOf(a));
							panelPhoto.v = a;
							
							
						}
						else if((panelPhoto.v == a) && (a > 25)) {
							A_D = 1;
							Acceleration(25);
							Decc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = false;
							on_off_Decc = true;
							panelPhoto.v = a;
							
						}
						Lev_1.setEnabled(false);
						Lev_2.setEnabled(true);
						Lev_3.setEnabled(true);
						
						panelPhoto.repaint();
					}
				}

			});
			
			Lev_2.addActionListener(new ActionListener() {
				 

				public void actionPerformed(ActionEvent e) {
					if(panelPhoto.bracher == true) {
						if(panelPhoto.v == 0) {
							a = 1;
							
							Acceleration(35);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							
						}
						else if((panelPhoto.v == a) && (a < 35)){
							
							Acceleration(35);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							
						}
						
						else if((panelPhoto.v == a) && (a > 35)) {
							A_D = 1;
							Acceleration(35);
							Decc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = false;
							on_off_Decc = true;
							panelPhoto.v = a;
							
						}
						
						Lev_1.setEnabled(true);
						Lev_2.setEnabled(false);
						Lev_3.setEnabled(true);
						
						panelPhoto.repaint();
					}
				}

			});
			
			Lev_3.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(panelPhoto.bracher == true) {
						if(panelPhoto.v == 0) {
							a = 1;
							
							Acceleration(45);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							
							
							
						}
						else if((panelPhoto.v == a )&& (a < 45 )){
							
							Acceleration(45);
							Acc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = true;
							panelPhoto.v = a;
							
							
						}
						else if((panelPhoto.v == a) && (a > 45)) {
							//Cas ou on rabaisse le niveaux de rotation
							Acceleration(45);
							Decc.start();
							AccVal.setText(String.valueOf(a));
							on_off_Acc = false;
							on_off_Decc = true;
							panelPhoto.v = a;
							
						}
						Lev_1.setEnabled(true);
						Lev_2.setEnabled(true);
						Lev_3.setEnabled(false);
						
						panelPhoto.repaint();
					}
				}

			});
	}


	public static void main(String[] args) {
		
		new Principal();
	}

}
