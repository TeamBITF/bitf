/**
 * 参考:https://www.javadrive.jp/tutorial/
 */

package net.BITF.panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * setPreferredSize関数を必ず使ってください<BR>
 */
public class StartPanel extends BITFPanel{

	/*
	 * メンバ変数
	 */
	private JLabel image;
	private JLabel car;
//	private <型名> <変数名>;
//

	/*
	 * 初期設定
	 */
	public StartPanel(){
		image = new JLabel(new ImageIcon("Z:/0楠祭　鍵班/startgamen/warp.gif"));
		image.setBounds(0,0,image.getWidth(),image.getHeight());
		car= new JLabel(new ImageIcon("Z:/0楠祭　鍵班/startgamen/bitf_derorian.jpg"));
		car.setBounds(300,0,car.getWidth(),car.getHeight());

		//this.drawImage(image, 10, 10, this);
		//this.add(add(image.add(car)));

		//add(new JButton("ぼたん"));
		add(image);

	}


	@Override	public boolean update() {
		// TODO 自動生成されたメソッド・スタブ



		return false;
	}

	//ココはいじらない
//	public static void main(String[] args){
//		StartPanel startPanel = new StartPanel();
//
//		JFrame frame = new JFrame();
//
//		frame.setName("net.bitf.GameFrame");
//		frame.setTitle("Back Image to the Future ~春の青菜ソースを添えて~");
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		Container pane = frame.getContentPane();
//		pane.add(startPanel);
//
//		frame.setVisible(true);
//
//		while(true){
//			startPanel.update();
//		}
//	}
}
