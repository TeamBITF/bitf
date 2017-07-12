package net.BITF;

import net.BITF.frame.DebugFrame;
import net.BITF.frame.MainFrame;
import net.BITF.myo.HelloMyo;
import net.BITF.util.IFps;


public class Control implements IFps
{
	private MainFrame mainFrame;
	private DebugFrame debugFrame;

	private HelloMyo myo;

	public Control(){

		System.out.println("DebugMode:" + Main.isDebugMode);

		mainFrame = new MainFrame(Main.stage);
		mainFrame.setVisible(true);

		if(Main.isDebugMode){
			debugFrame = new DebugFrame(mainFrame);
			debugFrame.setVisible(true);
		}

		//myo = new HelloMyo();
	}

	public void update() {
		mainFrame.update();

		if(Main.isDebugMode){
			debugFrame.update();
		}
	}

}
