package a3;
//
public class Threshold implements PixelTransformation {

	private  double _threshold;
	
	public Threshold(double threshold)
	{
		if(threshold < 0)
		{
			throw new IllegalArgumentException("Please enter in a possitive value for threshold!");
		}
		_threshold = threshold;
	}
	
	@Override
	public Pixel transform(Pixel p) {
		if(p.getIntensity() > _threshold)
		{
			return Pixel.WHITE;
		}
		else
		{
			Pixel blackPixel = new GrayPixel(0.0);
			return Pixel.BLACK;
		}
	}

}
