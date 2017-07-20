package net.BITF;

import net.BITF.frame.DebugFrame;
import net.BITF.frame.MainFrame;
import net.BITF.myo.MyoControl;
import net.BITF.util.IFps;


public class Control implements IFps
{
	private MainFrame mainFrame;
	private DebugFrame debugFrame;

	private MyoControl myoControl;

	public Control(){

		System.out.println("DebugMode:" + Main.isDebugMode);

		mainFrame = new MainFrame(Main.stage);
		mainFrame.setVisible(true);

		if(Main.isConnectingMyo){
			myoControl = new MyoControl(Main.myo, Main.hub, mainFrame);
		}

		if(Main.isDebugMode){
			debugFrame = new DebugFrame(mainFrame, myoControl);
			debugFrame.setVisible(true);
		}
	}

	@Override
	public void update() {
		mainFrame.update();

		if(Main.isDebugMode){
			debugFrame.update();
		}

		if(Main.isConnectingMyo){
			myoControl.update();
		}
	}

}
