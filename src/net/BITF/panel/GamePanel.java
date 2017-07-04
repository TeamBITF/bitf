package net.BITF.panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.BITF.Circle.ListCircle;
import net.BITF.panel.game.AnswerPanel;
import net.BITF.panel.game.StatusPanel;
import net.BITF.panel.game.TestPanel;

public class GamePanel extends BITFPanel{

	/**
	 * 正解を格納する変数
	 */
	private static int result;

	private static AnswerPanel answerPanel;
	private static TestPanel testPanel;
	private static StatusPanel statusPanel;

	private static JPanel layoutPanelH;
	private static JPanel layoutPanelV;

	public GamePanel(){
		super();

		answerPanel = new AnswerPanel();

		layoutPanelH = new JPanel();
		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));

		layoutPanelV = new JPanel();
		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));

		result = 2;

		testPanel = new TestPanel(result);
		testPanel.setBounds(0, 48, this.getWidth(), this.getHeight());	//表示サイズを設定

		statusPanel = new StatusPanel();
		statusPanel.setBounds(0, 0, statusPanel.getWidth(), statusPanel.getHeight());

		//BoxLayout
		layoutPanelH.add(statusPanel);
		layoutPanelH.add(testPanel);

		layoutPanelV.add(layoutPanelH);
		layoutPanelV.add(answerPanel);

		this.add(layoutPanelV);
	}

	public static int getResult() {
		return result;
	}

	public static void changeImage(){
		testPanel.changeImage();
	}

	public static void changeImage(int index){
		testPanel.changeImage(index);
	}


	@Override
	public boolean update() {
		testPanel.updateUI();
		ListCircle.getInstance().update();

		return false;

	}
}
