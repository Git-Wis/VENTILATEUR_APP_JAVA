package donner;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelPhoto extends JPanel{

	

	public int i=0;
	public int v=0;
	public int Tmp_sess =0;
	public Boolean bracher = false;
	JFrame fenetre;
	public PanelPhoto(JFrame f) {
		fenetre = f;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		BufferedImage couvD = LoadImage("img/CouvD.png");
		AffineTransform pos_couvD = AffineTransform.getTranslateInstance(20,21);
		g2D.drawImage(couvD,pos_couvD,null);
		fenetre.repaint();
		
		BufferedImage ventilo = LoadImage("img/Vent.png");
		
		AffineTransform at = AffineTransform.getTranslateInstance(40,25);
		at.rotate(Math.toRadians((this.i++)*this.v),ventilo.getWidth()/2,ventilo.getHeight()/2);
		
		g2D.drawImage(ventilo,at,null);
		fenetre.repaint();
		
		if(this.bracher == false) {
			BufferedImage pied = LoadImage("img/corpsDB.png");
			AffineTransform pos_pied = AffineTransform.getTranslateInstance(15,0);
			g2D.drawImage(pied,pos_pied,null);
			fenetre.repaint();
		}
		else {
			BufferedImage pied = LoadImage("img/corpsB.png");
			AffineTransform pos_pied = AffineTransform.getTranslateInstance(14,0);
			g2D.drawImage(pied,pos_pied,null);
			fenetre.repaint();
		}
		
	}
	
	BufferedImage LoadImage(String FileName)
	{
		BufferedImage img = null;
		
		try {
		img = ImageIO.read(new File(FileName));
		}
		catch(IOException e){
			
		}
		return img;
	}


}

