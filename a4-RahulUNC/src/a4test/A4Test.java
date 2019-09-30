package a4test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a4.*;

public class A4Test {
		
	static public String[] getTestNames() {
		String[] test_names = new String[10];
		test_names[0] = "testGetCaption";
		test_names[1] = "testNewPaintMethod";
		test_names[2] = "testSampleIterator";
		test_names[3] = "testGetWidth";
		test_names[4] = "testGetPixel";
		test_names[5] = "testGetHight";
		test_names[6] = "testNullArrayMutable";
		test_names[7] = "testNullArrayElementsMutable";
		test_names[8] = "testNullArrayElementsImmutable";
		test_names[9] = "testNullArrayImmutable";
		
		return test_names;
	}
	Pixel[][] PixelArray2D = { {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)}, 
							   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)},
							   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)} };
	
	
	
	@Test
	public void testGetCaption() {
		Picture mutable = new MutablePixelArrayPicture(PixelArray2D.clone(),"caption");
		assertTrue(mutable.getCaption().equals("caption"));
	}
	@Test
	public void testNewPaintMethod()
	{
		Picture mutable = new MutablePixelArrayPicture(PixelArray2D.clone(),"caption");
		Pixel[][] pixelArray = { {new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3)}, 
				   {new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3)},
				   {new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3), new GrayPixel(0.3)},};
		Picture other = new MutablePixelArrayPicture(pixelArray,"caption");
		mutable.paint(1, 1, other);
		assertTrue(mutable.getPixel(1, 1).equals(other.getPixel(1, 1)));
	}
	
	@Test
	public void testSampleIterator()
	{
		Pixel[][] array = new ColorPixel[3][3];
		for(int i = 0; i <  array.length; i++)
		{
			for(int j = 0; j < array[0].length; j++)
			{
				array[i][j] =  new ColorPixel(0,0,0);
			}
		}
		Picture newPic = new MutablePixelArrayPicture(array, "test caption");
		Iterator<Pixel> iterator = newPic.sample(0, 0, 2, 2);
		assertTrue(iterator.hasNext());
		assertEquals(array[0][0], iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(array[2][0], iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(array[0][2], iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(array[2][2], iterator.next());
	}
	@Test
	public void testGetWidth()
	{
		Picture mutable = new MutablePixelArrayPicture(PixelArray2D.clone(),"caption");
		assertEquals(mutable.getWidth(), PixelArray2D.length);
	}
	@Test
	public void testGetHight()
	{
		Picture mutable = new MutablePixelArrayPicture(PixelArray2D.clone(),"caption");
		assertEquals(mutable.getHeight(), PixelArray2D[0].length);
	}
	@Test
	public void testGetPixel()
	{
		Picture mutable = new MutablePixelArrayPicture(PixelArray2D.clone(),"caption");
		assertEquals(mutable.getPixel(1, 1), PixelArray2D[1][1]);
	}
	@Test
	public void testNullArrayMutable()
	{
		try {
			Picture mutable = new MutablePixelArrayPicture(null," ");
		} catch (Exception E) { }
	}
	@Test
	public void testNullArrayElementsMutable()
	{
		Pixel[][] PixelArray2D1 = { {null, new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)}, 
				   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)},
				   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)} };
		try {
			Picture mutable = new MutablePixelArrayPicture(PixelArray2D1," ");
		} catch (Exception E) { }
	}
	@Test
	public void testNullArrayImmutable()
	{
		try {
			Picture mutable = new ImmutablePixelArrayPicture(null," ");
		} catch (Exception E) { }
	}
	@Test
	public void testNullArrayElementsImmutable()
	{
		Pixel[][] PixelArray2D1 = { {null, new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)}, 
				   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)},
				   {new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5), new GrayPixel(0.5)} };
		try {
			Picture mutable = new ImmutablePixelArrayPicture(PixelArray2D1," ");
		} catch (Exception E) { }
	}
	
}