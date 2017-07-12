package net.BITF.myo;

import net.BITF.Main;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

public class MyoControl {

	private Hub hub;
	private Myo myo;

	private DataCollector dataCollector;

	private int x=600,y=600;//マウス初期位置
	public MyoControl(){
		/*
		 * Myoの初期化
		 */
		myo = Main.myo;
		hub = Main.hub;

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
//		try{
//			Robot r = new Robot();  //インスタンス生成
//			  r.mouseMove(x,y ); //座標(mouseX, mouseY)にカーソルを移動
//			}catch(AWTException e){
//			  e.printStackTrace();
//			}
//
//		if (roll_w < 8 && 4 < roll_w ){ //右
//			x++;                       // 右へ移動
//		}

	}
}
