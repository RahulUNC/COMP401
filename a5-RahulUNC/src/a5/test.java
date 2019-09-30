package a5;

import java.util.Iterator;

public class test {

	public static void main(String[] args)
	{
		Pixel[][] array = {{new GrayPixel(0.2),new GrayPixel(0.3), new GrayPixel(0.5)},
						   {new GrayPixel(0.4),new GrayPixel(0.5), new GrayPixel(0.6)},
						   {new GrayPixel(0.9),new GrayPixel(0.2), new GrayPixel(0.1)}};
		Picture p = new MutablePixelArrayPicture(array,"caption");
		Iterator<Pixel> zigZag = p.zigzag();
		System.out.println(p.getPixel(0, 1).getIntensity());
		while(zigZag.hasNext()) {			
			System.out.println(zigZag.next().getIntensity());
		}
	}
	
}
