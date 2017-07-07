package net.BITF.panel.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.BITF.component.NextButton;
import net.BITF.panel.GamePanel;
import net.BITF.util.ImageManager;

public class AnswerPanel extends JPanel{

	private NextButton pass;
	private JComboBox comboBox;
	private DefaultComboBoxModel model;

	public AnswerPanel(GamePanel gamePanel){

		ImageManager images = ImageManager.getInstance();
		String[] strings = { "選択なし" };

		List<String> list = new ArrayList<String>();
		list.add("選択なし");

		String[] nameList = images.getImageFromList(gamePanel.getResult()).getNameList();
		for (int i = 0; i < nameList.length; i++){
			list.add(images.getImageFromList(gamePanel.getResult()).getName(i));
		}

		//strings += images.getImageFromList(GameFrame.getResult()).getNameList();


		model = new DefaultComboBoxModel(list.toArray());

		pass = new NextButton(gamePanel);
		comboBox = new JComboBox(model);
	    comboBox.setPreferredSize(new Dimension(200, 40));
	    comboBox.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));

		add(pass);
		add(comboBox);

	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
}
