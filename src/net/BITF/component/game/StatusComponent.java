package net.BITF.component.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.BITF.panel.GamePanel;
import net.BITF.util.ResourceLoader;

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

		//ゲージの画像読み込み
		statusGauge = new BufferedImage[3];
		try{
			statusFrame = ImageIO.read(ResourceLoader.instance.getResource("data/Game/gauge_frame.gif"));

			for (int i = 0; i < 3; i++){
				statusGauge[i] = ImageIO.read(ResourceLoader.instance.getResource("data/Game/gauge_" + Integer.toString(i) + ".jpg"));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(statusFrame.getWidth(), statusFrame.getHeight()));
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.drawRect(0, 0, getWidth(), getHeight());

		int w = statusGauge[0].getWidth();

		int count = (int) (57 * getPersentage());
		//int count = (int) (57);

		for (int i = 0; i < count; i++){
			g2.drawImage(statusGauge[0], i * w + 5 , 3, this);
		}

		g2.drawImage(statusFrame, 0, 0, this);
	}

	private double getPersentage(){
		return (double) gamePanel.getTime() / GamePanel.TIME_LIMIT_PER_IMAGE;
	}

}
