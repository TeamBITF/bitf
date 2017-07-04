package net.BITF.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCircle {

	private static final ListCircle instance = new ListCircle();

	private List<Circle> list;
	protected Circle lastUsed;

	private ListCircle(){
		list = new ArrayList<Circle>();
		lastUsed = null;
	}

	public static ListCircle getInstance(){
		return instance;
	}

	public List<Circle> getList() {
		return list;
	}

	public Circle getLastUsed() {
		return lastUsed;
	}

	public void setNewCircle(Circle circle){
		list.add(circle);
		lastUsed = circle;
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
		if(!Circle.collision(mouse_x, mouse_y)){
			Circle c = new Circle(mouse_x, mouse_y);
			System.out.println("ListCircle : " + c + "\nhas created");
			list.add(c);
		}
	}

	public void update(){
		Iterator<Circle> it = list.iterator();
		while(it.hasNext()){
			Circle c = it.next();

			/*
			 * alphaの値が0以下になるとき
			 */
			if(c.update()){
//				System.out.println(it + "\nhas removed");
				it.remove();
			}
		}
	}
}
