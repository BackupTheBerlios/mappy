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
	private JLabel zoomLabel;
	private JTextField pointX;
	
	public StatusBar(JProgressBar progress){
		setLayout(new GridLayout(2,5));
		setBorder(new MetalBorders.Flush3DBorder());
		this.progress = progress;
		
		
		pointX = new JTextField(2);
		position = new JLabel();
		position.setFont(new Font("Verdana", Font.PLAIN, 11));
		zoom = new JLabel();
		zoom.setFont(new Font("Verdana", Font.PLAIN, 11));
		info = new JLabel();
		info.setFont(new Font("Verdana", Font.PLAIN, 11));
		state = new JLabel(" Fortschritt   ");
		state.setFont(new Font ("Verdana", Font.PLAIN, 11));
		
		
		add(pointX);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(position);
		add(zoom);
		add(progress);
		add(state);
		add(info);
	}

	public void setZoom(int z){
		zoom.setText("Zoom: " +z+ "%   ");
		
	}
	public void setInfo(String z){
		info.setText(z + "   ");
	}
	
	public void setPosition(int x, int y){
		position.setText(" x: "+x+" y: "+y + "   ");
	}

	/**
	 * @return
	 */
	public JLabel getInfo() {
		return info;
	}
}
