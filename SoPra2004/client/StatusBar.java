package client;

/**
 * The Status Bar of the GUI with all its Labels
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StatusBar extends JPanel {

	private JLabel info;

	private JLabel zoomLabel;

	private JProgressBar progress;

	private JLabel state;

	private JTextField pointX;

	private JTextField pointY;

	private JToolBar xPointBar;

	private JToolBar yPointBar;

	private Gui owner;

	private int zoomValue;

	public StatusBar(JProgressBar progress, Gui owner) {
		setLayout(new GridLayout(2, 5));
		setBorder(new MetalBorders.Flush3DBorder());
		this.progress = progress;
		this.owner = owner;

		pointX = new JFormattedTextField();
		pointX.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				checkX();
				setRefreshStatus();
			}

			public void keyTyped(KeyEvent arg0) {
				checkX();
				setRefreshStatus();
			}

			public void keyReleased(KeyEvent arg0) {
				checkX();
				setRefreshStatus();
			}
		});
		pointY = new JFormattedTextField();
		pointY.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				checkY();
				setRefreshStatus();
			}

			public void keyTyped(KeyEvent arg0) {
				checkY();
				setRefreshStatus();
			}

			public void keyReleased(KeyEvent arg0) {
				checkY();
				setRefreshStatus();
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

		zoomLabel = new JLabel();
		zoomLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		info = new JLabel();
		info.setFont(new Font("Verdana", Font.PLAIN, 11));
		state = new JLabel(" Fortschritt   ");
		state.setFont(new Font("Verdana", Font.PLAIN, 11));

		add(xPointBar);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(yPointBar);
		add(zoomLabel);
		add(progress);
		add(state);
		add(info);
	}

	/**
	 * Sets the Refresh Status
	 */
	protected void setRefreshStatus() {
		if (pointX.getText().length() > 0 && pointY.getText().length() > 0) {
			owner.setRefreshEnabled(true);
		} else {
			owner.setRefreshEnabled(false);
		}
	}

	/**
	 * Checks the X-Value on its format
	 */
	private void checkX() {
		try {
			new Integer(pointX.getText());
			if (pointX.getText().length() > 5) {
				Toolkit.getDefaultToolkit().beep();
				String text = pointX.getText().substring(0,
						pointX.getText().length() - 1);
				pointX.setText(text);
			}
		} catch (NumberFormatException e) {
			if (pointX.getText().length() > 0) {
				Toolkit.getDefaultToolkit().beep();
				pointX.setText("0");
			}
		}
	}

	/**
	 * Checks the Y-Value on its format
	 */
	private void checkY() {
		try {
			new Integer(pointY.getText());
			if (pointY.getText().length() > 5) {
				Toolkit.getDefaultToolkit().beep();
				String text = pointY.getText().substring(0,
						pointY.getText().length() - 1);
				pointY.setText(text);
			}
		} catch (NumberFormatException e) {
			if (pointY.getText().length() > 0) {
				Toolkit.getDefaultToolkit().beep();
				pointY.setText("0");
			}
		}
	}

	/**
	 * Sets the text for the zoom-Label
	 * 
	 * @param zoom
	 *            the Zoom-value as an int
	 */
	public void setZoom(int zoom) {
		zoomLabel.setText(" Zoom: " + zoom + "%");
	}

	/**
	 * Sets the current Zoom Value
	 * 
	 * @return the current zoom Value as an int
	 */
	public void setZoomValue(int zoom) {
		zoomValue = zoom;
	}

	/**
	 * Returns the Zoom Value
	 * 
	 * @return the current zoom Value as an int
	 */
	public int getZoomValue() {
		return zoomValue;

	}

	/**
	 * Sets an Info Text
	 * 
	 * @param z
	 *            the defined String for an Info Text
	 */
	public void setInfo(String z) {
		info.setText(z + "   ");
	}

	/**
	 * Returns the current Info Text
	 * 
	 * @return the current Info Text as a JLabel
	 */
	public JLabel getInfo() {
		return info;
	}

	/**
	 * Returns the upperLeft of the Map
	 * 
	 * @return the current Point upperLeft of the map
	 */
	Point getUpperLeft() {
		Point upperLeft = null;
		Integer x;
		Integer y;
		try {
			x = new Integer(pointX.getText());
			y = new Integer(pointY.getText());
			upperLeft = new Point(x.intValue(), y.intValue());
		} catch (NumberFormatException nfex) {
			pointX.setText(null);
			pointY.setText(null);
		}
		return upperLeft;
	}

	/**
	 * Sets the upperLeft of the map
	 * 
	 * @param x
	 *            the defined x-coordinate of the map
	 * @param y
	 *            the defined y-coordinate of the map
	 */
	void setXY(int x, int y) {
		pointX.setText(new Integer(x).toString());
		pointY.setText(new Integer(y).toString());
	}
}