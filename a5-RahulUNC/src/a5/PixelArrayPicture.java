package a5;

public abstract class PixelArrayPicture extends PictureImpl{
	
	protected Pixel[][] pixelContainer;
	
	public PixelArrayPicture(Pixel[][] sourcePixels, String caption)
	{
		super(caption);
		pixelContainer = sourcePixels;
	}
	
	/*
	 * Only three additional methods are defined in PixelArrayPicture
	 * both Immutable and Mutable encapsulate a 2D array
	 * thus getWidth(), getHeight(), and getPixel(int x, int y) can be implemented
	 */	
	public int getWidth()
	{
		return pixelContainer.length;
	}
	
	public int getHeight()
	{
		return pixelContainer[0].length;
	}
	
	public Pixel getPixel(int x, int y)
	{
		return pixelContainer[x][y];
	}
	
	/*
	 * Single Pixel Paint methods remains abstract
	 * Since painting will differ in mutable and immutable
	 */
	public abstract Picture paint(int x, int y, Pixel p, double factor);
	
}
