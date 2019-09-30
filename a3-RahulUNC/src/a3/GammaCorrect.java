package a3;
//
public class GammaCorrect implements PixelTransformation {

	private double _gamma;
	
	public GammaCorrect (double gamma)
	{
		if(gamma < 0)
		{
			throw new IllegalArgumentException("Please enter in a possitive value for gamma!");
		}
		_gamma = gamma;
	}
	
	@Override
	public Pixel transform(Pixel p) {
		Pixel returnPixel = new ColorPixel( Math.pow(p.getRed(), 1.0/_gamma), Math.pow(p.getGreen(), 1.0/_gamma), Math.pow(p.getBlue(), 1.0/_gamma));
		return returnPixel;
	}

}
