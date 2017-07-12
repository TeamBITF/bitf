package net.BITF.myo;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private DataCollector dataCollector;

	public MyoControl(Myo myo, Hub hub){
		/*
		 * Myoの初期化
		 */

		System.out.println("MyoControll" + myo);

		this.myo = myo;
		this.hub = hub;

		dataCollector = new DataCollector();
		hub.addListener(dataCollector);

	}

	public void update(){
		hub.run(1000/20);
	}

	public DataCollector getDataCollector(){
		return dataCollector;
	}
}
