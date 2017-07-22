package net.BITF.myo;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import net.BITF.frame.MainFrame;
import net.BITF.panel.GamePanel;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {
	private MainFrame mainFrame;

	private Hub hub;
	private Myo myo;

	private Robot robot;
	public static Point point;

	private DataCollector dataCollector;


	public static int y,x;//マウス初期位置
	private float v = 5, s = 3;



	public MyoControl(Myo myo, Hub hub, MainFrame mainFrame){


		/*
		 * Myoの初期化
		 */

		this.mainFrame = mainFrame;
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
		hub.run(1);

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

		if (MainFrame.stage == 1){
			GamePanel panel = (GamePanel) mainFrame.getPanel();

			if (!dataCollector.pullflag){
				if(roll_w <= 8){
					x=(int) (x-(((int)roll_w-8)*s)); //右
				}
					else{
					x=(int) (x-(((int)roll_w-8)*v));//左
				}
					y = (int) (y-(((int)pitch_w - 8)*s));//上下

				if(x >= 880){
				x=875;
				}
				if(x <= 90){
					x=95;
				}
				if(y <= 60){
					y = 65;
				}
				if(y >= 641){
					y=636;
				}
			}
			else{
				if( y >= 70 && y <= 283){
					y=y-(int)pitch_w+8;//上下
				}else if(y < 70){
					y=70;
				}else if(y > 283){
					y=283;
				}
			}

			if(panel.getTime()==0){
				x=683;
				y=384;
				dataCollector.pullflag= false;
			}
		}else if(MainFrame.stage == 0){
			if(roll_w <= 8){
				x=(int) (x-(((int)roll_w-8)*s)); //右
			}
				else{
				x=(int) (x-(((int)roll_w-8)*v));//左
			}
				y = (int) (y-(((int)pitch_w - 8)*s));//上下

			if(x >= 880){
			x=875;
			}
			if(x <= 90){
				x=95;
			}
			if(y <= 60){
				y = 65;
			}
			if(y >= 641){
				y=636;
			}
		}


		if (dataCollector.getCurrentPose() != null) {
			String  pose= dataCollector.getCurrentPose().getType().toString();
		}

//		System.out.println(x+","+y);
		robot.mouseMove(x, y);
		point.setLocation(x, y);

		if (MainFrame.stage != 1){
			dataCollector.pullflag = false;
		}

	}

}

