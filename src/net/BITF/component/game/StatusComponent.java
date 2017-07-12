package net.BITF.component.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.BITF.panel.GamePanel;

public class StatusComponent extends JPanel {

	private GamePanel gamePanel;

	/**
	 * 内側のサイズ
	 *
	 */
	private BufferedImage statusFrame;

	/**
	 * args<br>
	 * 0 : 緑<br>
	 * 1 : 黄<br>
	 * 2 : 赤
	 */
	private BufferedImage[] statusGauge;

	public StatusComponent(GamePanel gamePanel){
		this.gamePanel = gamePanel;

		//透明
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(new Color(0xFF000000, true));

		ImageIcon image = new ImageIcon("resource/data/Game/gauge_frame.gif");
		JLabel frame = new JLabel(image);

//
//		statusGauge = new BufferedImage[3];
//		try{
//			statusFrame = ImageIO.read(new File("resource/data/Game/gauge_frame.gif"));
//
//			for (int i = 0; i < 3; i++){
//				statusGauge[i] = ImageIO.read(new File("resource/data/Game/gauge_" + Integer.toString(i) + ".jpg"));
//			}
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}

		add(frame);
		//setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
	}

//	@Override
//	public void paintComponent(Graphics g){
//		Graphics2D g2 = (Graphics2D) g;
//
//		g2.setColor(Color.WHITE);
//		g2.drawRect(0, 0, getWidth(), getHeight());
//
//		int w = statusGauge[0].getWidth();
//		int h = statusGauge[0].getHeight();
//
//		int count = (int) (38 * getPersentage());
//
//		for (int i = 0; i < count; i++){
//			g2.drawImage(statusGauge[0], i * w + 4, 4, this);
//		}
//
//		g2.drawImage(statusFrame, 0, 0, this);
//
////		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
//
//	}

	private double getPersentage(){
		return (double) gamePanel.getTime() / GamePanel.TIME_LIMIT_PER_IMAGE;
	}

}
