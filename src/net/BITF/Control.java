package net.BITF;

import net.BITF.frame.MainFrame;
import net.BITF.myo.HelloMyo;
import net.BITF.util.IFps;


public class Control implements IFps
{
	private MainFrame frame;
	private HelloMyo myo;

	public Control(){
		frame = new MainFrame(Main.stage);
		frame.setVisible(true);

		//myo = new HelloMyo();
	}

	public void update() {
		frame.update();
	}

}
