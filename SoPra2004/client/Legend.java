/*
 * Created on 20.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalBorders;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Legend extends JLabel{
	Legend(){
		//setBorder(new MetalBorders.Flush3DBorder());
		//setSize(30,100);
		initComponents();
	}
	/**
	 * 
	 */
	private void initComponents() {
		//Graphics gr = getGraphics();
		//getRectangle(gr.create());
		
		
	}
	final private int random(){
	    return (int)(Math.random() * 256 );
	}
	public void getRectangle(Graphics gr){
		int r = random();
		int g = random();
		int b = random();
		gr.setColor(new Color(r,g,b));
		gr.fillRect(1,1,25,25);
		gr.setColor(Color.black);
		gr.drawRect(0,0,25,25);
		//return gr;
	}
}
