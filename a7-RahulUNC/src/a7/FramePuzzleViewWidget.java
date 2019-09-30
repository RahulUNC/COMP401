package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FramePuzzleViewWidget extends JPanel implements MouseListener, KeyListener{
	
	private Picture original;
	private PictureView[][] picture_view;
	private JPanel container;
	private int subWidth, subHeight, blankX = 4, blankY = 4;
	
	public FramePuzzleViewWidget (Picture source){
		original = source;
		setLayout(new BorderLayout());
		container = new JPanel();
		container.addMouseListener(this);
		container.addKeyListener(this);
		container.setFocusable(true);
		container.setLayout(new GridLayout(5,5));
		picture_view = new PictureView[5][5];
		subWidth = original.getWidth() / 5;
		subHeight = original.getHeight() / 5;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(i == 4 && j == 4)
				{
					Pixel[][] monoChrome = new GrayPixel[subWidth][subHeight];
					for(int x = 0; x < subWidth; x++)
					{
						for(int y = 0; y < subHeight; y++)
						{
							monoChrome[x][y] = new GrayPixel(1.0);
						}
					}
					picture_view[i][j] = new PictureView((new MutablePixelArrayPicture(monoChrome, "")).createObservable());
				} else {
					Picture pic = new SubPictureImpl(original, i * subWidth, j * subHeight, subWidth, subHeight);
					picture_view[i][j] = new PictureView(pic.createObservable());
				}
				picture_view[i][j].addMouseListener(this);
				picture_view[i][j].addKeyListener(this);
			}
		}
		for (int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				container.add(picture_view[j][i]);
			}
		}
		add(container);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int xPos = 0, yPos = 0;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(e.getSource() ==  picture_view[i][j]) {
					xPos = i;
					yPos = j;
				}
			}
		}
		int spacesToMove = 0;
		if(xPos < blankX && yPos == blankY)
		{
			spacesToMove = blankX - xPos;
			for(int i = 0; i < spacesToMove; i++)
			{
				Picture temp = picture_view[blankX - 1][blankY].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX - 1][blankY].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankX--;
			}
		}
		else if (xPos > blankX && yPos == blankY)
		{
			spacesToMove = xPos - blankX;
			for(int i = 0; i < spacesToMove; i++)
			{
				Picture temp = picture_view[blankX + 1][blankY].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX + 1][blankY].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankX++;
			}
		}
		else if (yPos < blankY && xPos == blankX)
		{
			spacesToMove = blankY - yPos;
			for(int i = 0; i < spacesToMove; i++)
			{
				Picture temp = picture_view[blankX][blankY - 1].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX][blankY - 1].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankY--;
			}
		} else {
			spacesToMove = yPos - blankY;
			for(int i = 0; i < spacesToMove; i++)
			{
				Picture temp = picture_view[blankX][blankY + 1].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX][blankY + 1].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankY++;
			}
		}
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
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			try {
				Picture temp = picture_view[blankX][blankY - 1].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX][blankY - 1].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankY--;
			} catch (RuntimeException r) {
				
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			try {
				Picture temp = picture_view[blankX][blankY + 1].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX][blankY + 1].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankY++;
			} catch (RuntimeException r) {
				
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			try {
				Picture temp = picture_view[blankX + 1][blankY].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX + 1][blankY].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankX++;
			} catch (RuntimeException r) {
				
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			try {
				Picture temp = picture_view[blankX - 1][blankY].getPicture();
				Picture blankSwap = picture_view[blankX][blankY].getPicture();
				picture_view[blankX - 1][blankY].setPicture(blankSwap.createObservable());
				picture_view[blankX][blankY].setPicture(temp.createObservable());
				blankX--;
			}catch (RuntimeException r) {
				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
