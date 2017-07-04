package net.BITF.frame;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.BITF.FrameRate;
import net.BITF.Main;
import net.BITF.panel.BITFPanel;
import net.BITF.panel.GamePanel;
import net.BITF.panel.StartPanel;

public class MainFrame extends JFrame{

	public int stage;
	private BITFPanel bitfPanel;

	public MainFrame(){

		stage = 0;

		if (Main.args.length > 0){
			if(Main.args[0].equals("title")){
				stage = 0;
			}
			else if (Main.args[0].equals("game")){
				stage = 1;
			}
		}

		setName("net.bitf.GameFrame");
		setTitle("Back Image to the Future ~春の青菜ソースを添えて~");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = env.getMaximumWindowBounds();
		setBounds(rect);

		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		nextStage();

	}

	public void nextStage(){
		Container pane = getContentPane();

		switch(stage){
		case 0:
			System.out.println("Title");
			bitfPanel = new StartPanel();
			break;

		default:
			bitfPanel = new GamePanel();
		}

//		if(stage == 0){
//			bitfPanel = new StartPanel();
//		}
//		else if(stage == 1){
//			bitfPanel = new GamePanel();
//		}



		pane.add(bitfPanel);
	}

	public void update(){
		bitfPanel.update();

		FrameRate.getInstance().count();
	}


}
