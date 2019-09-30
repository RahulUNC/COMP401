package a3;

public class tester {

	public static void main(String[] args) {
		Pixel initialPixel = new ColorPixel(0.2, 0.2, 0.2);
		Pixel initialPixel1 = new ColorPixel(0.4, 0.4, 0.4);
		Picture test1 = new MutablePixelArrayPicture(3,4,initialPixel1);
		test1.paint(1, 1, 3, 3, initialPixel);
		//test1.paint(-1, -1, 2, initialPixel);
		for(int i = 0; i < test1.getWidth(); i++)
		{
			System.out.println("");
			for(int j = 0; j < test1.getHeight(); j++)				
			{
				System.out.print(test1.getPixel(i,j).getIntensity() + " ");
			}
		}
	}

}
