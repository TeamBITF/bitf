package net.BITF.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.BITF.util.ResourceLoader;

public class LoadingPanel extends JPanel{
	private ImageIcon loading;
	private ImageIcon backd;
	public LoadingPanel(){
		
		//setLayout(null);

		

		backd = new ImageIcon(ResourceLoader.instance.getResource("data/load/黒.png"));
		loading = new ImageIcon(ResourceLoader.instance.getResource("data/load/loading.gif"));

		JLabel load = new JLabel(loading);
		JLabel back = new JLabel(backd);
		

		
		
		//this.add(back);
		this.add(load);
		//updateUI();
		
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
//		g2.drawRect(0, 0, loading.getIconWidth(), loading.getIconHeight());
		g2.drawRect(0, 0, getWidth(), getHeight());
	}
	
}
