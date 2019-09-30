package a3;

public class ImmutablePixelArrayPicture implements Picture {
//Rahul Narvekar
	//COMP 401
	Pixel[][] _pixel_Array;
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array)
	{
		if( pixel_array == null || pixel_array.length <= 0 || pixel_array[0].length <= 0 )
		{
			throw new IllegalArgumentException("Please do not input an undefined array!");
		}

		int column = pixel_array[0].length;
		for(int i = 0; i <  pixel_array.length; i++)
		{
			if(pixel_array[i] == null || column != pixel_array[i].length)
				throw new IllegalArgumentException("ErroR");
		}
		for(Pixel[] r : pixel_array)
		{
			for(Pixel c: r)
			{
				if(c==null)
					throw new IllegalArgumentException("ErroR");
			}
		}
		_pixel_Array = pixel_array;
	}
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value)
	{
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException("Please give valid width's and height's!");
		if(initial_value == null)
		{
			throw new IllegalArgumentException("Please do not enter a null pixel!");
		}
		Pixel[][] providedVal = new ColorPixel[width][height];
		for(int r = 0; r < providedVal.length; r++)
		{
			for(int c = 0; c < providedVal[0].length; c++)
			{
				providedVal[r][c] = initial_value;
			}
		}
		_pixel_Array = providedVal;
	}
	public ImmutablePixelArrayPicture(int width, int height)
	{
		if(width < 0 || height < 0)
			throw new IllegalArgumentException("Please give valid width's and height's!");
		Pixel[][] providedVal = new ColorPixel[width][height];
		for(int r = 0; r < providedVal.length; r++)
		{
			for(int c = 0; c < providedVal[r].length; c++)
			{
				providedVal[r][c] = new GrayPixel(0.5);
			}
		}
		_pixel_Array = providedVal;		
	}
	@Override
	public int getWidth() {
		return _pixel_Array.length;
	}

	@Override
	public int getHeight() {
		return _pixel_Array[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x >= _pixel_Array.length || y >= _pixel_Array[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		return _pixel_Array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		Pixel[][] providedVal = new Pixel[this.getWidth()][this.getHeight()];
		for(int r = 0; r < providedVal.length; r++)
		{
			for(int c = 0; c < providedVal[0].length; c++)
			{
				providedVal[r][c] = _pixel_Array[r][c];
			}
		}
		if(x >= _pixel_Array.length || y >= _pixel_Array[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		providedVal[x][y] = p;
		Picture newImage = new MutablePixelArrayPicture(providedVal);
		return newImage;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x >= _pixel_Array.length || y >= _pixel_Array[0].length || factor < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		Pixel[][] adjustedArray = _pixel_Array.clone();
		adjustedArray[x][y] = p.blend(p, factor);
		Picture singularPixelChange = new ImmutablePixelArrayPicture(adjustedArray);
		return singularPixelChange;
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
		Pixel[][] adjustedArray = _pixel_Array.clone();
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
		Pixel[][] adjustedArray = _pixel_Array.clone();
		if(ax == bx || ay == by)
		{
			throw new IllegalArgumentException("Please enter in a valid range!");
		}
		if(ax < bx && ay <= by)
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
			for(int i = bx; i < ax; i++)
			{
				for(int j = by; j < ay; j++)
				{
					adjustedArray[i][j] = p.blend(p, factor);
				}
			}
		}
		Picture painted = new ImmutablePixelArrayPicture(adjustedArray);
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
		Pixel[][] adjustedArray = _pixel_Array.clone();
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
		Pixel[][] adjustedArray = _pixel_Array.clone();
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

}
