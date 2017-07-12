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

	}

	public void update(){
		hub.run(1000/20);

		myocon();	//実処理

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
		double pitch_w = dataCollector.getPitchW();
		
		if (roll_w < 8 && 4 < roll_w ){ //右
			x++;                        // 右へ移動
		}
		if (pitch_w < 8 && 4 < pitch_w ){ // 下キーが押されていたら
			y++;  // 下へ移動
			
		}
		if (roll_w > 8 && 12 > roll_w && x > 0){ // 左キーが押されていたら
			x--;                       // 左へ移動
			
		}
		if (pitch_w > 8 && 12 > pitch_w && y > 0){ // 上キーが押されていたら
			y--;                       // 上へ移動
			
		}
		if (roll_w <= 4 && 0 < roll_w ){ // 右キーが押されていたら
			x = x + 2;                       // 右へ移動
		
		}
		if (pitch_w <= 4 && 0 < pitch_w ){ // 下キーが押されていたら
			y = y + 2;                       // 下へ移動
		
		}
		if (roll_w >= 12 && 16 > roll_w && x > 0){ // 左キーが押されていたら
			x -= 2;                       // 左へ移動
		
		}
		if (pitch_w >= 12 && 16 > pitch_w && y > 0 ){ // 上キーが押されていたら
			y -= 2;                       // 上へ移動
			
		}
	}
}
