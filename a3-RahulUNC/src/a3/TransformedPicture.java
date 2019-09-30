package a3;
//
public class TransformedPicture implements Picture {

	Picture _source;
	PixelTransformation _xform;
	
	public TransformedPicture (Picture source, PixelTransformation xform)
	{
		if(source == null || xform == null)
		{
			throw new IllegalArgumentException("Please do not enter in null values!");
		}
		_source = source;
		_xform = xform;
	}
	
	@Override
	public int getWidth() {
		return _source.getWidth();
	}

	@Override
	public int getHeight() {
		return _source.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {	
		
		if(x < 0 || y < 0)
		{
			throw new IllegalArgumentException("Please enter in possitive X and Y values!");
		}
		if(x >= _source.getWidth() ||  y >= _source.getHeight())
		{
			throw new IllegalArgumentException("Provided indexes are out of range!");
		}
		Pixel old = _source.getPixel(x, y);
		Pixel modified = _xform.transform(old);
		return modified;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x >= _source.getWidth() || y >= _source.getHeight())
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		Pixel[][] adjustedArray = populate().clone();
		adjustedArray[x][y] = p;
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return null;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x >= _source.getWidth() || y >= _source.getHeight())
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		Pixel[][] adjustedArray = populate().clone();
		adjustedArray[x][y] = p.blend(p, factor);
		Picture painted = new MutablePixelArrayPicture(adjustedArray);
		return null;
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
		Pixel[][] adjustedArray = populate().clone();
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		if(ax < bx && ay < by)
		{
			for(int i = ax; i < bx; i++)
			{
				for(int j = ay; j < by; j++)
				{
					adjustedArray[i][j] = p;
				}
			}
		}
		else
		{
			for(int i = bx; i < ax; i++)
			{
				for(int j = by; j < ay; j++)
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
			for(int i = ax; i < bx; i++)
			{
				for(int j = ay; j < by; j++)
				{
					adjustedArray[i][j] = p.blend(p, factor);
				}
			}
		}
		else
		{
			for(int i = bx; i < ax; i++)
			{
				for(int j = by; j < ay; j++)
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
		Pixel[][] populated = new ColorPixel[_source.getWidth()][_source.getHeight()];
		for(int i = 0; i < _source.getWidth(); i++)
		{
			for(int j = 0; j < _source.getHeight(); j++)
			{
				populated[i][j] = getPixel(i, j);
			}
		}
		return populated;
	}

}
