package net.BITF.panel;

import javax.swing.JPanel;

import net.BITF.frame.MainFrame;

/**
 * setPreferredSize関数を必ず使ってください<BR>
 *
 */
public class BITFPanel extends JPanel{

	protected int nextStage;

	public BITFPanel(){
		nextStage = MainFrame.stage;
		//setPreferredSize(new Dimension(500, 500));
	}

	public BITFPanel getInstance(){
		return this;
	}

	/**
	 * メインループ
	 * @return
	 * 遷移したいステージ番号
	 */
	public int update(){
		return MainFrame.stage;
	}
}
