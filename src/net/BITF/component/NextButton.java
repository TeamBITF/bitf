package net.BITF.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.BITF.Circle.ListCircle;
import net.BITF.panel.GamePanel;

public class NextButton extends JButton implements ActionListener{

	public NextButton(){
		super("Next");

		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("ButtonClicked");

		ListCircle.getInstance().removeAllCircle();
		GamePanel.changeImage();
	}
}
