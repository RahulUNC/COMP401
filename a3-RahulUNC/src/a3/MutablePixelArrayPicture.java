package a3;
//Rahul Narvekar
//COMP 401
public class MutablePixelArrayPicture implements Picture {

	private Pixel[][] _holder;
	
	public MutablePixelArrayPicture(Pixel[][] pixel_array)
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
		
		_holder = pixel_array;
	}
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value)
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
		_holder = providedVal;
	}
	public MutablePixelArrayPicture(int width, int height)
	{
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException("Please give valid width's and height's!");
		Pixel[][] grayScale = new GrayPixel[width][height];
		for(int r = 0; r < grayScale.length; r++)
		{
			for(int c = 0; c < grayScale[r].length; c++)
			{
				grayScale[r][c] = new GrayPixel(0.5);
			}
		}	
		_holder = grayScale;
	}
	
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return _holder.length;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return _holder[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x >= _holder.length || y >= _holder[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		return _holder[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if(x >= _holder.length || y >= _holder[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		_holder[x][y] = p;
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x >= _holder.length || y >= _holder[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x < 0 || y < 0 || factor < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
		_holder[x][y] = p.blend(p, factor);
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if(ax < 0)
			ax = 0;
		if(ay  < 0)
			ay = 0;
		if(bx < 0)
			bx = 0;
		if(by < 0)
			by = 0;
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
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
					_holder[i][j] = p;
				}
			}
		}
		else
		{
			for(int i = bx; i < ax; i++)
			{
				for(int j = by; j < ay; j++)
				{
					_holder[i][j] = p;
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if(ax < 0)
			ax = 0;
		if(ay  < 0)
			ay = 0;
		if(bx < 0)
			bx = 0;
		if(by < 0)
			by = 0;
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in values that are not null!");
		}
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
					_holder[i][j] = p.blend(p, factor);
				}
			}
		}
		else
		{
			for(int i = bx; i <= ax; i++)
			{
				for(int j = by; j <= ay; j++)
				{
					_holder[i][j] = p.blend(p, factor);
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
		for(int x = 0; x < _holder.length; x++)
		{
			for (int y = 0; y < _holder[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					_holder[x][y] = p;
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(p == null)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}			
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
		for(int x = 0; x < _holder.length; x++)
		{
			for (int y = 0; y < _holder[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					_holder[x][y] = p.blend(p, factor);
				}
			}
		}
		return this;
	}

}
