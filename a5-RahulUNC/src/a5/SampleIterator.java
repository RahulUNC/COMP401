package a5;

import java.util.Iterator;

public class SampleIterator implements Iterator<Pixel> {

	private int xProg;
	private int yProg;
	private Picture source;
	private int initX;
	private int initY;
	private int dx;
	private int dy;

	/*
	 * SampleIterator Constructor Encapsulates the source picture, tileWidth,
	 * tileHeight, init (x,y), and change in x & y Initializes xProg and yProg,
	 * which keep track of (x,y) positions
	 */
	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy) {
		if(source == null)
		{
			throw new IllegalArgumentException("null picture");
		}
		if(init_x >= source.getWidth() || init_y >= source.getHeight() || dx <= 0 || dy <= 0)
		{
			throw new IllegalArgumentException("out of bounds!");
		}
		
		this.source = source;
		this.initX = init_x;
		this.initY = init_y;
		this.dx = dx;
		this.dy = dy;
		xProg = init_x;
		yProg = init_y;
	}

	/*
	 * Determines if there is another SubPicture in the sequence Checks to see if
	 * yProg is equal to its maximum possible height
	 */
	public boolean hasNext() {
		if (yProg <= source.getHeight() - 1)
			return true;
		else
			return false;
	}

	// Find the next SubPicture in the sequence
	public Pixel next() {

		if (hasNext()) { // checks to see if another picture exists or throws exception
			// extracts a SubPicture at the current position of xProg, yProg
			Pixel returnPixel = source.getPixel(xProg, yProg);
			xProg += dx;
			//if xProg reaches its maximum width it is reset and y is iterated by the change in dy
			if (xProg > source.getWidth() - 1) {
				yProg += dy;
				xProg = initX;
			}
			return returnPixel;
		} else
			throw new RuntimeException("There is not another value to find");
	}

}
