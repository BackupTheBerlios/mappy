/*
 * Created on 15.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
//import javax.swing.border.Border;

import java.awt.*;
public class StatusBar extends JPanel {
	
	JLabel position=new JLabel("");
	JLabel info=new JLabel("");
	JLabel zoom=new JLabel("");
	private JProgressBar progress;
	
	public StatusBar(JProgressBar progress){
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		setBorder(new MetalBorders.Flush3DBorder());
		
		this.progress = progress;
		
		System.out.println(progress);
		
		position.setText("");
		position.setFont(new Font("Verdana", Font.BOLD, 12));
		zoom.setText("");
		zoom.setFont(new Font("Verdana", Font.BOLD, 12));
		info.setText("");
		info.setFont(new Font("Verdana", Font.BOLD, 12));
		
		add(position);
		add(info);
		add(zoom);
		add(progress);	
	}

	public void setZoom(int z){
		zoom.setText("Zoom: " +z+ "%   ");
		
	}
	public void setInfo(String z){
		info.setText(z + "   ");
	}
	
	public void setPosition(int x, int y){
		position.setText("x: "+x+" y: "+y + "   ");
	}
}
