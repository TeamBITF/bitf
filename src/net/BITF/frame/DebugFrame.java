package net.BITF.frame;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import net.BITF.FrameRate;
import net.BITF.panel.GamePanel;

public class DebugFrame extends JFrame{

	private String message;

	private MainFrame mainFrame;
	private JTextPane text;

	public DebugFrame(MainFrame mainFrame){
		this.mainFrame = mainFrame;

		setResizable(false);
		setAlwaysOnTop(true);

		setBounds(0, 0, 200, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		text = new JTextPane();
		add(text);
	}

	public void update(){
		init();

		addText(mainFrame.bitfPanel.toString());

		if (mainFrame.bitfPanel instanceof GamePanel){
			GamePanel panel = (GamePanel) mainFrame.bitfPanel;
			addText(Integer.toString(panel.getTime()) + " / " + Integer.toString(GamePanel.TIME_LIMIT_PER_IMAGE));
		}

		FrameRate.getInstance().count();
		addText(Float.toString(FrameRate.getInstance().getFrameRate()));



		text.setText(message);

	}

	private void init(){
		message = "";
	}

	private void addText(String text){
		message += text + "\n";
	}
}
