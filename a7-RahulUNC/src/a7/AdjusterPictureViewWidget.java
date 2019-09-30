package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class AdjusterPictureViewWidget extends JPanel implements MouseListener, ChangeListener{

	private PictureView picture_view;
	private JSlider blur;
	private JSlider saturation;
	private JSlider brightness;
	private List<ChangeListener> change_listeners;
	private Picture original;

	public AdjusterPictureViewWidget(Picture picture) {
		original = picture;
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);

		JPanel container = new JPanel(new GridLayout(3,0));
		JPanel blur_panel = new JPanel();		
		JPanel saturation_panel = new JPanel();
		JPanel brightness_panel = new JPanel();

		blur = new JSlider(0, 5, 0);
		blur.setPreferredSize(new Dimension(picture.getWidth() - 40, 50));
		blur.setPaintLabels(true);
		blur.setPaintTicks(true);
		blur.setSnapToTicks(true);
		blur.setMajorTickSpacing(1);

		saturation = new JSlider(-100, 100, 0);
		saturation.setPreferredSize(new Dimension(picture.getWidth() - 80, 50));
		saturation.setPaintLabels(true);
		saturation.setPaintTicks(true);
		saturation.setMajorTickSpacing(50);

		brightness = new JSlider(-100, 100, 0);
		brightness.setPreferredSize(new Dimension(picture.getWidth() - 80, 50));
		brightness.setPaintLabels(true);
		brightness.setPaintTicks(true);
		brightness.setMajorTickSpacing(50);
		JLabel lblBlur = new JLabel("Blur");
		JLabel lblSaturation = new JLabel("Saturation");
		JLabel lblBrightness = new JLabel("Brightness");

		blur_panel.add(lblBlur);
		blur_panel.add(blur);
		saturation_panel.add(lblSaturation);
		saturation_panel.add(saturation);
		brightness_panel.add(lblBrightness);
		brightness_panel.add(brightness);		

		container.add(blur_panel);
		container.add(saturation_panel);
		container.add(brightness_panel);

		add(container, BorderLayout.SOUTH);

		blur.addChangeListener(this);
		saturation.addChangeListener(this);
		brightness.addChangeListener(this);		
		change_listeners = new ArrayList<ChangeListener>();
	}

	public void stateChanged(ChangeEvent e) {	
		Picture blurred = blur(original);
		Picture saturated;
		if(saturation.getValue()!=0)
		{
			 saturated =  saturate(blurred);
		}
		else 
		{
			 saturated = blurred;
		}
		Picture brightened = brighten(saturated);
		picture_view.setPicture(brightened.createObservable());		
	}

	public Picture blur(Picture toPaint)
	{		//implementation discussed with saumil patel
		Picture newPic;
		Pixel[][] newVals = new ColorPixel[toPaint.getWidth()][toPaint.getHeight()];
		int blurFactor = blur.getValue();
		if (blurFactor == 0) {
			newPic = original;
		}
		else {
			for (int x = 0; x < toPaint.getWidth(); x++)
			{
				for(int y = 0; y < toPaint.getHeight(); y++)
				{
					double redSum = 0.0, greenSum = 0.0, blueSum = 0.0;
					int blurArea = 0;

					for(int innerX = x - blurFactor; innerX <= x + blurFactor; innerX++)
					{
						for(int innerY = y - blurFactor; innerY <= y + blurFactor; innerY++)
						{
							try {
								if(innerY != y || innerX != x)
								{
									blurArea++;
									redSum += toPaint.getPixel(innerX, innerY).getRed();
									greenSum += toPaint.getPixel(innerX, innerY).getGreen();
									blueSum += toPaint.getPixel(innerX, innerY).getBlue();
								}
							} catch (RuntimeException e) {							
							}
						}
					}
					redSum /= blurArea;
					greenSum /= blurArea;
					blueSum /= blurArea;
					newVals[x][y] = new ColorPixel(redSum, greenSum, blueSum);
				}
			}
			newPic = new MutablePixelArrayPicture(newVals, "");
		}
		return newPic;
	}
	public Picture saturate(Picture toPaint)
	{
		int f = saturation.getValue();	
		Pixel[][] pixelsToModify = new ColorPixel[toPaint.getWidth()][toPaint.getHeight()];
		for(int i = 0; i < toPaint.getWidth(); i++)
		{
			for(int j = 0; j < toPaint.getHeight(); j++)
			{

				double b = toPaint.getPixel(i, j).getIntensity();
				double red = toPaint.getPixel(i, j).getRed();
				double green = toPaint.getPixel(i, j).getGreen();
				double blue = toPaint.getPixel(i, j).getBlue();
				double[] needToSort = {red, green, blue};
				double[] sorted = needToSort.clone();
				for (int r = 0; r < sorted.length; r++) {
					for (int c = r+1; c < sorted.length; c++) {
						if ( (sorted[r] > sorted[c]) && (r != c) ) {
							double temp = sorted[c];
							sorted[c] = sorted[r];
							sorted[r] = temp;
						}
					}
				}

				if(Math.abs(sorted[2]) < 0.01)
				{
					pixelsToModify[i][j] = toPaint.getPixel(i, j);
				}	
				else if(f < 0)
				{
					double newRed = red * (1.0 + (f/100.0)) - (b * f/100.0);
					double newGreen = green * (1.0 + (f/100.0)) - (b * f/100.0);
					double newBlue = blue * (1.0 + (f/100.0)) - (b * f/100.0);
					pixelsToModify[i][j] =  new ColorPixel(newRed,newGreen, newBlue);
				}
				else
				{
					double lc = sorted[2];
					double newRed = red * ((lc+((1.0-lc)*(f/100.0)))/lc);
					double newGreen = green * ((lc+((1.0-lc)*(f/100.0)))/lc);
					double newBlue = blue * ((lc+((1.0-lc)*(f/100.0)))/lc);
					pixelsToModify[i][j] =  new ColorPixel(newRed,newGreen, newBlue);
				}
			}
		}
		Picture newPic = new MutablePixelArrayPicture(pixelsToModify, "");
		return newPic;
	}		
	public Picture brighten(Picture toPaint)
	{
		double b = brightness.getValue() * 1.0;
		if(b != 0 )
		{
			b = b/100;
			Pixel[][] pixelsToModify = new ColorPixel[toPaint.getWidth()][toPaint.getHeight()];
			for(int i = 0; i < toPaint.getWidth(); i++)
			{
				for(int j = 0; j < toPaint.getHeight(); j++)
				{
					Pixel old = toPaint.getPixel(i, j);
					if (b > 0)
					{
						pixelsToModify[i][j] = old.lighten(b);
					}
					else
					{
						pixelsToModify[i][j] = old.darken(b*-1.0);
					}
				}
			}
			Picture newPic = new MutablePixelArrayPicture(pixelsToModify, "");
			return newPic;
		}
		return toPaint;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void addChangeListener(ChangeListener l) {
		change_listeners.add(l);
	}

	public void removeChangeListener(ChangeListener l) {
		change_listeners.remove(l);
	}

}