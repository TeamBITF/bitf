package net.BITF.component.game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.BITF.component.NextButton;
import net.BITF.panel.GamePanel;
import net.BITF.util.ImageManager;

public class AnswerComponent extends JPanel{

	private NextButton pass;
	private JComboBox<String> comboBox;

	private GamePanel gamePanel;

	public AnswerComponent(GamePanel gamePanel){
		this.gamePanel = gamePanel;

		pass = new NextButton(gamePanel);
		comboBox = new JComboBox<String>();

		reset();

	    //comboBox.setPreferredSize(new Dimension(500, 40));
	    comboBox.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));

		add(pass);
		add(comboBox);

	}

	public void reset(){
		ImageManager images = ImageManager.getInstance();

		List<String> list = new ArrayList<String>();
		list.add("選択なし");
		list.addAll(Arrays.asList(images.generateRandomList(gamePanel.getResult())));

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (int i = 0; i < list.size(); i++){
			model.addElement(list.get(i));
		}

		comboBox.setModel(model);
	}

	public String getAnswer(){
		return (String) comboBox.getSelectedItem();
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
}
