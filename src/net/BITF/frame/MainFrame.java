package net.BITF.frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import net.BITF.FrameRate;
import net.BITF.panel.BITFPanel;
import net.BITF.panel.EndPanel;
import net.BITF.panel.GamePanel;
import net.BITF.panel.LoadingPanel;
import net.BITF.panel.StartPanel;
import net.BITF.util.ResourceLoader;

public class MainFrame extends JFrame implements Runnable{

	private Thread thread;

	public static int oldStage;
	public static int stage;
	public static String userName;
	public static int score;

	private AudioClip[] bgm;


	protected BITFPanel bitfPanel;
	private LoadingPanel loadingPanel;

	public MainFrame(int stage){

		thread = new Thread(this);

		bgm = new AudioClip[3];
		bgm[0] = Applet.newAudioClip(ResourceLoader.instance.getResource("data/bgm/startbgm.wav"));
		bgm[1] = Applet.newAudioClip(ResourceLoader.instance.getResource("data/bgm/game.wav"));
		bgm[2] = Applet.newAudioClip(ResourceLoader.instance.getResource("data/bgm/ranking.wav"));

		userName = "";
		oldStage = 0;

		setName("net.bitf.GameFrame");
		setTitle("Back Image to the Future ~春の青菜ソースを添えて~");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = env.getMaximumWindowBounds();
		setBounds(rect);

		setLocationRelativeTo(this);

		MainFrame.stage = stage;

		nextStage();

		thread.start();
	}


	public void nextStage(){
		Container pane = getContentPane();

		setBackground(Color.BLACK);

		loadingPanel = new LoadingPanel();
		loadingPanel.setPreferredSize(new Dimension(pane.getWidth(), pane.getHeight()));

		pane.removeAll();
		pane.setBackground(Color.BLACK);

		pane.add(loadingPanel);


		pane.validate();

		System.out.println("stageid:" + stage);

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

		pane.remove(loadingPanel);
		pane.add(bitfPanel);

		for (int i = 0; i < bgm.length; i++){
			bgm[i].stop();
		}

		bgm[stage].loop();


 	}

	public void update(){

		oldStage = stage;
		int newStage = bitfPanel.update();

		if (oldStage != newStage){
			stage = newStage;
			nextStage();
		}

		FrameRate.getInstance().count();
	}

	public BITFPanel getPanel(){
		return bitfPanel;
	}


	@Override
	public void run() {
		while(true){
			if(loadingPanel != null){
				loadingPanel.updateUI();
			}
		}
	}
}
