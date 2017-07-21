package net.BITF.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.BITF.util.ResourceLoader;

public class LoadingPanel extends JPanel implements Runnable{
	private Thread thread;
	private int count;

	private BITFPanel bitfPanel;

	private ImageIcon bg;
	private ImageIcon loading;
	private ImageIcon dot;

	public LoadingPanel(){




		loading = new ImageIcon(ResourceLoader.instance.getResource("data/load/loadingend.gif"));
		 JLabel load = new JLabel(loading);
		 load.setLocation(0, 0);
		add(load);


	}

	public void setPanelInfo(BITFPanel bitfPanel){
		this.bitfPanel = bitfPanel;
	}

	@Override
	public void run() {
		while (thread != null){
			if (count < 4){
				count++;
			}
			else {
				count = 0;
			}

			updateUI();
		}
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	}
}
