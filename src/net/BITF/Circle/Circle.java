package net.BITF.Circle;

import java.util.Iterator;

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
	private int alpha;

	private static final int INITIAL_R = 15;
	private static final int INCR = 15;

	public Circle(int x, int y){
		this.x = x;
		this.y = y;

		r = INITIAL_R;

		alpha = 255;
	}

	/**
	 * 当たり判定
	 * @param circle
	 * @param mouse_x
	 * @param mouse_y
	 * @return
	 */
	public static boolean collision(int mouse_x, int mouse_y){

		boolean collision = false;
		Iterator<Circle> it = ListCircle.getInstance().getList().iterator();

		while(it.hasNext()){
			Circle c = it.next();

			//クリックされた座標に円がある
			if((p2(c.x - mouse_x) + p2(c.y - mouse_y) < p2(c.r))){
				System.out.println(c.toString());
				System.out.print("extend " + c.r);

				c.alpha = 255;
				c.r += INCR;

				System.out.println(" -> " + c.r);

				collision = true;
				//break;
			}
		}

		return collision;
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

	private static int p2(int value){
		return value * value;
	}

	public int getAlpha() {
		return alpha;
	}

	public float getAlphaFloat(){
		return 1 - (float) alpha / 255;
	}
}
