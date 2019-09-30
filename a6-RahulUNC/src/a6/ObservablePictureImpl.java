package a6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture{

	private Picture pic;
	private List<ROIObserverRegionCombo> observers;
	private Region union;
	private boolean suspend;
	
	public ObservablePictureImpl (Picture p)
	{
		if(p == null)
			throw new IllegalArgumentException("Input Picture is null!");
		observers = new ArrayList<ROIObserverRegionCombo>();
		suspend = false;
		union = null;
		this.pic = p;
	}
	@Override
	public SubPicture extract(int x, int y, int width, int height) {
		return pic.extract(x, y, width, height);
	}
	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {		
		observers.add(new ROIObserverRegionCombo(observer, r));
	}
	@Override
	//[METHOD] uses the iterator's remove method to remove intersecting regions.
	public void unregisterROIObservers(Region r) {
		Iterator<ROIObserverRegionCombo> i = observers.iterator();
		while(i.hasNext())
		{
			ROIObserverRegionCombo roi = i.next();
			try {
				roi.getRegion().intersect(r);
				i.remove();
			} catch (NoIntersectionException e) {
				continue;
			}
		}
	}
	@Override
	//[METHOD] uses the iterator's remove method to remove identical observer references
	public void unregisterROIObserver(ROIObserver observer) {
		Iterator<ROIObserverRegionCombo> i = observers.iterator();
		while(i.hasNext())
		{
			ROIObserverRegionCombo roi = i.next();
			if(roi.getROIObserver() == observer)
			{
				i.remove();
			}
		}
	}
	@Override
	//[METHOD] adds common intersecting regions to an ArrayList which is later turned into an array
	public ROIObserver[] findROIObservers(Region r) {
		List<ROIObserver> commonObservers = new ArrayList<ROIObserver>();
		for(ROIObserverRegionCombo i : observers)
		{
			try {
				i.getRegion().intersect(r);
				commonObservers.add(i.getROIObserver());
			} catch (NoIntersectionException e)	{
			}
		}
		ROIObserver[] observerArray = new ROIObserver[commonObservers.size()];
		return commonObservers.toArray(observerArray);
	}
	@Override
	//[METHOD] sets the suspend field to true
	public void suspendObservable() {
		suspend = true;
	}
	@Override
	//[METHOD] sets the suspend field to false and notifies observers with the union of all paint regions
	public void resumeObservable() {
		suspend = false;
		if (union != null)
		{
			notifier(union);
			union = null;
		}
	}	
	@Override
	public int getWidth() {
		return pic.getWidth();
	}
	@Override
	public int getHeight() {
		return pic.getHeight();
	}
	@Override
	public Pixel getPixel(int x, int y) {
		return pic.getPixel(x, y);
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		pic = pic.paint(x, y, p, factor);
		Region onePixel = new RegionImpl(x,y,x,y);
		notifier(onePixel);
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint(ax, ay, bx, by, p, 1.0);
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		pic = pic.paint(ax, ay, bx, by, p, factor);
		int smallX = (ax < bx) ? ax : bx;
		int largeX = (ax > bx) ? ax : bx;
		int smallY = (ay < by) ? ay : by;
		int largeY = (ay > by) ? ay : by;		
		Region rectangle = new RegionImpl(smallX, smallY, largeX, largeY);
		notifier(rectangle);
		return pic;
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		pic = pic.paint(cx, cy, radius, p, factor);
		Region circle = new RegionImpl((int)(cx - radius), (int)(cy - radius), (int)(cx + radius), (int)(cy + radius));
		notifier(circle);
		return this;
	}
	@Override
	public Picture paint(int x, int y, Picture p) {
		return paint(x, y, p, 1.0);
	}
	@Override
	public Picture paint(int x, int y, Picture p, double factor) {
		pic = pic.paint(x, y, p, factor);
		int largeX = x + p.getWidth() -1;
		int largeY = y + p.getHeight() -1;
		Region picRegion = new RegionImpl(x, y, largeX, largeY);
		notifier(picRegion);
		return this;
	}
	//[METHOD] called in every paint method, if suspend is on then the union of paints is found
	//if union is false then after every paint method observers are notified
	public void notifier(Region r)
	{
		if(!suspend)
		{
			for(ROIObserverRegionCombo o : observers)
			{
				try {					
					Region intersection = o.getRegion().intersect(r);
					o.getROIObserver().notify(this, intersection);
				} catch (NoIntersectionException e)	{			
				}
			}
		} else {
			union = r.union(union);
		}
	}
	@Override
	public String getCaption() {
		return pic.getCaption();
	}
	@Override
	public void setCaption(String caption) {
		pic.setCaption(caption);
	}
}