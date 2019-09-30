package a5;

import java.util.Iterator;

public class TileIterator implements Iterator<SubPicture> {

	private Picture source;
	private int tileWidth;
	private int tileHeight;
	private int xProg;
	private int yProg;
	
	/*
	 * Tile Iterator Constructor
	 * Encapsulates the source picture, tileWidth, tileHeight
	 * Initializes xProg and yProg, which keep track of (x,y) positions
	 */
	public TileIterator(Picture source, int tile_width, int tile_height) {
		if(source == null)
		{
			throw new IllegalArgumentException("Source picture is null");
		}
		if(tile_width < 1 || tile_width > source.getWidth() || tile_height < 1 || tile_height > source.getHeight())
		{
			throw new IllegalArgumentException("Out of bounds!");
		}
		this.source = source;
		tileWidth = tile_width;
		tileHeight = tile_height;
		xProg = 0;
		yProg = 0;
	}
	
	/*
	 * Determines if there is another SubPicture in the sequence
	 * Checks to see if yProg is equal to its maximum possible height
	 */
	public boolean hasNext() {
		return (yProg <= source.getHeight() - tileHeight);
	}

	//Find the next SubPicture in the sequence
	public SubPicture next() {
		if(hasNext()) //checks to see if another picture exists or throws exception
		{
			//extracts a SubPicture at the current position of xProg, yProg
			SubPicture subPic = source.extract(xProg, yProg, tileWidth, tileHeight);
			xProg += tileWidth;
			//if xProg reaches its maximum width it is reset and y is iterated by tileHeight
			if(xProg > source.getWidth() - tileWidth) {
				xProg = 0;
				yProg += tileHeight;
			}
			return subPic;
		} else {
			throw new RuntimeException("There are no more tiles left!");
		}
	}

}
