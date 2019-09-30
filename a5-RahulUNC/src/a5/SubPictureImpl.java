package a5;

public class SubPictureImpl extends PictureImpl implements SubPicture {

	private Picture source;
	private int xOffSet;
	private int yOffSet;
	private int width;
	private int height;
	
	
	/* Constructor for SubPictureImpl
	 * Input: source picture, the amount x and y are offset by, and the width and height of the SubPicture
	 * Output: initializes fields in PixelArrayPicture
	 */
	public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {
		
		
		super(source.getCaption());
		if(width <= 0 || height <= 0 || xoffset >= source.getWidth() || yoffset >= source.getHeight() ||
		  (xoffset + width) > source.getWidth() || (yoffset + height) > source.getHeight() ||
		  xoffset < 0 || yoffset < 0)
			throw new IllegalArgumentException("Out of bounds!");
		this.source = source;
		xOffSet = xoffset;
		yOffSet = yoffset;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return source.getPixel(x + xOffSet, y + yOffSet);
	}

	
	/* 
	 * Single Pixel Paint function that returns a picture in context to mutability
	 * Input: x and y coordinate of pixel to change, and the factor by which the image must be blended
	 * Output: a painted picture with respect to mutability
	 */
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		Picture painted = source.paint(x + xOffSet, y + yOffSet, p, factor);
		//checks for reference, if reference is the same then this is returned because the object is mutable
		if(painted == source)
		{
			source = painted;
			return this;
		}
		//creates a new SubPicture that maintains immutability
		SubPicture immutableVersion = painted.extract(xOffSet, yOffSet, width, height); 
		immutableVersion.setCaption(getCaption()); //initializes the caption
		return immutableVersion;
	}

	@Override
	public int getXOffset() {
		return xOffSet;
	}

	@Override
	public int getYOffset() {
		return yOffSet;
	}

	@Override
	public Picture getSource() {
		return source;
	}
}