package net.BITF.frame;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import net.BITF.FrameRate;
import net.BITF.Main;
import net.BITF.myo.MyoControl;
import net.BITF.panel.GamePanel;

public class DebugFrame extends JFrame{

	private String message;

	private MainFrame mainFrame;
	private MyoControl myo;

	private JTextPane text;

	public DebugFrame(MainFrame mainFrame, MyoControl myo){
		this.mainFrame = mainFrame;
		this.myo = myo;

		setResizable(false);
		setAlwaysOnTop(true);

		setBounds(0, 0, 500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		text = new JTextPane();
		add(text);
	}

	public void update(){
		init();

		//=========

		addText(mainFrame.bitfPanel.toString());

		if (mainFrame.bitfPanel instanceof GamePanel){
			GamePanel panel = (GamePanel) mainFrame.bitfPanel;
			addText("ImageID:" + Integer.toString(panel.getResult()));
			addText("Left time:" + Integer.toString(panel.getTime()) + " / " + Integer.toString(GamePanel.TIME_LIMIT_PER_IMAGE));
		}

		//=========

		FrameRate.getInstance().count();
		addText(Float.toString(FrameRate.getInstance().getFrameRate()));

//		addText("PanelState:" + (mainFrame.bitfPanel.isLoading() ? "Loading" : "Ready"));

		//==========

		addText("PlayerName:" + mainFrame.userName);

		//==========

		if(Main.isConnectingMyo){
			addText("MyoState:" + ((myo != null) ? "Connected" : "Disconnected"));
			addText(myo.getDataCollector().toString());
		}

		text.setText(message);

	}

	private void init(){
		message = "";
	}

	private void addText(String text){
		message += text + "\n";
	}
}
