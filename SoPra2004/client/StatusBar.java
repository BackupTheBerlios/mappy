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

import java.awt.*;
public class StatusBar extends JPanel {
	
	JLabel position;
	JLabel info;
	JLabel zoom;
	private JProgressBar progress;
	private JLabel state;
	
	public StatusBar(JProgressBar progress){
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		setBorder(new MetalBorders.Flush3DBorder());
		
		this.progress = progress;
		
		position = new JLabel();
		position.setFont(new Font("Verdana", Font.BOLD, 12));
		zoom = new JLabel();
		zoom.setFont(new Font("Verdana", Font.BOLD, 12));
		info = new JLabel();
		info.setFont(new Font("Verdana", Font.BOLD, 12));
		state = new JLabel(" Fortschritt");
		state.setFont(new Font ("Verdana", Font.BOLD, 12));
		
		
		add(position);
		add(info);
		add(zoom);
		add(progress);
		add(state);
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
