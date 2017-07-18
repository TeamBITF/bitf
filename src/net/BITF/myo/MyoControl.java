package net.BITF.myo;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import net.BITF.frame.MainFrame;
import net.BITF.panel.BITFPanel;
import net.BITF.panel.GamePanel;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {
	private MainFrame frame;

	private Hub hub;
	private Myo myo;

	private Robot robot;
	public static Point point;

	private DataCollector dataCollector;


	public static int y,x;//マウス初期位置
	private float v = 3, s = 2;

	public MyoControl(Myo myo, Hub hub, MainFrame mainFrame){

		/*
		 * Myoの初期化
		 */

		frame = mainFrame;
		System.out.println("MyoControll:" + myo);

		x = y = 100;
		point = new Point(x, y);

		this.myo = myo;
		this.hub = hub;

		dataCollector = new DataCollector(mainFrame);
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
		BITFPanel panel = frame.getPanel();

		x = (int) point.getX();
		y = (int) point.getY();

		double roll_w = dataCollector.getRollW();
		double pitch_w = dataCollector.getPitchW();

		if (panel instanceof GamePanel &&  "WAVE_OUT" != dataCollector.getCurrentPose().getType().toString()){
		if(x<8){
			x=(int) (x-(((int)roll_w-8)*v)); //左
		}
		else{
			x=(int) (x-(((int)roll_w-8)*s));//右
		}
		y=y-(int)pitch_w+8;//上下
		}
		else{
		y=y-(int)pitch_w+8;//上下
		}
		if (dataCollector.getCurrentPose() != null) {
			String  pose= dataCollector.getCurrentPose().getType().toString();
		}

		System.out.println(x+","+y);
		robot.mouseMove(x, y);
		point.setLocation(x, y);

	}



}

