package net.BITF.panel;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.BITF.Circle.ListCircle;
import net.BITF.component.game.AnswerComponent;
import net.BITF.component.game.MainComponent;
import net.BITF.component.game.StatusComponent;

public class GamePanel extends BITFPanel implements ActionListener{
	/**
	 * 正解を格納する変数
	 */
	private int result;

	/**
	 * 1枚最大1分
	 * value = 60
	 */
	public static int TIME_LIMIT_PER_IMAGE = 60;

	private AnswerComponent answerComponent;
	private MainComponent mainComponent;
	private StatusComponent statusComponent;

	private JPanel layoutPanelH;
	private JPanel layoutPanelV;

	private BufferedImage bg;

	protected Timer timer;
	protected int totalTimeLimit;
	private int time;

	public GamePanel(){

		super();

		/**
		 * 3分
		 */
		totalTimeLimit = 3 * 60;


		/* ================================================================
		 * 初期化
		 * ================================================================*/

		/*
		 * GamePanelの初期化
		 */

		//レイアウト
		setLayout(new FlowLayout(FlowLayout.CENTER));

		/*
		 * Layout指定したパネルの設定
		 */

		//縦
		layoutPanelH = new JPanel();
		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));
		layoutPanelH.setOpaque(false);
		//layoutPanelH.setBorder(new EmptyBorder(0, 0, 0, 20));

		//横
		layoutPanelV = new JPanel();
		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));
		layoutPanelV.setOpaque(false);
		//layoutPanelV.setBorder(new LineBorder(new Color(-1, true), 10));

		//Components
		result = 2;
		answerComponent = new AnswerComponent(this);
		mainComponent = new MainComponent(result);
		statusComponent = new StatusComponent(this);


		/* ================================================================
		 * Componentの配置
		 * ================================================================*/

		layoutPanelH.add(statusComponent);

		layoutPanelV.add(mainComponent);
		layoutPanelV.add(answerComponent);

		layoutPanelH.add(layoutPanelV);

		add(layoutPanelH);


		/*
		 * 背景画像の読み込み
		 */

		try {
			bg = ImageIO.read(new File("resource/data/Game/雷.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//その他
		init();

		/* ================================================================
		 * Timerの設定
		 * ================================================================*/
		timer = new Timer(1000, this);
		timer.setActionCommand("time");
		timer.start();
	}

	private void init(){
		time = (60 < totalTimeLimit) ? GamePanel.TIME_LIMIT_PER_IMAGE : totalTimeLimit;
	}

	public int getTime(){
		return time;
	}

	public int getResult() {
		return result;
	}

	public void changeImage(){
		mainComponent.changeImage();
	}

	public void changeImage(int index){
		mainComponent.changeImage(index);
	}


	@Override
	public int update() {
		mainComponent.updateUI();

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
				statusComponent.updateUI();
			}
			else {
				System.out.println("Timed out");
				timer.stop();
				init();
			}

			//全体の制限時間
			if (totalTimeLimit <= 0){
				nextStage = 2;
			}
		}
	}


	@Override
    public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(bg, 0, 0, this);

	}
}
