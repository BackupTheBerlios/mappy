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
import javax.swing.border.Border;

import java.awt.*;
public class StatusBar extends JPanel {
	
	JLabel position=new JLabel("");
	JLabel info=new JLabel("");
	JLabel zoom=new JLabel("");
	
	public StatusBar(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		position.setHorizontalAlignment(JLabel.LEFT);
		info.setHorizontalAlignment(JLabel.CENTER);
		zoom.setHorizontalAlignment(JLabel.RIGHT);
		
		position.setPreferredSize(new Dimension(110,15));
		zoom.setPreferredSize(new Dimension(110,15));
		
		position.setText("");
		zoom.setText("");
		info.setText("");
		
		this.add(position);
		this.add(info);
		this.add(zoom);
		
	}
	public void setZoom(int z){
		zoom.setText("Zoom: " +z+ "%");
		
	}
	public void setInfo(String z){
		info.setText(z);
	}
	
	public void setPosition(int x, int y){
		position.setText("x: "+x+" y: "+y);
	}

}
