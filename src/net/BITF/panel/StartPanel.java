package net.BITF.panel;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.BITF.frame.MainFrame;
/**
 * setPreferredSize関数を必ず使ってください<BR>
 */
public class StartPanel extends BITFPanel implements ActionListener{

	/*
	 * メンバ変数
	 */

	private JLabel image;
	private JLabel car;
	private JLabel fire;
	private JLabel title;
	protected static JTextField name;
//	private <型名> <変数名>;
//

	/*
	 * 初期設定
	 */
	public StartPanel(){
		super();

		this.setLayout(null);

		//this.setPreferredSize(new Dimension(1366, 768));

		ImageIcon icon = new ImageIcon("resource/data/Start/fire.gif");
		image = new JLabel(icon);
		image.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		icon = new ImageIcon("resource/data/Start/warp.gif");
		fire = new JLabel(icon);
		fire.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

		icon=new ImageIcon("resource/data/Start/car.png");
		car= new JLabel(icon);
		car.setBounds(300,50,icon.getIconWidth(),icon.getIconHeight());

		icon=new ImageIcon("resource/data/Start/Title.png");
		title= new JLabel(icon);
		title.setBounds(380,20,icon.getIconWidth(),icon.getIconHeight());

		ImageIcon btn1 = new ImageIcon("resource/data/Start/startbutton.png");
		JButton button1 = new JButton(btn1);
	    button1.setBounds(610, 540, 150,60 );
	    button1.setContentAreaFilled(false);
	    button1.setBorderPainted(false);

	    ImageIcon btn2 = new ImageIcon("resource/data/Start/rankingbutton.png");
	    JButton button2 = new JButton(btn2);
	    button2.setBounds(610, 620, 150,60 );
	    button2.setContentAreaFilled(false);
	    button2.setBorderPainted(false);

	    name = new JTextField(15);
	    name.setToolTipText("名前を入力");
	    name.setText("aaaaa");


	    name.setBounds(550,480,250,30);

	    add(name);

	    add(button1);
	    add(button2);
	    add(title);
	    add(car);
	    add(fire);
		fire.add(image);




	    button1.setActionCommand("game");
	    button1.addActionListener(this);
	    button2.setActionCommand("end");
	    button2.addActionListener(this);


	}


	@Override
	public int update() {
		updateUI();
		return nextStage;
	}








	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.name = name.getText();
		if(e.getActionCommand().equals("game")){
			this.nextStage = 1;
		}
		else if(e.getActionCommand().equals("end")){
			this.nextStage = 2;

		}

	}

}



