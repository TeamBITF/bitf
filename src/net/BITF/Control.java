package net.BITF;

import net.BITF.frame.DebugFrame;
import net.BITF.frame.MainFrame;
import net.BITF.myo.MyoControl;
import net.BITF.util.IFps;


public class Control implements IFps
{
	private MainFrame mainFrame;
	private DebugFrame debugFrame;

	private MyoControl myo;

	public Control(){

		System.out.println("DebugMode:" + Main.isDebugMode);

		mainFrame = new MainFrame(Main.stage);
		mainFrame.setVisible(true);

		if(Main.isConnectingMyo){
			myo = new MyoControl();
		}

		if(Main.isDebugMode){
			debugFrame = new DebugFrame(mainFrame, myo);
			debugFrame.setVisible(true);
		}
	}

	public void update() {
		mainFrame.update();

		if(Main.isDebugMode){
			debugFrame.update();
		}

		if(Main.isConnectingMyo){
			myo.update();
		}
	}

}
