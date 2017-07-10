package net.BITF;

import net.BITF.frame.MainFrame;
import net.BITF.util.IFps;


public class Control implements IFps
{

	private MainFrame frame;

	public Control(){
		frame = new MainFrame(Main.stage);
		frame.setVisible(true);
	}

	public void update() {
		frame.update();
	}

}
