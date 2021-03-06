package net.BITF.component.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.BITF.panel.GamePanel;
public class TimerComponent extends JPanel{

	private GamePanel gamePanel;
	public Object BorderLayout;

	public TimerComponent(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

		setOpaque(false);
		setPreferredSize(new Dimension(100, 100));
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(0x80aaaaaa, true));
		g2.fillRect(0, 0, 100, 100);

		g2.setColor(Color.RED);
		g2.fillOval(0, 0, 100, 100);

		final int deg = 360 * gamePanel.getTime() / GamePanel.TIME_LIMIT_PER_IMAGE;

		g2.setColor(Color.WHITE);
		g2.fillArc(0, 0, 100, 100, 90, deg);

		g2.setColor(Color.BLACK);
		g2.drawOval(0, 0, 100, 100);
	}
}
