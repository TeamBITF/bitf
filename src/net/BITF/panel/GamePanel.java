package net.BITF.panel;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.FlowLayout;
=======
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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

<<<<<<< HEAD
	private AnswerComponent answerComponent;
	private MainComponent mainComponent;
	private StatusComponent statusComponent;

	private JPanel layoutPanelH;
	private JPanel layoutPanelV;

=======
	private AnswerPanel answerPanel;
	private MainPanel testPanel;
	private StatusPanel statusPanel;
	private JComboBox comboBox;
	
//	private JPanel layoutPanelH;
//	private JPanel layoutPanelV;

	private JLabel warp;
	private JLabel stu;
	
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a
	protected Timer timer;
	protected int totalTimeLimit;
	private int time;

	public GamePanel(){

		super();

		/**
		 * 3分
		 */
		totalTimeLimit = 3 * 60;


<<<<<<< HEAD
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);

		this.setLayout(layout);
		//this.setBackground(new Color(0));


		JPanel[] panels = new JPanel[3];
		for(int i = 0; i < panels.length; i++){
			panels[i] = new JPanel();
		}


		answerComponent = new AnswerComponent(this);
		answerComponent.setBounds(0, 0, 300, this.getHeight());
=======
//		FlowLayout layout = new FlowLayout();
//		layout.setAlignment(FlowLayout.LEFT);
//		setLayout(layout);
		setLayout(null);


//		layoutPanelH = new JPanel();
//		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a

//		layoutPanelV = new JPanel();
//		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));

<<<<<<< HEAD
		layoutPanelH = new JPanel();
		layoutPanelH.setLayout(new BoxLayout(layoutPanelH, BoxLayout.Y_AXIS));
		layoutPanelH.setBorder(new EmptyBorder(0, 0, 0, 20));


		layoutPanelV = new JPanel();
		layoutPanelV.setLayout(new BoxLayout(layoutPanelV, BoxLayout.X_AXIS));
		layoutPanelV.setBorder(new LineBorder(new Color(-1, true), 10));
=======
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a

//		answerPanel = new AnswerPanel(this);
//		answerPanel.setBounds(0, 0,this.getWidth() , this.getHeight());
//		answerPanel.setLocation(100,50);
		
		result = 2;

<<<<<<< HEAD
		mainComponent = new MainComponent(result);
		mainComponent.setBounds(0, 48, this.getWidth(), this.getHeight());	//表示サイズを設定

		statusComponent = new StatusComponent(this);
		statusComponent.setBounds(0, 0, statusComponent.getWidth(), statusComponent.getHeight());

		//BoxLayout
		layoutPanelH.add(statusComponent);
		layoutPanelH.add(mainComponent);

		layoutPanelV.add(layoutPanelH);
		layoutPanelV.add(answerComponent);
=======
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
	    combo.setBounds(900, 80, 200,40 );
	    
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


>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a

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
		mainComponent.changeImage();
	}

	public void changeImage(int index){
		mainComponent.changeImage(index);
	}


	@Override
	public int update() {
<<<<<<< HEAD
		mainComponent.updateUI();

=======
		testPanel.updateUI();
	
		
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a
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
<<<<<<< HEAD
				statusComponent.updateUI();
=======
			//	statusPanel.updateUI(); 
>>>>>>> 746b116e5a8beaf58e34e992f4df58d454632c5a
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
