package a5;

public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture{

	public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(copyPixelArray(parray), caption);
	}
	
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}
		
		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}

		pixelContainer[x][y] = pixelContainer[x][y].blend(p,  factor);
		
		return this;
	}

	
	private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {
		
		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;
		
		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");			
		}
		
		for (int x=0; x<width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");			
			}
		}
		
		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");						
		}
		
		for (int x=0; x<width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");			
			}
		}

		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");								
				}
			}
		}
		
		Pixel[][] copy = new Pixel[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
	}

}
