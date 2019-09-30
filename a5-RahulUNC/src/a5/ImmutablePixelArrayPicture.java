package a5;

public class ImmutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	/*
	 * Constructor that initializes fields in super Input: 2D array, and a string
	 * caption Output: initializes fields in PixelArrayPicture
	 * copyPixelArray, provided by KMP, validates the passed array
	 */
	public ImmutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(copyPixelArray(parray), caption);
	}

	/*
	 * Singular Pixel Paint method
	 * KMP's original implementation of this method has been preserved
	 * as its implementation is unique to Immutable
	 */
	public Picture paint(int x, int y, Pixel p, double factor) {
		if ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight())) {
			throw new IllegalArgumentException("x or y out of bounds");
		}

		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if ((factor < 0.0) || (factor > 1.0)) {
			throw new IllegalArgumentException("factor is out of bounds");
		}
		//returns a new MutablePixelArrayPicture that contains the new painted pixel
		return (new MutablePixelArrayPicture(pixelContainer, getCaption())).paint(x, y, p,factor);
	}

	private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {

		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;

		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");
			}
		}

		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");
				}
			}
		}

		Pixel[][] copy = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
	}

	private static Picture copyAsImmutable(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				parray[x][y] = p.getPixel(x, y);
			}
		}
		return new ImmutablePixelArrayPicture(parray, p.getCaption());
	}

	/*
	 * Each of the next three methods call to super once they return the picture I
	 * used KMP's provided helper method to Copy the picture as immutable as the
	 * super class returns a mutable picture
	 */
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		return copyAsImmutable(super.paint(ax, ay, bx, by, p, factor));
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		return copyAsImmutable(super.paint(cx, cy, radius, p, factor));
	}

	public Picture paint(int x, int y, Picture p, double factor) {
		return copyAsImmutable(super.paint(x, y, p, factor));
	}
}
