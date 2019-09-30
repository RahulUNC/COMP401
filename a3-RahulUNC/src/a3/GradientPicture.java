package a3;

public class GradientPicture implements Picture {

	private int _width;
	private int _height;
	private Pixel _upperLeft;
	private Pixel _upperRight;
	private Pixel _lowerLeft;
	private Pixel _lowerRight;
	
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right)
	{
		if(width <= 0 || height <= 0) 
			throw new IllegalArgumentException("Please do not provide invalid widths and heights!");
		if(upper_left == null || upper_right == null || lower_left ==  null || lower_right == null)
		{
			throw new IllegalArgumentException("Please do not enter in null pixels!");
		}
		_upperLeft = upper_left;
		_upperRight = upper_right;
		_lowerLeft = lower_left;
		_lowerRight =lower_right;
		_width = width;
		_height = height;
	}
	
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
	
		return _width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return _height;
	}
	
	
	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		if( x >= _width || y>= _height || x < 0|| y < 0) 
			throw new IllegalArgumentException("Please enter in legal values for x and y!");
		
		Pixel startOfRow = _upperLeft.blend(_lowerLeft, (double)(y)/(_height-1));
		Pixel endOfRow = _upperRight.blend(_lowerRight, (double)(y)/(_height-1));
		Pixel returnPixel = startOfRow.blend(endOfRow, (double)(x)/(_width-1));
		
		return returnPixel;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		Pixel[][] populated = populate();
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");		
		populated[x][y] = p;
		Picture painted = new MutablePixelArrayPicture(populated);
		return painted;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		Pixel[][] populated = populate();
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");		
		populated[x][y] = p.blend(p, factor);
		Picture painted = new MutablePixelArrayPicture(populated);
		return painted;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
		if(ax < 0 || ay < 0 || bx < 0 || by < 0)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
		}
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		Pixel[][] populated = populate();
		if(ax < bx && ay < by)
		{
			for(int i = ax; i <= bx; i++)
			{
				for(int j = ay; j <= by; j++)
				{
					populated[i][j] = p;
				}
			}
		}
		else
		{
			for(int i = bx; i <= ax; i++)
			{
				for(int j = by; j <= ay; j++)
				{
					populated[i][j] = p;
				}
			}
		}
		Picture painted = new MutablePixelArrayPicture(populated);
		return painted;
		
		
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
		if(ax < 0 || ay < 0 || bx < 0 || by < 0)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
		}
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		Pixel[][] populated = populate();
		if(ax < bx && ay < by)
		{
			for(int i = ax; i <= bx; i++)
			{
				for(int j = ay; j <= by; j++)
				{
					populated[i][j] = p.blend(p, factor);
				}
			}
		}
		else
		{
			for(int i = bx; i <= ax; i++)
			{
				for(int j = by; j <= ay; j++)
				{
					populated[i][j] = p.blend(p, factor);
				}
			}
		}
		Picture painted = new MutablePixelArrayPicture(populated);
		return painted;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
		Pixel[][] adjustedArray = populate();
		for(int x = 0; x < adjustedArray.length; x++)
		{
			for (int y = 0; y < adjustedArray[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					adjustedArray[x][y] = p;
				}
			}
		}
		Picture adjustedCricle = new ImmutablePixelArrayPicture(adjustedArray);;
		return adjustedCricle;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
		Pixel[][] adjustedArray = populate();
		for(int x = 0; x < adjustedArray.length; x++)
		{
			for (int y = 0; y < adjustedArray[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					adjustedArray[x][y] = p.blend(p, factor);
				}
			}
		}
		Picture adjustedCricle = new ImmutablePixelArrayPicture(adjustedArray);;
		return adjustedCricle;
	}
	
	private Pixel[][] populate()
	{
		Pixel[][] populated = new Pixel[_width][_height];
		for(int i = 0; i < populated.length ; i++)
		{
			for(int j = 0; j < populated[0].length; j++)
			{
				Pixel startOfRow = _upperLeft.blend(_lowerLeft, (double)(j)/(_height-1));
				Pixel endOfRow = _upperRight.blend(_lowerRight, (double)(j)/(_height-1));
				Pixel targetPixel = startOfRow.blend(endOfRow, (double)(i)/(_width-1));
				populated[i][j] = targetPixel;
			}
		}
		return populated;
	}

}
