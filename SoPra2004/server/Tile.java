package server;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Class Tile to build up Tiles for the Map
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class Tile {
	private Dimension dim;

	private Image image = null;

	private float xFrom;

	private float xTo;

	private float yFrom;

	private float yTo;

	public Tile() {
	}

	/**
	 * Class constructor for building a Tile
	 * 
	 * @param id
	 *            id of the Tile
	 * @param xFrom
	 *            x-value where the Tile starts
	 * @param xTo
	 *            x-value where the Tile ends
	 * @param yFrom
	 *            y-value where the Tile starts
	 * @param yTo
	 *            y-value where the Tile ends
	 * @param gif
	 *            byte[] of the gif
	 */
	public Tile(int id, int xFrom, int xTo, int yFrom, int yTo, byte[] gif) {
		dim = new Dimension(xTo - xFrom, yTo - yFrom);
		this.xFrom = xFrom;
		this.xTo = xTo;
		this.yFrom = yFrom;
		this.yTo = yTo;

		try {
			createImage(gif);
		} catch (NoImageException e) {
			System.err.println("createImage der Tile: " + e.getMessage());
		}
	}

	/**
	 * Creates a image
	 * 
	 * @param gif
	 *            the gif from which the image should be painted
	 * @throws NoImageException
	 */
	private void createImage(byte[] gif) throws NoImageException {
		if (gif != null) {
			image = new ImageIcon(gif).getImage();
		} else {
			throw new NoImageException("Stream aus der DB ist null");
		}
	}

	/**
	 * Boolean Test if there is a image
	 * 
	 * @return true if there is a image, else false
	 */
	public boolean hasImage() {
		if (image != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the current image
	 * 
	 * @return the current immage
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the siye of the Image
	 * 
	 * @return the Siye as a Dimension of the current image
	 */
	public Dimension getSize() {
		return dim;
	}

	/**
	 * @return Returns the xFrom.
	 */
	public float getXFrom() {
		return xFrom;
	}

	/**
	 * @return Returns the xTo.
	 */
	public float getXTo() {
		return xTo;
	}

	/**
	 * @return Returns the yFrom.
	 */
	public float getYFrom() {
		return yFrom;
	}

	/**
	 * @return Returns the yTo.
	 */
	public float getYTo() {
		return yTo;
	}

	/**
	 * @param image
	 *            The image to set.
	 */
	public void setImage(Image image) {
		ImageIcon temp = new ImageIcon(image);
		this.image = temp.getImage();
	}

	public void setSize(Dimension d) {
		dim = d;
	}

	/**
	 * @param from
	 *            The xFrom to set.
	 */
	public void setXFrom(float from) {
		xFrom = from;
		setXTo(xFrom + getSize().width);
	}

	/**
	 * @param from
	 *            The yFrom to set.
	 */
	public void setYFrom(float from) {
		yFrom = from;
		setYTo(yFrom + getSize().height);
	}

	private void setXTo(float to) {
		yTo = to;
	}

	/**
	 * @param to
	 *            The yTo to set.
	 */
	private void setYTo(float to) {
		yTo = to;
	}
}