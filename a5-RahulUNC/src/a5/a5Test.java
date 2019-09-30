package a5;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Iterator;

public class a5Test {
	Pixel[][] rArray = new Pixel[5][5];
	Pixel[][] gArray = new Pixel[5][5];
	Pixel[][] bArray = new Pixel[5][5];
	Pixel[][] wArray = new Pixel[5][5];

	String ca = "caption";

	
	
	
	
	
	
	Pixel red = new ColorPixel(1, 0, 0);
	Pixel green = new ColorPixel(0, 1, 0);
	Pixel blue = new ColorPixel(0, 0, 1);
	Pixel yellow = new ColorPixel(0.9, 1, 0.1);
	Pixel white = new ColorPixel(1, 1, 1);
	
	
	
	
	
	
	@Test
	public void testForCaption() {
		Picture picture = new MutablePixelArrayPicture(new Pixel[][] {{new ColorPixel(0,0,0)}}, "Angela");
		assertEquals("Angela", picture.getCaption());  
		
		
		
		
		
	}
	
	// MutablePixelArrayPicture(Pixel[][] pixels, String caption);
	@Test
	public void testCaptionSetter() {
		Picture picture = new MutablePixelArrayPicture(new Pixel[][] {{new ColorPixel(0,0,0)}}, "Angela");
		picture.setCaption("Angela");
		assertEquals("Angela", picture.getCaption());
	
	}
	

	
	@Test
	public void testCaptionGetters() {
		try {
		Picture picture = new MutablePixelArrayPicture(new Pixel[][] {{new ColorPixel(0,0,0)}}, null);
		fail("Caption cannot be null");
	} catch (IllegalArgumentException e) {
	}
	}
	
	// ImmutablePixelArrayPicture(Pixel[][] pixels, String caption);
	@Test
	public void testNewPaintPixelTest() {
		Pixel[][] greenParray = new Pixel[5][5];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				greenParray[x][y] = green;
			}
		}
		
		Pixel[][] blueParray = new Pixel[5][5];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				blueParray[x][y] = blue;
			}
		}
	}
	
	@Test
	public void testPaintingPixels() {
		Pixel[][] parray = new Pixel[5][5];
		for (int x=0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				parray[x][y] = red;
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "angie");
		Picture result = picture.paint(3, 3, blue);
		check_for_component_equality(result.getPixel(3, 3), blue);
		assertNotEquals(picture, result);
	}
	
	@Test
	public void testPaintParams() {
		Picture picture = new MutablePixelArrayPicture (new Pixel[][] {{new ColorPixel(0,0,0)}}, "angie");
		try {
			picture.paint(-1, -1, blue, 1.0);
			fail("negative x");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(picture.getWidth(), 1, green, 0.5);
			fail("illegal x");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(1, -1, blue, 0.5);
			fail("negative y");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(1, picture.getHeight(), blue, 0.5);
			fail("illegal y");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(-1, -1, blue, 1.0);
			fail("negative x");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(1, 1, red, 1.5);
			fail("factor out of range");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.paint(1, 1, green, -1);
			fail("factor is negative");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testGettersForColorPixel() {
		assertNotSame(red, blue);
		assertNotSame(red, green);
		assertNotSame(green, blue);
		assertNotSame(green, red);
		assertNotSame(blue, red);
		assertNotSame(blue, green);
	}
	
	@Test
	public void testColorPixelConstructor() {
		try {
			Pixel noRed = new ColorPixel(-1, 0.5, 0.5);
			fail("red value negative");
		} catch (IllegalArgumentException e) {
		}
		try {
			Pixel noRed2 = new ColorPixel(2, 0.5, 0.5);
			fail("red value too big");
		} catch (IllegalArgumentException e) {
		}
		try {
			Pixel noBlue = new ColorPixel(0.5, -1, 0.5);
			fail("blue value negative");
		} catch (IllegalArgumentException e) {
		}
		try {
			Pixel noBlue2 = new ColorPixel(0.5, 3, 0.5);
			fail("blue value too high");
		} catch (IllegalArgumentException e) {
		}
		try {
			Pixel noGreen = new ColorPixel(0.5, 0.5, -0.5);
			fail("green value negative");
		} catch (IllegalArgumentException e) {
		}
		try {
			Pixel noGreen = new ColorPixel(0.5, 0.5, 3);
			fail("green value too high");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testGrayPixelConstructor() {
		try {
			Pixel noGray = new GrayPixel(-2);
			fail("intensity negative");
		} catch (IllegalArgumentException e) {
		}
	}
	
	private void makeArrays() {
		for(int x = 0 ; x<5; x++){
			for(int y = 0; y<5; y++){
				rArray[x][y] = red;
				gArray[x][y] = green;
				bArray[x][y] = blue;
				wArray[x][y] = white;
			}
		}
	}

	@Test
	public void testMutableNewPaint() {

		makeArrays();

		Picture rPic = new MutablePixelArrayPicture(rArray,"red picture");
		Picture bPic = new MutablePixelArrayPicture(bArray,"blue picture");

		rPic.paint(1,1,bPic);

		check_for_component_equality(red,rPic.getPixel(0,0));
		check_for_component_equality(blue,rPic.getPixel(2,2));
	}

	@Test
	public void testImmutableNewPaint() {

		makeArrays();

		Picture rPic = new ImmutablePixelArrayPicture(rArray,"red picture");
		Picture bPic = new ImmutablePixelArrayPicture(bArray,"blue picture");
		Picture testPic;

		testPic = rPic.paint(1,1,bPic);

		check_for_component_equality(red,rPic.getPixel(0,0));
		check_for_component_equality(blue,testPic.getPixel(1,1));
		check_for_component_equality(red,rPic.getPixel(2,2));
	}

	@Test
	public void testExtractIllegalArguments() {
		makeArrays();

		Picture p = new MutablePixelArrayPicture(rArray,"red pic");

		try{p.extract(20,5,2,2);
			fail("initial x or y out of bounds");
		} catch (Exception e) {
		}

		try{p.extract(5,5,-1,3);
			fail("negative value for dx or dy");
		} catch (Exception e) {
		}

	}

	

	@Test
	public void testZigzag() {
		makeArrays();

		Pixel[][] bigArray = new Pixel[8][8];

		for(int x = 0 ; x<8; x++){
			for(int y = 0; y<8; y++){
				bigArray[x][y]=white;
			}
		}

		Picture bigPic = new MutablePixelArrayPicture(bigArray,ca);
		Picture rPic = new ImmutablePixelArrayPicture(rArray,ca);

		bigPic.paint(0,0,rPic);

		
    
    
  ZigZagIterator it = new ZigZagIterator(bigPic);

		int n = 0;
		while(it.hasNext()){

			Pixel tmp = it.next();

			if(n==1){
				check_for_component_equality(red,tmp);
			}
			else if (n==57){
				check_for_component_equality(white,tmp);
			}

			n++;

		}


	}

	@Test
	public void testMutableConstructor() {
		 Pixel[][] pArrayNull = null;

		 Pixel[][] pArrayNullElement = new Pixel[2][2];
		 pArrayNullElement[1][1] = null;

		 try{
		 	Picture p = new MutablePixelArrayPicture(pArrayNull,ca);
		 	fail("null pixel array");
		 }
		 catch (IllegalArgumentException e) {
		}

		try {
			Picture p = new MutablePixelArrayPicture(pArrayNullElement, ca);
			fail("null elements in pixel array");
		}
		catch (IllegalArgumentException e) {
		}

	}

	@Test
	public void testGetters() {
		makeArrays();

		Picture p = new ImmutablePixelArrayPicture(rArray,ca);

		assertTrue(rArray.length==p.getWidth());
		assertTrue(rArray[0].length==p.getHeight());
		check_for_component_equality(p.getPixel(1,1),rArray[1][1]);
	}


	private static boolean check_for_component_equality(Pixel a, Pixel b) {
		assertEquals(a.getRed(), b.getRed(), 0.001);
		assertEquals(a.getGreen(), b.getGreen(), 0.001);
		assertEquals(a.getBlue(), b.getBlue(), 0.001);

		return true;
	}
	
	@Test
	public void testTile() {
		Pixel[][] parray = new Pixel[5][5];

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Iterator<SubPicture> tile_iter = picture.tile(2, 2);
		
		assertTrue(tile_iter.hasNext());
		check_for_component_equality(picture.extract(0,0,2,2).getPixel(0, 0), tile_iter.next().getPixel(0, 0));
		assertTrue(tile_iter.hasNext());
		check_for_component_equality(picture.extract(2,0,2,2).getPixel(0, 0), tile_iter.next().getPixel(0, 0));
		assertTrue(tile_iter.hasNext());
		check_for_component_equality(picture.extract(0,2,2,2).getPixel(0, 0), tile_iter.next().getPixel(0, 0));
		assertTrue(tile_iter.hasNext());
		check_for_component_equality(picture.extract(2,2,2,2).getPixel(0, 0), tile_iter.next().getPixel(0, 0));
		}
	
	@Test
	public void testZigZag() {
		Pixel[][] parray = new Pixel[4][4];

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Iterator<Pixel> pixel_iter = picture.zigzag();
		picture.paint(0, 0, 2, 2, Pixel.WHITE);
		
		int count = 0;
		while(pixel_iter.hasNext()) {
			if(count == 3) {
				check_for_component_equality(pixel_iter.next(), Pixel.WHITE);
				count++;
			} else if (count == 14) {
				check_for_component_equality(pixel_iter.next(), Pixel.BLACK);
			}
			
			pixel_iter.next();
			count++;
		}
		
	}
	
	@Test
	public void testPicturePaint() {
		Pixel[][] parray = new Pixel[10][10];
		Pixel[][] p_array = new Pixel[3][2];

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 2; y++) {
				p_array[x][y] = Pixel.WHITE;
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Picture picture_two = new MutablePixelArrayPicture(p_array, "Caption");
		picture.paint(2, 7, picture_two);
		
		check_for_component_equality(picture.getPixel(2,7), Pixel.WHITE);
		check_for_component_equality(picture.getPixel(3,7), Pixel.WHITE);
		check_for_component_equality(picture.getPixel(4,7), Pixel.WHITE);
		check_for_component_equality(picture.getPixel(2,8), Pixel.WHITE);
		check_for_component_equality(picture.getPixel(3,8), Pixel.WHITE);
		check_for_component_equality(picture.getPixel(4,8), Pixel.WHITE);
	}
	
	@Test
	public void testSubCaption() {
		Pixel[][] parray = new Pixel[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		SubPicture sub = picture.extract(0, 0, 1, 1);
		assertEquals(sub.getCaption(), "My caption");
		
	}
	
	@Test
	public void testSubGetHeight() {
		Pixel[][] parray = new Pixel[4][4];

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		SubPicture sub = picture.extract(1, 1, 1, 1);
		SubPicture sub2 = picture.extract(1, 1, 2, 2);
		assertEquals(sub.getHeight(), 1);
		assertEquals(sub2.getHeight(), 2);
		
	}
	
	@Test
	public void testSubGetWidth() {
		Pixel[][] parray = new Pixel[4][4];

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		SubPicture sub = picture.extract(1, 1, 1, 1);
		SubPicture sub2 = picture.extract(1, 1, 2, 2);
		assertEquals(sub.getWidth(), 1);
		assertEquals(sub2.getWidth(),2);
	}
	
	@Test
	public void testSubGetPixel() {
		Pixel[][] parray = new Pixel[5][5];

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		picture.paint(1, 1, Pixel.WHITE);
		SubPicture sub = picture.extract(0, 0, 3, 3);
		assertEquals(picture.getPixel(1, 1), sub.getPixel(1, 1));
	}
	
	@Test
	public void testSampleIter() {
		Pixel[][] parray = new Pixel[3][3];

		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");

		Iterator<Pixel> iter = picture.sample(0, 0, 2, 2);
		assertTrue(iter.hasNext());
		assertEquals(parray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][2], iter.next());
		assertFalse(iter.hasNext());	
	}
	@Test 
	public void testMutableValidSample() {

		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = new ColorPixel(1, 1, 1);
			}
		}
		try {
			Picture picture = new MutablePixelArrayPicture(null, "My caption");
			fail("Pixel array is null");
		} catch (IllegalArgumentException e) {

		}
		try {
			Picture picture = new MutablePixelArrayPicture(parray, "My caption").
					paint(1, 1, 2, 2, null);
			fail("Pixel is null");
		} catch (IllegalArgumentException e) {	
		}
		try { 
			Picture picture = new MutablePixelArrayPicture(parray, "My caption").
					paint(-1, 0, green);
			fail("x is negative");
		} catch(IllegalArgumentException e) {
		}
	}
	@Test 
	public void testImmutableValidSample() {

		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = new ColorPixel(1, 1, 1);
			}
		}
		try {
			Picture picture = new ImmutablePixelArrayPicture(null, "My caption");
			fail("Pixel array is null");
		} catch (IllegalArgumentException e) {

		}
		try {
			Picture picture = new ImmutablePixelArrayPicture(parray, "My caption").
					paint(1, 1, 2, 2, null);
			fail("Pixel is null");
		} catch (IllegalArgumentException e) {	
		}
		try { 
			Picture picture = new ImmutablePixelArrayPicture(parray, "My caption").
					paint(-1, 0, green);
			fail("x is negative");
		} catch(IllegalArgumentException e) {
		}
	}
	@Test 
	public void testCaption() {
		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = new ColorPixel(1, 1, 1);
			}
		}
		String a = "Hello World";
		Picture picture = new MutablePixelArrayPicture(parray, a);
		assertEquals(a, picture.getCaption());

		Picture picture1 = new MutablePixelArrayPicture(parray, "Hello World");
		Picture image = picture.paint(0, 0, 3, 2, green);
		assertEquals(picture.getCaption(), image.getCaption());


		Picture picture2 = new ImmutablePixelArrayPicture(parray, "Hello World");
		Picture image1 = picture.paint(0, 0, 3, 2, green);
		assertEquals(picture2.getCaption(), image1.getCaption());

	}
	@Test
	public void testExtract() {
		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = new ColorPixel(1, 1, 1);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		try {
			picture.extract(0, 0, 0, 1);
			fail("dx is less than 1");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.extract(0, 0, 2, 0);
			fail("dy is less than 1");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.extract(-1, 0, 2, 0);
			fail("x is negative");
		} catch (IllegalArgumentException e) {
		}
		try {
			picture.extract(7, 0, 2, 0);
			fail("x is above width");
		} catch(IllegalArgumentException e) {
		}
		try {
			picture.extract(0, 7, 2, 0);
			fail("y is above height");
		} catch(IllegalArgumentException e) {
		}
	}

	@Test
	public void testMutablePaint() {
		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = red;
			}
		}
		Pixel[][] parray1 = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray1[x][y] = blue;
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "red picture");
		Picture picture1 = new MutablePixelArrayPicture(parray1, "blue picture");

		picture.paint(1, 1, picture1);

		check_for_component_equality(red, picture.getPixel(0, 0));
		check_for_component_equality(blue, picture.getPixel(2, 2));
	}
	@Test
	public void testImmutablePaint() {
		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = red;
			}
		}
		Pixel[][] parray1 = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray1[x][y] = blue;
			}
		}
		Picture pic = new ImmutablePixelArrayPicture(parray, "red picture");
		Picture pic1 = new ImmutablePixelArrayPicture(parray1, "blue picture");
		Picture pic2;

		pic2 = pic.paint(1, 1, pic1);

		check_for_component_equality(red, pic.getPixel(0, 0));
		check_for_component_equality(blue, pic2.getPixel(2, 2));
		check_for_component_equality(red, pic.getPixel(2, 2));
	}

	@Test
	public void testMutableTileIter() {
		Pixel[][] wArray = new Pixel[9][9];
		for(int x = 0; x < wArray.length; x++) {
			for (int y = 0; y < wArray[0].length; y++) {
				wArray[x][y] = yellow;
			}
		}

		Pixel[][] rArray = new Pixel[5][5];
		for(int x = 0; x < rArray.length; x++) {
			for (int y = 0; y < rArray[0].length; y++) {
				rArray[x][y] = red;
			}
		}
		Pixel[][] bArray = new Pixel[5][5];
		for(int x = 0; x < bArray.length; x++) {
			for (int y = 0; y < bArray[0].length; y++) {
				bArray[x][y] = blue;
			}
		}
		Pixel[][] gArray = new Pixel[5][5];
		for(int x = 0; x < gArray.length; x++) {
			for (int y = 0; y < gArray[0].length; y++) {
				gArray[x][y] = green;
			}
		}
		Pixel[][] yArray = new Pixel[5][5];
		for(int x = 0; x < yArray.length; x++) {
			for (int y = 0; y < yArray[0].length; y++) {
				yArray[x][y] = yellow;
			}
		}

		Picture subRed = new MutablePixelArrayPicture(rArray, "red picture");
		Picture subBlue = new MutablePixelArrayPicture(bArray, "blue picture");
		Picture subGreen = new MutablePixelArrayPicture(gArray, "green picture");
		Picture wholePic = new MutablePixelArrayPicture(wArray, "whole picture");


		wholePic.paint(0, 0, subRed);
		wholePic.paint(4, 0, subBlue);
		wholePic.paint(0, 4, subGreen);


		Iterator<SubPicture> iter = wholePic.tile(5, 5);

		for(int x = 0; x < 4; x++) {
			while(iter.hasNext()) {
				Picture subPic = iter.next();
				if(x == 0) {
					check_for_component_equality(subPic.getPixel(0, 0),red);
				} else if (x == 1) {
					check_for_component_equality(subPic.getPixel(4, 0),blue);
				} else if(x == 2) {
					check_for_component_equality(subPic.getPixel(0, 4), green);
				} else if(x == 3) {
					check_for_component_equality(subPic.getPixel(4, 4), yellow);
				}
			}
		}
	}
	
	@Test
	public void testImmutablePicture() {
		Pixel[][] parray = new Pixel[5][5];
		for(int x = 0; x < parray.length; x++) {
			for (int y = 0; y < parray[0].length; y++) {
				parray[x][y] = red;
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "red picture");
		Picture picture1 = picture.paint(0, 0, green);
		assertNotEquals(picture, picture1);
		check_for_component_equality(red, picture.getPixel(0, 0));
		check_for_component_equality(green, picture1.getPixel(0, 0));
	}
	 @Test
	 public void testMutableZigzagIter() {

			Pixel[][] parray = new Pixel[5][5];
			for(int x = 0; x < parray.length; x++) {
				for (int y = 0; y < parray[0].length; y++) {
					parray[x][y] = red;
				}
			}
			Picture picture = new MutablePixelArrayPicture(parray, "My caption").
					paint(1, 1, 3, 3, green);
		
			Iterator<Pixel> iter = picture.zigzag();
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 0), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 0), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 2), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 1), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(0, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(0, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(1, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(4, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 1), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(1, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 4), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(4, 4), iter.next());
			assertFalse(iter.hasNext());	
			
	 }
	 @Test
	 public void testImmutableZigzagIter() {

			Pixel[][] parray = new Pixel[5][5];
			for(int x = 0; x < parray.length; x++) {
				for (int y = 0; y < parray[0].length; y++) {
					parray[x][y] = red;
				}
			}
			Picture pic = new ImmutablePixelArrayPicture(parray, "My caption");
			Picture picture = pic.paint(1, 1, 3, 3, green);
			
			assertNotEquals(pic, picture);
			Iterator<Pixel> iter = picture.zigzag();
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 0), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 0), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(0, 2), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 1), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(1, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(0, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(0, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(1, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 1), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(4, 0), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 1), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(1, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(2, 4), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 2), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(4, 3), iter.next());
			assertTrue(iter.hasNext());	
			check_for_component_equality(picture.getPixel(3, 4), iter.next());
			assertTrue(iter.hasNext());
			check_for_component_equality(picture.getPixel(4, 4), iter.next());
			assertFalse(iter.hasNext());	
			
	 }
	 
	 String caption1 = "Caption 1";
		String caption2 = "Caption 2";
		String caption3 = "Caption 3";
	 @Test
		public void testPictureCaptionGetter() {
			//Want to test the setter, and getter of Picture caption
			Pixel[][] parray = new Pixel[3][3];
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y <3; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}

			Picture picture = new MutablePixelArrayPicture(parray, "My Caption");
			//set caption
			assertEquals("My Caption", picture.getCaption());
			assertNotEquals(caption1, picture.getCaption());
			assertNotEquals(caption2, picture.getCaption());

			picture.setCaption(caption2);
			assertEquals(caption2, picture.getCaption());
			assertNotEquals(caption1, picture.getCaption());
			
		}
		
		@Test
		public void testPictureCaptionSetter() {
			//Testing validation of Caption setters
			Pixel[][] parray = new Pixel[3][3];
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y <3; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}
			
			try {
				Picture illegalString = new MutablePixelArrayPicture(parray, null);
				fail("Caption cannot be null");
			} catch (IllegalArgumentException e) {
			}
			
		}
		
		@Test 
		public void testPictureSampleBounds() {
			//Testing Sample Bounds
			Pixel[][] red = new Pixel[10][10];
			for(int x =0; x < 10; x++) {
				for(int y =0; y<10; y++) {
					if(x%2 == 0) {
						red[x][y] = new ColorPixel(1, 0, 0);
					}
					else {
						red[x][y] = new GrayPixel(.5);
					}
				}
			}
			
			Picture redAndGray = new MutablePixelArrayPicture(red, "redAndGray");
			
			try {
				redAndGray.sample(11,  3,  1,  1);
				fail("Input x is out of bounds");
			} catch (IllegalArgumentException e){
			}
			try {
				redAndGray.sample(2, 15, 1,  1);
				fail("Input y is out of bounds");
			} catch(IllegalArgumentException e) {
			}
			try {
				redAndGray.sample(5,  5, -1,  2);
				fail("Input dx cannot be negative");
			} catch(IllegalArgumentException e) {
			}
			try {
				redAndGray.sample(5, 5, 2, -1);
				fail("Input dy cannot be negative");
			} catch(IllegalArgumentException e) {
			}
		}
		
		@Test
		public void testPictureSample() {
			//Testing Picture Sample outputs
			Pixel[][] red = new Pixel[15][10];
			for(int x =0; x < 15; x++) {
				for(int y =0; y<10; y++) {
					if(x%2 == 0) {
						red[x][y] = new ColorPixel(1, 0, 0);
					}
					else {
						red[x][y] = new GrayPixel(.5);
					}
				}
			}
			
			Picture redAndGray = new MutablePixelArrayPicture(red, "redAndGray");
			Iterator<Pixel> sample = redAndGray.sample(2,3, 3, 4);

			check_for_component_equality(sample.next(), redAndGray.getPixel(2, 3));
			check_for_component_equality(sample.next(), redAndGray.getPixel(5, 3));
			check_for_component_equality(sample.next(), redAndGray.getPixel(8, 3));
			check_for_component_equality(sample.next(), redAndGray.getPixel(11, 3));
			check_for_component_equality(sample.next(), redAndGray.getPixel(14, 3));
			check_for_component_equality(sample.next(), redAndGray.getPixel(2, 7));
			check_for_component_equality(sample.next(), redAndGray.getPixel(5, 7));
			check_for_component_equality(sample.next(), redAndGray.getPixel(8, 7));
			check_for_component_equality(sample.next(), redAndGray.getPixel(11, 7));
			check_for_component_equality(sample.next(), redAndGray.getPixel(14, 7));
			assertFalse(sample.hasNext());
		}
		
		@Test
		public void testExtractBounds() {
			//Testing Picture Extract Method Bounds
			Pixel [][] parray = new Pixel[5][5];
			for(int x = 0; x<5;x++) {
				for(int y = 0; y<5; y++) {
					parray[x][y] = new ColorPixel(0, 1, 0);
				}
			}
			Picture picture = new MutablePixelArrayPicture(parray, "Caption");
			
			try {
				Picture pextract = picture.extract(6,  2, 2, 2);
				fail("X value is not in picture");
			} catch(IllegalArgumentException e) {
			}
			try {
				Picture pextract = picture.extract(2, 6, 2, 2);
				fail("Y value is not in picture");
			} catch(IllegalArgumentException e) {
			}
			try {
				Picture pextract = picture.extract(2, 2, -1, 3);
				fail("Width cannot be negative");
			} catch(IllegalArgumentException e) {
			}
			try {
				Picture pextract = picture.extract(2, 2, 2, -1);
				fail("Height cannot be negative");
			} catch(IllegalArgumentException e) {
			}
		}
		
		@Test
		public void testPictureExtract() {
			Pixel [][] parray = new Pixel[5][5];
			for(int x = 0; x<5;x++) {
				for(int y = 0; y<5; y++) {
					if((x%2==0) && (y%2==0)) {
						parray[x][y] = new ColorPixel(1,0,1);
					}
					else {
						parray[x][y] = new GrayPixel(0.5);
					}
				}
			}
			Picture picture = new MutablePixelArrayPicture(parray, "Caption");
			
			Pixel [][] check = new Pixel[2][2];
			for(int i = 0; i< 2; i++) {
				for(int j = 0; j<2; j++) {
					if(i%2 == 0 && j%2 ==0) {
						check[i][j] = new ColorPixel(1,0,1);
					}
					else {
						check[i][j] = new GrayPixel(0.5);
					}
					
				}
			}
			Picture checked = new MutablePixelArrayPicture(check, "check");
			
			SubPicture pextract = picture.extract(0, 0, 2, 2);
			for(int m = 0; m<2; m++) {
				for(int n = 0; n<2; n++) {
					check_for_component_equality(checked.getPixel(m, n), pextract.getPixel(m, n));
				}
			}
			
			
		}
		
		@Test
		public void testPaintMethod() {
			Pixel[][] parray = new Pixel[3][3];
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y <3; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}
			
			Picture mutable = new MutablePixelArrayPicture(parray, "mutable");
			Picture immutable = new ImmutablePixelArrayPicture(parray, "immutable");
			Pixel blue = new ColorPixel(0,0,1);
			
			Picture pmutable = mutable.paint(2, 2, blue);
			assertEquals(mutable, pmutable);
			
			Picture pimmutable = immutable.paint(2, 2, blue);
			assertNotEquals(immutable, pimmutable);
			
		}
		
		@Test
		public void testWindowBounds() {
			//Testing Window method Bounds
			Pixel[][] parray = new Pixel[3][3];
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y <3; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}
			
			Picture picture = new MutablePixelArrayPicture(parray, "window");
			
			try {
				picture.window(5, 2);
				fail("Width is outside the picture");
			} catch(IllegalArgumentException e) {
			}
			try {
				picture.window(2, 5);
				fail("Height is outside the picture");
			} catch(IllegalArgumentException e) {
			}
			try {
				picture.window(-1, 2);
				fail("Width cannot be negative");
			} catch(IllegalArgumentException e) {
			}
			try {
				picture.window(2, -1);
				fail("Height cannoy be negative");
			} catch(IllegalArgumentException e) {
			}
			
		}
		
		@Test
		public void testWindow() {
			//Testing window method
			Pixel[][] parray = new Pixel[5][5];
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y <5; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}
			
			Picture picture = new MutablePixelArrayPicture(parray, "window");
			Iterator<SubPicture> window = picture.window(3, 2);
			
			check_sub_picture(window.next(), picture.extract(0, 0, 3, 2));
			check_sub_picture(window.next(), picture.extract(1, 0, 3, 2));
			check_sub_picture(window.next(), picture.extract(2, 0, 3, 2));
			check_sub_picture(window.next(), picture.extract(0, 1, 3, 2));
			check_sub_picture(window.next(), picture.extract(1, 1, 3, 2));
			check_sub_picture(window.next(), picture.extract(2, 1, 3, 2));
			check_sub_picture(window.next(), picture.extract(0, 2, 3, 2));
			check_sub_picture(window.next(), picture.extract(1 ,2 ,3 ,2));
			check_sub_picture(window.next(), picture.extract(2 ,2 ,3 ,2));
			check_sub_picture(window.next(), picture.extract(0 ,3 ,3 ,2));
			check_sub_picture(window.next(), picture.extract(1 ,3 ,3 ,2));
			check_sub_picture(window.next(), picture.extract(2 ,3 ,3 ,2));
		}
		
		@Test
		public void testPictureTile() {
			//Testing tile method
			Pixel[][] parray = new Pixel[5][5];
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y <5; y++) {
					parray[x][y] = new ColorPixel(1, 0, 0);
				}
			}
			
			Picture picture = new MutablePixelArrayPicture(parray, "window");
			Iterator<SubPicture> tile = picture.tile(2, 2);
			
			check_sub_picture(tile.next(), picture.extract(0,0,2,2));
			check_sub_picture(tile.next(), picture.extract(2,0,2,2));
			check_sub_picture(tile.next(), picture.extract(0,2,2,2));
			check_sub_picture(tile.next(), picture.extract(2,2,2,2));
			
		}
		
		private static boolean check_sub_picture(SubPicture a, SubPicture b) {
			int aW = a.getWidth();
			int aH = a.getHeight();
			for(int i = 0; i<aW; i++) {
				for(int j = 0; j<aH; j++) {
					Pixel aP = a.getPixel(i, j);
					Pixel bP = b.getPixel(i,  j);
					check_for_component_equality(aP, bP);
				}
			}
			return false;
		}


}