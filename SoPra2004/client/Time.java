package client;

import java.awt.Font;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalBorders;

/**
 * Simple Time Class
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class Time extends JLabel {

	private GregorianCalendar now;

	private DateFormat formater;

	/**
	 * Class constructor Sets the time format (incl. font and format of the
	 * clock)
	 */
	Time() {
		formater = DateFormat.getDateTimeInstance(DateFormat.FULL,
				DateFormat.MEDIUM);
		setBorder(new MetalBorders.Flush3DBorder());
		setFont(new Font("Verdana", Font.PLAIN, 11));
		refresh();
		Wait w = new Wait();
		w.start();
	}

	/**
	 * refreshs the current time
	 */
	public void refresh() {
		now = new GregorianCalendar();
		now.setTime(now.getTime());
		String time = formater.format(now.getTime());
		setText("     " + time + " ");
		repaint();
	}

	class Wait extends Thread {
		public Wait() {
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				refresh();
			}
		}
	}
}