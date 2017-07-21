package net.BITF.component.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import net.BITF.Circle.Circle;
import net.BITF.image.ImageData;
import net.BITF.panel.GamePanel;
import net.BITF.util.ImageManager;

public class MainComponent extends JPanel implements MouseListener{

	private int index;
	private GamePanel gamePanel;

	private int initialAlpha = 0;//0xaa000000;
	private int maskColor = 0xFF5698d5;

	private BufferedImage image;

	private static final int IMAGE_MAX_WIDTH = 800;
	private int oldClickCount;

	public MainComponent(GamePanel gamePanel){
		this.gamePanel = gamePanel;

		setBackground(new Color(maskColor, true));
		changeImage(-1);
	}

	public MainComponent(GamePanel gamePanel, int select){
		this.gamePanel = gamePanel;
		changeImage(select);
	}

	/**
	 * 画像を指定したものに変更する
	 * @param index
	 */
	public int changeImage(int index){
		return init(index);
	}

	private int init(int index){
		int result;

		oldClickCount = 0;

		addMouseListener(this);
		setOpaque(false);

		ImageManager manager = ImageManager.getInstance();

//		image = null;
//		mask = null;

		/*
		 *	画像を透明にして読み込み
		 *	画像指定されていたらそれを
		 *	されていなければランダム
		 */

		if (index < 0){
			index = new Random().nextInt(manager.getSize());
		}

		this.index = index;
		result = index;

		System.out.println("read image for index:" + index);
		BufferedImage data = ImageManager.getInstance().getImageFromList(index).getImage();

		image = new BufferedImage(data.getWidth(), data.getHeight(), BufferedImage.TYPE_INT_ARGB);

		int[] array = manager.getImageFromList(index).getRGBArray();
		for (int i = 0; i < array.length; i++){
			array[i] = array[i] & 0xFFFFFF | initialAlpha;
		}

		image.setRGB(0, 0, data.getWidth(), data.getHeight(), array, 0, data.getWidth());

		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		return result;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		List<Circle> list = gamePanel.getListCircle().getList();

	    for (int i = 0; i < list.size(); i++){
	    	Circle circle = list.get(i);
//			System.out.println("MainComponent>>Circle r:" + circle.r);

			render(circle);

			if(circle.getAlpha() <= 0){
				list.remove(i);
			}
	    }

	    g2.drawImage(image, 0, 0, this);

	    g2.setColor(Color.WHITE);
	    g2.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
	}


	private void render(Circle circle){

		final int r = circle.r;

		int startX = circle.x - r;
		int startY = circle.y - r;

		int endX = circle.x + r;
		int endY = circle.y + r;

		int w, h;

		//ずれ
		//初回描画座標がマイナスのときのみ値を設定
		int offsetX = 0;
		int offsetY = 0;

		//startがマイナス
		if (startX < 0){
			offsetX = startX;
			startX = 0;
		}
		if (startY < 0){
			offsetY = startY;
			startY = 0;
		}

		//endが画像サイズより大きい
		if (endX > image.getWidth() ){
			endX = image.getWidth();
		}

		if (endY > image.getHeight()){
			endY = image.getHeight();
		}

		w = endX - startX;
		h = endY - startY;

//		System.out.printf("r:%d\noffset\n%d\n%d\n", r, offsetX, offsetY);

		//一部分の読み取り
		ImageData data = ImageManager.getInstance().getImageFromList(index);
		int[] array =  data.getRGBArray(startX, startY, w, h);

		int x, y;
		final int alpha = circle.getAlpha();

		for (y = 0; y < h; y++){
			for (x = offsetX; x < w / 2; x++){
				//透明な範囲
				if ((p2(x - r - offsetX) + p2(y - r - offsetY)) < p2(r)){
					break;
				}
			}

			final int ih = y * w;
			int e = ih + w;
			int s = ih;

			System.out.printf("array\ns:%d\ne:%d\ns+x:%d\ne-x:%d\nx:%d\n", s, e, s + x, e - x, x);

			s = x;
			e = x;

			if (x < 0){
				s = 0;
			}

			//かぶってる
			s = (x < 0) ? 0 : x;

			//見つからなかったとき
			if (x == w / 2){
				continue;
			}

//			int[] rgb = Arrays.copyOfRange(array, i, w + y * w - x);
			int[] rgb = Arrays.copyOfRange(array, ih + s, ih + w - x);
			for (int j = 0; j < rgb.length; j++){
				rgb[j] &= 0xFFFFFF | alpha << 24;
			}

//			image.setRGB(startX + x, startY + y, w - x * 2, 1, rgb, 0, 0);
			image.setRGB(startX + s, startY + y, rgb.length, 1, rgb, 0, 0);

		}

	}

	/**
	 * 2乗する関数
	 */
	private int p2(int value){
		int result = 0;

		//マイナスだったらプラスにする
		if (value < 0){
			value = (0xFFFFFFFF ^ value) + 1;
		}

		//大体2乗した値になるらしい
		for (int n = 1; n <= value; n++){
			result += (n << 1) - 1;
		}

		return result;
//		return value * value;
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		final int clickCount = e.getClickCount();

		if (oldClickCount == 0 || oldClickCount != clickCount){			//1ループで2回以上来ることがあるから仕方ないね。
//			System.out.println("PressedCount:" + e.getClickCount());
			if(e.getButton() == MouseEvent.BUTTON1){		//左クリック
				gamePanel.click(e.getX(), e.getY());
			}
		}

		oldClickCount = clickCount;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
