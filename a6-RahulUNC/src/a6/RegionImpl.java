package a6;

public class RegionImpl implements Region {

	private int left;
	private int top;
	private int right;
	private int bottom;
	
	public RegionImpl(int left, int top, int right, int bottom)
	{
		if(left < 0 || top < 0 || right < 0 || bottom < 0)
		{
			throw new IllegalArgumentException("Out of bounds!");
		}
		if(left > right || top > bottom)
		{
			throw new IllegalArgumentException("Illegal rectangle geometery!");
		}
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	@Override
	public int getTop() {
		return top;
	}

	@Override
	public int getBottom() {
		return bottom;
	}

	@Override
	public int getLeft() {
		return left;
	}

	@Override
	public int getRight() {
		return right;
	}

	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		if(other == null)
			throw new NoIntersectionException();		
		int newTop, newBottom, newLeft, newRight;		
		newTop = (top > other.getTop())? top : other.getTop();
		newBottom = (bottom < other.getBottom())? bottom : other.getBottom();
		newLeft = (left > other.getLeft())? left : other.getLeft();
		newRight = (right < other.getRight())? right : other.getRight();		
		try {
			return new RegionImpl(newLeft, newTop, newRight, newBottom);
		} catch (IllegalArgumentException e) {
			throw new NoIntersectionException();
		}		
	}

	@Override
	public Region union(Region other) {		
		if(other == null)
			return this;			
		int newTop, newBottom, newLeft, newRight;
		newTop = (top < other.getTop()) ? top : other.getTop();
		newBottom = (bottom > other.getBottom()) ? bottom : other.getBottom();
		newRight = (right > other.getRight()) ? right : other.getRight();
		newLeft = (left < other.getLeft()) ? left : other.getLeft();
		return new RegionImpl(newLeft, newTop, newRight, newBottom);
	}

}
