package net.BITF.myo;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private Robot robot;
	private Point point;

	private DataCollector dataCollector;

	private int x, y;//マウス初期位置
	private float v = 3, s = 2;



	public MyoControl(Myo myo, Hub hub){
		/*
		 * Myoの初期化
		 */

		System.out.println("MyoControll:" + myo);

		x = y = 100;
		point = new Point(x, y);

		this.myo = myo;
		this.hub = hub;

		dataCollector = new DataCollector();
		hub.addListener(dataCollector);

		robot = dataCollector.getRobot();

	}

	public void update(){
		hub.run(1000/20);

		if(dataCollector.flag){
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}

		myocon();	//実処理

	}

	public DataCollector getDataCollector(){
		return dataCollector;
	}

	public void myocon(){
		x = (int) point.getX();
		y = (int) point.getY();

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


		robot.mouseMove(x, y);
		point.setLocation(x, y);

	}



}

