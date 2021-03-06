package net.BITF.panel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import net.BITF.Main;
import net.BITF.frame.MainFrame;
import net.BITF.util.ResourceLoader;
import net.BITF.util.SqlManager;

public class EndPanel extends BITFPanel implements ActionListener  {

	private int yourScore;
	private AudioClip clip;

	public EndPanel(){

		super();

		if(Main.isDebugMode){

			if (MainFrame.oldStage != 1){
				MainFrame.oldStage = 1;
				MainFrame.score = 100;
			}

		}

		setLayout(null);
		yourScore = MainFrame.score;

		ImageIcon icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/utyu.jpg"));
		JLabel utyu = new JLabel(icon);
		utyu.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());


		icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/result.png"));
		JLabel results = new JLabel(icon);
		results.setBounds(-40, 0,icon.getIconWidth(),icon.getIconHeight());


		JLabel first, second, third;

		icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/1st.png"));
		first = new JLabel(icon);
		first.setBounds(395,260,icon.getIconWidth(),icon.getIconHeight());
		icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/2nd.png"));
		second = new JLabel(icon);
		second.setBounds(400,330,icon.getIconWidth(),icon.getIconHeight());
		icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/3rd.png"));
		third = new JLabel(icon);
		third.setBounds(395,400,icon.getIconWidth(),icon.getIconHeight());


//		ImageIcon btn = new ImageIcon("./img/sample.png");
		JButton bttn = new JButton();
		bttn.setText("タイトルへもどる");
		bttn.setBounds(900, 610, 100,30 );

		add(bttn);
		bttn.setActionCommand("start");
		bttn.addActionListener(this);


		SqlManager sql = new SqlManager();

		ArrayList<String> names = new ArrayList<>();
		ArrayList<Integer> scores = new ArrayList<Integer>();

		//SQLから点数を取得
		try {

			String device = (Main.isConnectingMyo) ? "myo" : "mouse";
			Statement state = sql.init();

			//直前の画面がゲーム画面だった場合
			if (MainFrame.oldStage == 1){

				String name = MainFrame.userName;


				if (name.equals("")){
					name = "noname";
				}

				if (!name.equals("_demo")){

					state.executeUpdate("INSERT INTO ScoreBoard VALUES ('" + name + "'," + MainFrame.score + ",'" + device +"')");
				}

				MainFrame.userName = "";
			}

			ResultSet select = state.executeQuery("SELECT name, score FROM ScoreBoard WHERE device = '" + device + "' ORDER BY score DESC");

			while (select.next()){
				names.add(select.getString("name"));
				scores.add(select.getInt("score"));
			}

			state.close();
			select.close();

			System.out.println("SQL End");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(MainFrame.oldStage == 1){

			int rank;
			for(rank = 0; rank < scores.size(); rank++){
				if(MainFrame.score == scores.get(rank)){//ランキングと比べてる
					break;
				}
			}





			ImageIcon comment;
			if (rank < 3){
				comment = new ImageIcon(ResourceLoader.instance.getResource("data/end/res/res"+ (rank + 1) +".png"));
				clip = Applet.newAudioClip(ResourceLoader.instance.getResource("data/se/終わったら"+(rank+1)+"位.wav"));
			}
			else if (rank >= 6){
				comment = new ImageIcon(ResourceLoader.instance.getResource("data/end/res/res5.png"));
				clip = Applet.newAudioClip(ResourceLoader.instance.getResource("data/se/ランキングに入れなかったら.wav"));
			}
			else{
				comment = new ImageIcon(ResourceLoader.instance.getResource("data/end/res/res4.png"));
				clip = Applet.newAudioClip(ResourceLoader.instance.getResource("data/se/パララッパラ.wav"));
			}

			JLabel commentLabel = new JLabel(comment);
			commentLabel.setBounds(400, 110, comment.getIconWidth(), comment.getIconHeight());
			dispYourScore(rank + 1);
			add(commentLabel);


		}

		MainFrame.score = 0;

		for(int a = 0; a < 6 && a < scores.size(); a++){
			int temp = scores.get(a);

			int digit;

			ArrayList<Integer> list = new ArrayList<Integer>();
			for(digit=0; temp > 0; digit++, temp/=10){
				list.add(temp % 10);
			}

			JLabel[] label = new JLabel[digit];
			ImageIcon[] icons = new ImageIcon[digit];


			for(int i = 0; i < digit; i++){
				icons[i] = new ImageIcon(ResourceLoader.instance.getResource("data/End/Dnumber/no"+list.get(i)+".png"));
				label[i]=new JLabel(icons[i]);

				label[i].setBounds(840 - i * 40,265+a*70,icons[i].getIconWidth(),icons[i].getIconHeight());
				add(label[i]);
			}

			list.clear();

			setLoading(false);
		}

		JLabel ranks[] = new JLabel[3];
		for(int i = 0, a = 0; i < 3; i++){
			icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/ran"+(i + 4)+".png"));
			ranks[i] = new JLabel(icon);
			ranks[i].setBounds(420,480+a,icon.getIconWidth(),icon.getIconHeight());
			a = a + 70;
			add(ranks[i]);//4.5.6位
		}

		add(first);
		add(second);
		add(third);//1.2.3位


		JLabel Rank[] = new JLabel[6];
		ImageIcon waku=new ImageIcon(ResourceLoader.instance.getResource("data/End/ran_waku.png"));
		for(int i = 0, a = 0; i < 6 ;i++, a += 70){
			Rank[i]= new JLabel(waku);
			Rank[i].setBounds(400,250+a,waku.getIconWidth(),waku.getIconHeight());

			if (i < names.size()){
				JLabel label = new JLabel(names.get(i));
				label.setForeground(Color.red);
				label.setBounds(500,250 + a, 200, 100);

				//TODO フォント
				label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));

				add(label);
			}

			add(Rank[i]); //マリカみたいな枠
		}




		//add(praise);//順位によって変わる褒め言葉
		add(results);
		add(utyu); //背景
		validate();

		if (clip != null){
			clip.play();
		}

	}

	private void dispYourScore(int rank){
		JLabel[] label = new JLabel[2];
		label[0] = new JLabel("Your Score:" + Integer.toString(yourScore));
		label[1] = new JLabel("rank:" + Integer.toString(rank));
		for (int i = 0; i < label.length; i++){
			label[i].setForeground(Color.WHITE);
			label[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
			label[i].setBounds(1000, 100, 16 * 14, 600 + i * 60);

			add(label[i]);
		}
		System.out.println("score:" + yourScore);



	}


	@Override
	public int update() {
		updateUI();
		return nextStage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start")){
			this.nextStage = 0;
		}
	}

}
