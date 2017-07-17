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
import net.BITF.frame.MainFrame;
import net.BITF.util.ImageManager;

public class GamePanel extends BITFPanel implements ActionListener{

	/**
	 * 正解を格納する変数
	 */
	private int result;

	/**
	 * 1枚最大1分
	 * value = 60
	 */
	public static int TIME_LIMIT_PER_IMAGE = 60 * 1000;

	private AnswerComponent answerComponent;
	private MainComponent mainComponent;
	private StatusComponent statusComponent;

	private JPanel layoutPanelH;
	private JPanel layoutPanelV;

	private BufferedImage bg;

	protected Timer timer;
	protected int totalTimeLimit;

	/*
	 * 画像1枚にかける時間
	 */
	private int time;

	public GamePanel(){
		super();

		/**
		 * 3分
		 */
		totalTimeLimit = 3 * 60 * 1000;


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
		timer = new Timer(1, this);
		timer.setActionCommand("time");
		timer.start();

		setLoading(false);
	}

	private void init(){
		//TIME_LIMIT_PER_IMAGEで初期化
		time = GamePanel.TIME_LIMIT_PER_IMAGE;

	}

	public void pass(){
		changeImage();
	}

	public void answer(){

		ImageManager manager = ImageManager.getInstance();

		/*
		 * answerComponentから現在の選択を取得
		 */
		String answer = answerComponent.getAnswer();

		/*
		 * 正誤の判定
		 *
		 * あってたらMainFrame.scoreの加算
		 * 画像の切り替え
		 *
		 * 違ったら減点
		 * 2回まで間違えられる
		 * ここで画像は切り替えない
		 */

		if (answer.equals("選択なし")){
			//何もしない
		}
		else if (answer.equals(manager.getImageFromList(result).getName())){
			//正解
			System.out.println("正解");
			MainFrame.score += time / 1000;
			changeImage();

		}
		else {
			//はずれ
			System.out.println("不正解");
			MainFrame.score -= 5;
		}
	}

	public int getTime(){
		return time;
	}

	public int getResult() {
		return result;
	}

	public void changeImage(){
		changeImage(-1);
	}

	public void changeImage(int index){
		answerComponent.reset();
		ListCircle.getInstance().removeAllCircle();
		result = mainComponent.changeImage(index);
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

				//ペナルティ処理追加する?

				init();
			}

			//全体の制限時間
			if (totalTimeLimit <= 0){//とりま20秒位で遷移
				System.out.println("Timed out");
				timer.stop();

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
