package net.BITF.panel;

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

import net.BITF.frame.MainFrame;
import net.BITF.util.ResourceLoader;
import net.BITF.util.SqlManager;

public class EndPanel extends BITFPanel implements ActionListener  {

	private JLabel utyu;
	private JLabel results;
	private JLabel praise;
	private JLabel first,second,third;
	private JLabel Rank[] = new JLabel[100];
	private JLabel Ranks[]=new JLabel[100];

	public EndPanel(){

		super();

		this.setLayout(null);

		ImageIcon icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/utyu.jpg"));
		utyu = new JLabel(icon);
		utyu.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		icon = new ImageIcon(ResourceLoader.instance.getResource("data/End/result.png"));
		results= new JLabel(icon);
		results.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		icon=new ImageIcon(ResourceLoader.instance.getResource("data/End/1_cong.png"));
		praise= new JLabel(icon);
		praise.setBounds(400,00,icon.getIconWidth(),icon.getIconHeight());


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
		ArrayList<Integer> scores = new ArrayList<>();

		//SQLから点数を取得
		try {
			Statement state = sql.init();

			//直前の画面がゲーム画面だった場合
			if (MainFrame.oldStage == 1){
				state.executeUpdate("INSERT INTO ScoreBoard VALUES ('" + MainFrame.userName + "'," + MainFrame.score + ")");
			}

			ResultSet select = state.executeQuery("select name, score from ScoreBoard order by score desc");

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

		for(int a = 0; a < scores.size(); a++){
			int temp = scores.get(a);

			int digit;

			ArrayList<Integer> list = new ArrayList<Integer>();
			for(digit=0; temp > 0; digit++, temp/=10){
				list.add(temp % 10);
			}

			JLabel[] label = new JLabel[digit];
			ImageIcon[] icons = new ImageIcon[digit];


			for(int i=0; i < digit; i++){
				icons[i] = new ImageIcon(ResourceLoader.instance.getResource("data/End/Dnumber/no"+list.get(i)+".png"));
				label[i]=new JLabel(icons[i]);

				label[i].setBounds(840 - i * 40,265+a*70,icons[i].getIconWidth(),icons[i].getIconHeight());
				add(label[i]);
			}

			list.clear();


			setLoading(false);
		}

		for(int i = 0, a = 0;i <= 2;i++){
			icon=new ImageIcon(ResourceLoader.instance.getResource("data/End/ran"+(i+4)+".png"));
			Ranks[i] = new JLabel(icon);
			Ranks[i].setBounds(420,480+a,icon.getIconWidth(),icon.getIconHeight());
			a = a + 70;
			add(Ranks[i]);//4.5.6位
		}

		add(first);
		add(second);
		add(third);//1.2.3位

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
		add(praise);//順位によって変わる褒め言葉
		add(results);
		add(utyu); //背景



	}






	@Override
	public int update() {
		// TODO 自動生成されたメソッド・スタブ
		updateUI();
		return nextStage;
	}






	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

		if(e.getActionCommand().equals("start")){
			this.nextStage = 0;
		}
	}

}
