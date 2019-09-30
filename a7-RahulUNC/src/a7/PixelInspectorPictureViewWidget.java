package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

public class PixelInspectorPictureViewWidget extends JPanel implements MouseListener, MouseEvent{

	
	private PictureView picture_view;
	private JLabel pixelX = new JLabel("X Pos: ");
	private JLabel pixelY = new JLabel("Y Pos: ");
	private JLabel red = new JLabel("Red: ");
	private JLabel green = new JLabel("Green: ");
	private JLabel blue = new JLabel("Blue: ");
	private JLabel brightness = new JLabel("Brightness: ");
	
	public PixelInspectorPictureViewWidget(Picture picture) {
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);		
		JPanel container1 = new JPanel(new GridLayout(6,0));
		container1.add(pixelX, BorderLayout.CENTER);
		container1.add(pixelY);
		container1.add(red);
		container1.add(green);
		container1.add(blue);
		container1.add(brightness);
		add(container1, BorderLayout.WEST);
	}
	@Override
	public int getDetail() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractView getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initUIEvent(String arg0, boolean arg1, boolean arg2, AbstractView arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getBubbles() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCancelable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EventTarget getCurrentTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getEventPhase() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EventTarget getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeStamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initEvent(String arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preventDefault() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopPropagation() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getAltKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public short getButton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClientX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClientY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCtrlKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getMetaKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EventTarget getRelatedTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScreenX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScreenY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getShiftKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initMouseEvent(String arg0, boolean arg1, boolean arg2, AbstractView arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, boolean arg9, boolean arg10, boolean arg11, boolean arg12, short arg13,
			EventTarget arg14) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		DecimalFormat d = new DecimalFormat("#.##");
		d.setRoundingMode(RoundingMode.FLOOR);
		pixelX.setText("X Pos: " + arg0.getX());
		pixelY.setText("Y Pos: " + arg0.getY());
		red.setText("Red " + d.format(picture_view.getPicture().getPixel(arg0.getX(), arg0.getY()).getRed()));
		green.setText("Green " + d.format(picture_view.getPicture().getPixel(arg0.getX(), arg0.getY()).getGreen()));
		blue.setText("Blue " + d.format(picture_view.getPicture().getPixel(arg0.getX(), arg0.getY()).getBlue()));
		brightness.setText("Brightness " + d.format(picture_view.getPicture().getPixel(arg0.getX(), arg0.getY()).getIntensity()));
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
