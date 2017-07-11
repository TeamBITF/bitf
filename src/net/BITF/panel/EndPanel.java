package net.BITF.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import net.BITF.frame.MainFrame;

public class EndPanel extends BITFPanel implements ActionListener  {

	private JLabel utyu;
	private JLabel result;
	private JLabel praise;
	private JLabel first,second,third;
	private JLabel Rank[] = new JLabel[100];
	private JLabel Ranks[]=new JLabel[100];

	public EndPanel(){

		super();

		this.setLayout(null);

		JLabel names = new JLabel(MainFrame.name);
		names.setForeground(Color.red);
		names.setBounds(500,250,200,100);
		names.setFont(new Font("Arial", Font.PLAIN, 40));

		add(names);

		ImageIcon icon = new ImageIcon("resource/data/End/utyu.jpg");
		utyu = new JLabel(icon);
		utyu.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		icon = new ImageIcon("resource/data/End/result.png");
		result= new JLabel(icon);
		result.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		
		icon=new ImageIcon("resource/data/End/1_cong.png");
		praise= new JLabel(icon);
		praise.setBounds(400,00,icon.getIconWidth(),icon.getIconHeight());


		icon = new ImageIcon("resource/data/End/1st.png");
		first = new JLabel(icon);
		first.setBounds(395,260,icon.getIconWidth(),icon.getIconHeight());
		icon = new ImageIcon("resource/data/End/2nd.png");
		second = new JLabel(icon);
		second.setBounds(400,330,icon.getIconWidth(),icon.getIconHeight());
		icon = new ImageIcon("resource/data/End/3rd.png");
		third = new JLabel(icon);
		third.setBounds(395,400,icon.getIconWidth(),icon.getIconHeight());


		ImageIcon btn = new ImageIcon("./img/sample.png");
		JButton bttn = new JButton(btn);
		bttn.setText("タイトルへもどる");
		bttn.setBounds(610, 610, 100,30 );

		add(bttn);
		bttn.setActionCommand("start");
		bttn.addActionListener(this);



		List<Integer> list = new ArrayList<Integer>();
		int tens[]={983,546,323,256,123,100};

		for(int a=0;a<6;a++){
			int temp=tens[a];

			System.out.println(temp);
			int digit;

			for(digit=0;temp>0;digit++,temp/=10){
				list.add(temp % 10);
			}

			JLabel[] label = new JLabel[digit];
			ImageIcon[] icons = new ImageIcon[digit];


			for(int i=0;i<digit;i++){
				icons[i] = new ImageIcon("resource/data/End/Dnumber/no"+list.get(i)+".png");
				label[i]=new JLabel(icons[i]);

				label[i].setBounds(840 - i * 40,265+a*70,icons[i].getIconWidth(),icons[i].getIconHeight());
				add(label[i]);
			}

			list.clear();
		}







		for(int i = 0, a = 0;i <= 2;i++){
			icon=new ImageIcon("resource/data/End/ran"+(i+4)+".png");
			Ranks[i] = new JLabel(icon);
			Ranks[i].setBounds(420,480+a,icon.getIconWidth(),icon.getIconHeight());
			a=a+70;
			add(Ranks[i]);//4.5.6位
		}

		add(first);add(second);add(third);//1.2.3位

		ImageIcon waku=new ImageIcon("resource/data/End/ran_waku.png");
		for(int i = 0, a = 0;i <=5 ;i++){
			Rank[i]= new JLabel(waku);
			Rank[i].setBounds(400,250+a,waku.getIconWidth(),waku.getIconHeight());
			a += 70;
			add(Rank[i]); //マリカみたいな枠
		}
		add(praise);//順位によって変わる褒め言葉
		add(result); 
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
