﻿package net.BITF.panel.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

	private int persentage = 100;

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

	public StatusPanel(){
		statusGauge = new BufferedImage[3];
		try{
			statusFrame = ImageIO.read(new File("data/img/gauge_frame.gif"));

			for (int i = 0; i < 3; i++){
				statusGauge[i] = ImageIO.read(new File("data/img/gauge_" + Integer.toString(i) + ".jpg"));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

//		System.out.println(statusFrame.getWidth());
//		System.out.println(statusFrame.getHeight());
		setPreferredSize(new Dimension(statusFrame.getWidth(), statusFrame.getHeight()));
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		int w = statusGauge[0].getWidth();
		int h = statusGauge[0].getHeight();
		for (int i = 0; i < 38; i++){

			g2.drawImage(statusGauge[0], i * w + 5, 5, this);
		}

		g2.drawImage(statusFrame, 0, 0, this);

		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

	}
}