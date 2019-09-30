package a6;

public class ROIObserverRegionCombo {
	
	private ROIObserver observe;
	private Region r;
	
	public ROIObserverRegionCombo(ROIObserver observe, Region r)
	{
		this.observe = observe;
		this.r = r;
	}
	
	public ROIObserver getROIObserver() {
		return observe;
	}
	
	public Region getRegion()
	{
		return r;
	}

}
