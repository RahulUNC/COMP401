package a3;

public class HorizontalStackPicture implements Picture {
//Rahul Narvekar
	//COMP 401
	Picture _left;
	Picture _right;
	int _widthLeft;
	int _heightLeft;
	int _widthRight;
	int _heightRight;
	Pixel[][] populated;
	public HorizontalStackPicture (Picture left, Picture right)
	{
		if(left == null || right == null)
			throw new IllegalArgumentException("Please enter in pictures that do not contain null values!");
		if(left.getHeight() != right.getHeight())
			throw new IllegalArgumentException("Please make sure that your images have equal heights!");
		this._widthLeft = left.getWidth();
		this._heightLeft = left.getHeight();
		this._widthRight = right.getWidth();
		this._heightRight = right.getHeight();
		populated = new ColorPixel[this._widthLeft + this._widthRight][this._heightRight];		
		
		for(int i = 0; i < left.getWidth() + right.getWidth(); i++)
		{
			for(int j = 0; j < left.getHeight(); j++)
			{
				if(i < left.getWidth())
				{
					populated[i][j] = left.getPixel(i, j);
				}
				else
					populated[i][j] = right.getPixel(i - this._widthLeft, j);
			}
		}
		
	}
	@Override
	public int getWidth() {
		return populated.length;
	}

	@Override
	public int getHeight() {
		return populated[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		return populated[x][y];		
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {		
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		populated[x][y] = p;
		return this;			
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");

		if(x >= populated.length|| y >= populated[0].length || factor > 1|| factor < 1)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		populated[x][y] = p.blend(p, factor);
		return this;
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
		if(ax < 0 || ay < 0 || bx < 0 || by < 0 || factor > 1|| factor < 1)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
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
		for(int x = 0; x < populated.length; x++)
		{
			for (int y = 0; y < populated[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					populated[x][y] = p;
				}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(p == null || factor > 1|| factor < 1)
		{
			throw new IllegalArgumentException("Please enter in a pixel that is not null!");
		}	
		if(radius < 0)
			throw new IllegalArgumentException("Please enter in a possitive value for radius!");
		for(int x = 0; x < populated.length; x++)
		{
			for (int y = 0; y < populated[0].length; y++)
			{
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius)
				{
					populated[x][y] = p.blend(p, factor);
				}
			}
		}
		return this;
	}
}
