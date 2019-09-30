package a6;

import a6.ObservablePicture;
import a6.ROIObserver;
import a6.Region;

public class ROIObserverImpl implements ROIObserver {

	private int timesNotified;
	
	public ROIObserverImpl() {
		timesNotified = 0;
	}
	
	public void notify(ObservablePicture picture, Region changed_region) {
		timesNotified++;
	}
	
	public int getTimesNotified() {
		return timesNotified;
	}
}
