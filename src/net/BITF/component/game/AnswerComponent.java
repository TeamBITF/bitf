package net.BITF.component.game;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.BITF.Main;
import net.BITF.component.NextButton;
import net.BITF.panel.GamePanel;
import net.BITF.util.ImageManager;

public class AnswerComponent extends JPanel{

	private NextButton pass;
	private JList<String> answerList;

	private GamePanel gamePanel;
//	private List<JPanel> answerList;

	public AnswerComponent(GamePanel gamePanel){
		this.gamePanel = gamePanel;

		setLayout(new FlowLayout(FlowLayout.LEFT));
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pass = new NextButton(gamePanel);

		answerList = new JList<>();

		answerList.setAlignmentY(Component.TOP_ALIGNMENT);
		answerList.setAlignmentX(Component.LEFT_ALIGNMENT);

		answerList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		answerList.setAlignmentY(Component.TOP_ALIGNMENT);
		answerList.setAlignmentX(Component.LEFT_ALIGNMENT);
		answerList.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));

		reset();

		answerList.setSelectedIndex(0);

		add(answerList);

		if (!Main.isConnectingMyo){
			add(pass);
		}

		selectNext();
	}

	public void reset(){
		ImageManager images = ImageManager.getInstance();

		List<String> list = new ArrayList<String>();
		list.add("もう1度画像を見る");
		list.addAll(Arrays.asList(images.generateRandomList(gamePanel.getResult())));

		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < list.size(); i++){
			model.addElement(list.get(i));
		}

		answerList.setModel(model);
		answerList.setSelectedIndex(0);
	}

	public String getAnswer(){
		return answerList.getSelectedValue();
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	public void select(int index){
		answerList.setSelectedIndex(index);
		answerList.updateUI();
	}

	public void selectNext(){
		int index = answerList.getSelectedIndex();
		index++;

		//1周したとき
		if (index >= answerList.getModel().getSize()){
			index = 0;
		}

		select(index);
	}

	public void selectPre(){
		int index = answerList.getSelectedIndex();
		index--;
		final int size = answerList.getModel().getSize();

		//選択値がマイナスになった
		if (index < size){
			index = size - 1;
		}

		select(index);
	}
}
