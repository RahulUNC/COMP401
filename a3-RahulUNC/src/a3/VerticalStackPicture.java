package a3;
//Rahul Narvekar
//COMP 401
public class VerticalStackPicture implements Picture {

	private int widthTop;
	private int heightTop;
	private int widthBottom;
	private int heightBottom;
	private Pixel[][] populated;
 	
	public VerticalStackPicture (Picture top, Picture bottom)
	{
		if(top == null || bottom == null)
			throw new IllegalArgumentException("Please enter in pictures that do not contain null values!");
		if(top.getWidth() != bottom.getWidth())
			throw new IllegalArgumentException("Please make sure that your images have equal widths!");
		this.widthTop = top.getWidth();
		this.heightTop = top.getHeight();
		this.widthBottom = bottom.getWidth();
		this.heightBottom = bottom.getHeight();
		populated = new ColorPixel[this.widthTop][this.heightTop + this.heightBottom];	
		for(int h = 0; h < this.heightTop; h++)
		{
			for(int j = 0; j < this.widthBottom; j++)
			{
				{
					populated[j][h] = top.getPixel(j, h);
				}				
			}
		}
		for(int i = 0; i < this.heightBottom; i++)
		{
			for(int k = 0; k < this.widthBottom; k++)
			{
					populated[k][i + this.heightTop] = bottom.getPixel(k, i);
			}
		}

	}
	
	@Override
	public int getWidth() {
		return this.widthBottom;
	}

	@Override
	public int getHeight() {
		return this.heightBottom + this.heightTop;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return populated[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (p == null)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		//Pixel[][] populated = populate(_top, _bottom);
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");		
		populated[x][y] = p;
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (p == null|| factor > 1|| factor < 1)
		{
			throw new IllegalArgumentException("Null value for pixel!");
		}
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		if(x >= populated.length || y >= populated[0].length)
			throw new IllegalArgumentException("Please do not enter illgal value for X and Y");
		populated[x][y] = p.blend(p, factor);
		return this;
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
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if(ax < 0 || ay < 0 || bx < 0 || by < 0|| factor > 1|| factor < 1)
		{
			throw new IllegalArgumentException("Please enter in non-negative values!");
		}
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
					populated[i][j] = p.blend(p, factor);;
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
		if(p == null|| factor > 1 || factor < 1 || factor < 1)
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
