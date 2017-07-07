package net.BITF.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.BITF.Circle.ListCircle;
import net.BITF.panel.game.AnswerPanel;
import net.BITF.panel.game.StatusPanel;
import net.BITF.panel.game.TestPanel;
public class GamePanel extends BITFPanel implements ActionListener{

	/**
	 * 正解を格納する変数
	 */
	private int result;

	/**
	 * 1枚最大1分
	 */
	public static int TIME_LIMIT_PER_IMAGE = 60;

	private AnswerPanel answerPanel;
	private TestPanel testPanel;
	private StatusPanel statusPanel;

	private JPanel layoutPanelH;
	private JPanel layoutPanelV;

	protected Timer timer;
	protected int totalTimeLimit;
	private int time;

	public GamePanel(){

		super();

		totalTimeLimit = 3 * 60;


		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);

		this.setLayout(layout);

		answerPanel = new AnswerPanel(this);
		answerPanel.setBounds(0, 0, 300, this.getHeight());


		layoutPanelH = new JPanel();
		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));

		layoutPanelV = new JPanel();
		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));

		result = 2;

		testPanel = new TestPanel(result);
		testPanel.setBounds(0, 48, this.getWidth(), this.getHeight());	//表示サイズを設定

		statusPanel = new StatusPanel(this);
		statusPanel.setBounds(0, 0, statusPanel.getWidth(), statusPanel.getHeight());

		//BoxLayout
		layoutPanelH.add(statusPanel);
		layoutPanelH.add(testPanel);

		layoutPanelV.add(layoutPanelH);
		layoutPanelV.add(answerPanel);

		this.add(layoutPanelV);

		init();

		timer = new Timer(1000, this);
		timer.setActionCommand("time");
		timer.start();
	}

	private void init(){
		time = (60 < totalTimeLimit) ? GamePanel.TIME_LIMIT_PER_IMAGE : totalTimeLimit;

		/*
		 * TODO
		 * 画像の初期化処理を組み込む
		 */
	}

	public int getTime(){
		return time;
	}

	public int getResult() {
		return result;
	}

	public void changeImage(){
		testPanel.changeImage();
	}

	public void changeImage(int index){
		testPanel.changeImage(index);
	}


	@Override
	public int update() {
		testPanel.updateUI();
		statusPanel.updateUI();

		ListCircle.getInstance().update();

		return nextStage;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("time")){
			totalTimeLimit--;

			//1枚にかける時間
			if (time > 0){
				time--;
			}
			else {
				System.out.println("Timed out");
				timer.stop();
				init();
			}

			//全体の制限時間
			if (totalTimeLimit <= 0){
				//nextStage = 2;
			}
		}

	}
}
