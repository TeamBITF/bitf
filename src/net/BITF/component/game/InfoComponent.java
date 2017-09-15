package net.BITF.component.game;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.BITF.Control;
import net.BITF.Main;
import net.BITF.frame.MainFrame;

public class InfoComponent extends JPanel{

	private JLabel name;
	private JLabel score;
	private JLabel chain;
	private JLabel pose;

	public InfoComponent() {
//		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);

		name = new JLabel("Name : " + MainFrame.userName);
		name.setHorizontalAlignment(JLabel.LEFT);
		add(name);

		score = new JLabel("Score : " + Integer.toString(MainFrame.score));
		score.setHorizontalAlignment(JLabel.LEFT);
		add(score);

		chain = new JLabel("Chain : " + Integer.toString(MainFrame.continuous));
		chain.setHorizontalAlignment(JLabel.LEFT);
		add(chain);

		if (Main.isConnectingMyo){
			pose = new JLabel("Pose : " + Control.myoControl.getDataCollector().getCurrentPose());
			add(pose);
		}


	}

	public void update(){
		score.setText("Score : " + Integer.toString(MainFrame.score));
		chain.setText("Chain : " + Integer.toString(MainFrame.continuous));

		if (Main.isConnectingMyo){
			pose.setText("Pose : " + Control.myoControl.getDataCollector().getCurrentPose());
		}
	}
}
