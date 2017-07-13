package net.BITF.myo;

import java.awt.Robot;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private DataCollector dataCollector;

	private int x=600,y=600;//マウス初期位置
	private float v = 3,s = 2;

	public MyoControl(Myo myo, Hub hub){
		/*
		 * Myoの初期化
		 */

		System.out.println("MyoControll:" + myo);

		this.myo = myo;
		this.hub = hub;

		dataCollector = new DataCollector();
		hub.addListener(dataCollector);

	}

	public void update(){
		hub.run(1000/20);

		myocon();	//実処理

	}
	public DataCollector getDataCollector(){
		return dataCollector;
	}
	public void myocon(){

		Robot r = dataCollector.getRobot();

		double roll_w = dataCollector.getRollW();
		double pitch_w = dataCollector.getPitchW();


		if(x<8){
			x=(int) (x-(((int)roll_w-8)*v)); //左
		}
		else{
			x=(int) (x-(((int)roll_w-8)*s));//右
		}
		y=y-(int)pitch_w+8;//上下

		if (dataCollector.getCurrentPose() != null) {
			String pose = dataCollector.getCurrentPose().getType().toString();

			}
		}



	}

