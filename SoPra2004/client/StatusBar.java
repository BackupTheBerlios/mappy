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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class StatusBar extends JPanel {
	
	private JLabel info;
	private JLabel zoom;
	private JProgressBar progress;
	private JLabel state;
	private JLabel zoomLabel;
	private JTextField pointX;
	private JTextField pointY;
	private JToolBar xPointBar;
	private JToolBar yPointBar;
	
	
	public StatusBar(JProgressBar progress){
		setLayout(new GridLayout(2,5));
		setBorder(new MetalBorders.Flush3DBorder());
		this.progress = progress;
		
		pointX = new JFormattedTextField();
		pointX.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				try{
					new Integer(pointX.getText());
				}
				catch(NumberFormatException e){
					if(pointX.getText().length() > 1){
						String text = pointX.getText().substring(0, pointX.getText().length() - 1);
						pointX.setText(text);
					}
					else{
						pointX.setText(null);
					}
				}
			}
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}			
		});
		pointY = new JFormattedTextField();
		pointY.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				try{
					new Integer(pointY.getText());
				}
				catch(NumberFormatException e){
					if(pointY.getText().length() > 1){
						String text = pointY.getText().substring(0, pointY.getText().length() - 1);
						pointY.setText(text);
					}
					else{
						pointY.setText(null);
					}
				}
			}
			public void keyTyped(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}			
		});
		
		xPointBar = new JToolBar();
		xPointBar.setFloatable(false);
		xPointBar.add(new JLabel(" X-Wert "));
		xPointBar.add(pointX);
		
		yPointBar = new JToolBar();
		yPointBar.setFloatable(false);
		yPointBar.add(new JLabel(" Y-Wert "));
		yPointBar.add(pointY);
		
		zoom = new JLabel();
		zoom.setFont(new Font("Verdana", Font.PLAIN, 11));
		info = new JLabel();
		info.setFont(new Font("Verdana", Font.PLAIN, 11));
		state = new JLabel(" Fortschritt   ");
		state.setFont(new Font ("Verdana", Font.PLAIN, 11));
		
		add(xPointBar);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(yPointBar);
		add(zoom);
		add(progress);
		add(state);
		add(info);
	}

	public void setZoom(int z){
		zoom.setText("  Zoom: " +z+ "%   ");
		
	}
	public void setInfo(String z){
		info.setText(z + "   ");
	}

	/**
	 * @return
	 */
	public JLabel getInfo(){
		return info;
	}

	/**
	 * @return
	 */
	public Point getUpperLeft() {
		Point upperLeft = null;
		Integer x;
		Integer y;
		try{
			x = new Integer(pointX.getText());
			y = new Integer(pointY.getText());
			upperLeft = new Point(x.intValue(), y.intValue());
		}
		catch(NumberFormatException nfex){
			pointX.setText(null);
			pointY.setText(null);
		}
		return upperLeft;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setXY(int x, int y){
		pointX.setText(new Integer(x).toString());
		pointY.setText(new Integer(y).toString());
	}
}
