package a5;

import java.util.Iterator;

public class ZigZagIterator implements Iterator<Pixel> {

	private Picture source;
	private int xProg;
	private int yProg;
	
	/*
	 * ZigZagIterator Constructor
	 * Encapsulates the source picture
	 * Initializes xProg and yProg, which keep track of (x,y) positions
	 */
	public ZigZagIterator(Picture source) {
		if(source == null)
		{
			throw new IllegalArgumentException("Source picture is null");
		}
		this.source = source;
		xProg = 0;
		yProg = 0;
	}
	
	/*
	 * Determines if there is another pixel in the sequence
	 * Checks to see if xProg and yProg have the position of the last Pixel
	 */
	public boolean hasNext() {
		return (yProg < source.getHeight() && xProg < source.getWidth());
	}

	//Finds the next Pixel in the ZigZag
	@Override
	public Pixel next() {
		//Checks to see if there is another pixel in the sequence
		if(hasNext()) {
			//Sets the return pixel to the current xProg and YProg Values
			Pixel zigZag = source.getPixel(xProg, yProg);
			//Finds if the current diagonal is even or odd by adding (x,y)
			boolean even = true;
			if(!((xProg + yProg) % 2 ==0))
				even = false;
			if(even) {	//Even Cases
				//Since corners are both the special cases, you must go down first
				//or you end up outside the grid
				if (xProg == source.getWidth() -1)
					yProg++;
				//if yProg is 0, x is iterated
				else if(yProg == 0)
					xProg++;				
				//normal even behavior
				else {
					xProg++;
					yProg--;
				}
			}
			else {	//Odd Cases
				if (yProg == source.getHeight() - 1) //Checks corner case
					xProg++;
				else if (xProg == 0) //if xProg is 0, y is iterated
					yProg++;				
				else //normal odd behavior
				{
					yProg++;
					xProg--;
				}
			}				
			return zigZag;
		} else {
			throw new RuntimeException("There are no more pixels left");
		}
	}
}
