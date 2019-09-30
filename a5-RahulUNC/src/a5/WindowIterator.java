package a5;

import java.util.Iterator;

public class WindowIterator implements Iterator<SubPicture> {

	Picture source;
	int width;
	int _height;
	int xProg;
	int yProg;	
	
	/*
	 * WindowIterator Constructor
	 * Encapsulates the source picture, width, height
	 * Initializes xProg and yProg, which keep track of (x,y) positions
	 */
	public WindowIterator(Picture source, int window_width, int window_height) {
		
		if(source == null)
		{
			throw new IllegalArgumentException("Null picture!");
		}
		if(window_width < 1 || window_width > source.getWidth() || window_height < 1 || window_height > source.getHeight())
		{
			throw new IllegalArgumentException("Out of bounds!");
		}		
		this.source = source;
		width = window_width;
		_height = window_height;
		xProg = 0;
		yProg = 0;
	}
	
	/*
	 * Determines if there is another SubPicture in the sequence
	 * Checks to see if yProg is equal to its maximum possible height
	 */
	public boolean hasNext() {
		return (yProg <= source.getHeight() - _height);
	}

	//Find the next SubPicture in the sequence
	public SubPicture next() {
		
		if (hasNext()) { //checks to see if another picture exists or throws exception
			//extracts a SubPicture at the current position of xProg, yProg
			SubPicture newSub = source.extract(xProg, yProg, width, _height);
			xProg++;
			//if xProg reaches its maximum width it is reset and y is iterated by 1
			if(xProg > source.getWidth() - width )
			{
				xProg = 0;
				yProg++;
			}
			return newSub;
		} else
			throw new RuntimeException("There are no new windows!");
	}

}
