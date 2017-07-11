package net.BITF.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Timer;

import net.BITF.Circle.ListCircle;
import net.BITF.panel.game.AnswerPanel;
import net.BITF.panel.game.MainPanel;
import net.BITF.panel.game.StatusPanel;
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

	private AnswerPanel answerPanel;
	private MainPanel testPanel;
	private StatusPanel statusPanel;
	private JComboBox comboBox;
	
//	private JPanel layoutPanelH;
//	private JPanel layoutPanelV;

	private JLabel warp;
	private JLabel stu;
	
	protected Timer timer;
	protected int totalTimeLimit;
	private int time;

	public GamePanel(){

		super();

		/**
		 * 3分
		 */
		totalTimeLimit = 3 * 60;


//		FlowLayout layout = new FlowLayout();
//		layout.setAlignment(FlowLayout.LEFT);
//		setLayout(layout);
		setLayout(null);


//		layoutPanelH = new JPanel();
//		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));

//		layoutPanelV = new JPanel();
//		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));


//		answerPanel = new AnswerPanel(this);
//		answerPanel.setBounds(0, 0,this.getWidth() , this.getHeight());
//		answerPanel.setLocation(100,50);
		
		result = 2;

		testPanel = new MainPanel(result);
		testPanel.setLocation(10,80);
		
//		statusPanel = new StatusPanel(this);
//		statusPanel.setBounds(0, 0, statusPanel.getWidth(), statusPanel.getHeight());
//		statusPanel.setLocation(0,0);
		
		ImageIcon icon = new ImageIcon("resource/data/Game/雷.jpg");
		warp = new JLabel(icon);
		warp.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		ImageIcon btn1 = new ImageIcon("resource/data/Start/startbutton.png");
		JButton button1 = new JButton(btn1);
	    button1.setBounds(900, 540, 150,60 );
	    
	    JComboBox combo = new JComboBox();
	    combo.setBounds(900, 80, 300,40 );
	    
	    ImageIcon icon2 =new ImageIcon("resource/data/Game/gauge_frame.gif");
	    stu = new JLabel(icon2);
	    stu.setBounds(10, 10, icon2.getIconWidth(),icon2.getIconHeight() );
	    
	    
		
		//BoxLayout
//		layoutPanelH.add(statusPanel);
//		layoutPanelH.add(testPanel);
//
//		layoutPanelV.add(layoutPanelH);
//		layoutPanelV.add(answerPanel);

//		this.add(layoutPanelV);

		
		add(testPanel);
		
//		add(answerPanel);
//		add(statusPanel);
		add(button1);
		add(combo);
		add(stu);
		add(warp);



		init();

		//1秒で更新
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
			//	statusPanel.updateUI(); 
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
}
