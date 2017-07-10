package net.BITF.frame;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.BITF.FrameRate;
import net.BITF.Main;
import net.BITF.panel.BITFPanel;
import net.BITF.panel.EndPanel;
import net.BITF.panel.GamePanel;
import net.BITF.panel.StartPanel;

public class MainFrame extends JFrame{

	public static final MainFrame instance = new MainFrame();

	public static int stage;
	public static String name;

	private BITFPanel bitfPanel;

	public MainFrame(){

		stage = 0;

		//チート用
		bitfPanel = new GamePanel();

		if (Main.args.length > 0){
			if(Main.args[0].equals("title")){
				stage = 0;
			}
			else if (Main.args[0].equals("game")){
				stage = 1;
			}
			else if (Main.args[0].equals("end")){
				stage = 2;
			}
		}

		init();

	}

	public MainFrame(int stage){
		MainFrame.stage = stage;

		init();
	}

	private void init(){
		name = "";

		setName("net.bitf.GameFrame");
		setTitle("Back Image to the Future ~春の青菜ソースを添えて~");

		setExtendedState(JFrame.MAXIMIZED_BOTH);


		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = env.getMaximumWindowBounds();
		setBounds(rect);

		setLocationRelativeTo(this);

		nextStage();
	}

	public void nextStage(){
		Container pane = getContentPane();

//		init();
		pane.removeAll();
		//validate();


		System.out.println(stage);

		switch(stage){
		case 0:
			System.out.println("Title");
			bitfPanel = new StartPanel();
			break;
		case 1:
			System.out.println("Game");
			bitfPanel = new GamePanel();
			break;
		case 2:
			System.out.println("End");
			bitfPanel = new EndPanel();
			break;


		default:
			stage = 0;
			bitfPanel = new StartPanel();
		}


		System.out.println(bitfPanel.toString());


		pane.add(bitfPanel);



 	}

	public void update(){
		int oldStage = stage;
		int newStage = bitfPanel.update();

		if (oldStage != newStage){
			stage = newStage;
			nextStage();
		}

		FrameRate.getInstance().count();
	}


}
