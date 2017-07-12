package net.BITF.myo;

import java.awt.AWTException;
import java.awt.Robot;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private DataCollector dataCollector;

	private int x=600,y=600;//マウス初期位置
	public MyoControl(Myo myo, Hub hub){
		/*
		 * Myoの初期化
		 */

		System.out.println("MyoControll:" + myo);

		this.myo = myo;
		this.hub = hub;

		dataCollector = new DataCollector();
		hub.addListener(dataCollector);


		myocon(); //実処理
	}

	public void update(){
		hub.run(1000/20);
	}
	public DataCollector getDataCollector(){
		return dataCollector;
	}
	public void myocon(){
		try{
			Robot r = new Robot();  //インスタンス生成
			  r.mouseMove(x,y ); //座標(mouseX, mouseY)にカーソルを移動
			}catch(AWTException e){
			  e.printStackTrace();
			}

		double roll_w = dataCollector.getRollW();

		if (roll_w < 8 && 4 < roll_w ){ //右
			x++;                        // 右へ移動
			System.out.println(x);
		}

	}
}
