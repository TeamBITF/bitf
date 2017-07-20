package net.BITF.Circle;


public class Circle {

	public static int INCREMENT = 10;

	/**
	 * 中心座標
	 */
	public int x, y;

	public int r;

	/**
	 * <h4>透明度</h4>0で透明<br>初期値:0xFF
	 */
	protected int alpha;

	protected static final int INITIAL_R = 15;

	public Circle(int x, int y){
		this.x = x;
		this.y = y;

		r = INITIAL_R;

		alpha = 255;
	}

	/**
	 * チェックポイント
	 */
	public boolean update(){
		alpha -= INCREMENT;
		if (alpha < 0){
			alpha = 0;
			return true;
		}

		return false;
	}

	private int p2(int value){
		return value * value;
	}

	public int getAlpha() {
		return alpha;
	}

	public float getAlphaFloat(){
		return 1 - (float) alpha / 255;
	}
}
