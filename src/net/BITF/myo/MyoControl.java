package net.BITF.myo;

import net.BITF.Main;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private DataCollector dataCollector;

	public MyoControl(){
		/*
		 * Myoの初期化
		 */
		myo = Main.myo;
		hub = Main.hub;

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
