package net.BITF.component;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.BITF.panel.GamePanel;

public class NextButton extends JButton implements ActionListener{

	private GamePanel gamePanel;

	public NextButton(GamePanel gamePanel){
		super("Next");

		this.gamePanel = gamePanel;
		this.addActionListener(this);

		this.setAlignmentY(Component.TOP_ALIGNMENT);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("ButtonClicked");
//		gamePanel.pass();
		gamePanel.answer();
	}
}
