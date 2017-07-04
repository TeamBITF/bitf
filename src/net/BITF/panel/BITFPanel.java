package net.BITF.panel;

import javax.swing.JPanel;

/**
 * setPreferredSize関数を必ず使ってください<BR>
 *
 */
public abstract class BITFPanel extends JPanel{
	public BITFPanel getInstance(){
		return this;
	}

	/**
	 * メインループ<BR>
	 * true : 次の画面に遷移させる
	 * @return
	 */
	public abstract boolean update();
}
