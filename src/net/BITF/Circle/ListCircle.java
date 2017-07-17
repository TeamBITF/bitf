﻿package net.BITF.Circle;

import java.util.ArrayList;
import java.util.List;

public class ListCircle {

	private static final ListCircle instance = new ListCircle();

	private List<Circle> list;
//	protected Circle lastUsed;

	private ListCircle(){
		list = new ArrayList<Circle>();
//		lastUsed = null;
	}

	public static ListCircle getInstance(){
		return instance;
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
		if(!Circle.collision(mouse_x, mouse_y)){
			Circle c = new Circle(mouse_x, mouse_y);
			list.add(c);
		}
	}

	public void update(){
		for (int i = 0; i < list.size(); i++){
			list.get(i).update();
		}
	}
}
