package net.BITF.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCircle {

	private List<Circle> list;
	private static final int INCR = 15;
	private static final int MAX_R = 135;

	public ListCircle(){
		list = new ArrayList<Circle>();
//		lastUsed = null;
	}

	public List<Circle> getList() {
		return list;
	}

//	public Circle getLastUsed() {
//		return lastUsed;
//	}

	public void setNewCircle(Circle circle){
		list.add(circle);
//		lastUsed = circle;
	}

	public void removeAllCircle(){
		list.clear();
	}

	/**
	 * 新しく円を生成します
	 * @param x
	 * マウスのX座標
	 * @param y
	 * マウスのY座標
	 */
	public void clicked(int mouse_x, int mouse_y){
		//なかった時
		if(!collision(mouse_x, mouse_y)){
			Circle c = new Circle(mouse_x, mouse_y);
			System.out.println("Creation\n" + c.toString());
			list.add(c);
		}
	}

	public void update(){
		for (int i = 0; i < list.size(); i++){
			list.get(i).update();
		}
	}

	private boolean collision(int mouse_x, int mouse_y){
		boolean collision = false;

		Iterator<Circle> it = list.iterator();
		while(it.hasNext()){
			Circle c = it.next();

			//クリックされた座標に円がある
			if((p2(c.x - mouse_x) + p2(c.y - mouse_y) < p2(c.r))){

//				System.out.println(c.toString());
//				System.out.print("extend " + c.r);

				c.alpha = 255;

				if (c.r < MAX_R){
					c.r += INCR;
				}

//				System.out.println(" -> " + c.r);

				collision = true;
				//break;
			}
		}

		return collision;

	}

	private int p2(int value) {
		return value * value;
	}
}
