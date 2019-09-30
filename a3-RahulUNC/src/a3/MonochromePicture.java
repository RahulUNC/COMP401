package a3;
//Rahul Narvekar
//COMP 401
public class MonochromePicture implements Picture {

	
	private int _width;
	private int _height;
	private Pixel _value;
	
	public MonochromePicture(int width, int height, Pixel value)
	{
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException("Please give valid width's and height's!");
		if(value == null)
		{
			throw new IllegalArgumentException("Please do not enter a null pixel!");
		}
		_width = width;
		_height = height;
		_value = value;
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
		if(x >= _width || y >= _height)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
		{
			throw new IllegalArgumentException("Out of range!");
		}
		return _value;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if(x < 0 || y < 0)
		{
			throw new IllegalArgumentException("Out of range!");
		}
		if(p == null)
		{
			throw new IllegalArgumentException("Out of range!");
		}
		Pixel[][] adjustedArray = populate().clone();;
		adjustedArray[x][y] = p;
		Picture adjustedPicture = new MutablePixelArrayPicture(adjustedArray);
		return adjustedPicture;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		Pixel[][] adjustedArray = populate().clone();;
		adjustedArray[x][y] = p.blend(p, factor);
		Picture adjustedPicture = new MutablePixelArrayPicture(adjustedArray);
		return adjustedPicture;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if(ax < 0 || ay < 0 || bx < 0 || by < 0)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
		}
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
		
		Pixel[][] adjustedArray = populate().clone();;
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		if(ax < bx && ay < by)
		{
			for(int i = ax; i <= bx; i++)
			{
				for(int j = ay; j <= by; j++)
				{
					adjustedArray[i][j] = p;
				}
			}
		}
		else
		{
			for(int i = bx; i <= ax; i++)
			{
				for(int j = by; j <= ay; j++)
				{
					adjustedArray[i][j] = p;
				}
			}
		}
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return painted;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		
		if(ax < 0 || ay < 0 || bx < 0 || by < 0)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
		}
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
		Pixel[][] adjustedArray = populate().clone();
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		if(ax < bx && ay < by)
		{
			for(int i = ax; i <= bx; i++)
			{
				for(int j = ay; j <= by; j++)
				{
					adjustedArray[i][j] = p.blend(p, factor);
				}
			}
		}
		else
		{
			for(int i = bx; i <= ax; i++)
			{
				for(int j = by; j <= ay; j++)
				{
					adjustedArray[i][j] = p.blend(p, factor);
				}
			}
		}
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return painted;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {		
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		Pixel[][] adjustedArray = populate().clone();;
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
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
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return painted;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		Pixel[][] adjustedArray = populate().clone();;
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
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
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return painted;
	}
	
	public Pixel[][] populate()
	{
		Pixel[][] returnArray = new Pixel[_width][_height];
		for(int i = 0; i < _width; i++)
		{
			for(int j = 0; j < _height; j++)
			{
				returnArray[i][j] = _value;
			}
		}
		return returnArray;
	}

}
