package net.BITF.component.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import net.BITF.Circle.Circle;
import net.BITF.Circle.ListCircle;
import net.BITF.util.ImageManager;

public class MainComponent extends JPanel implements MouseListener{

	private boolean flag;
	private int index;

	private int initialAlpha = 0x22000000;
	private int maskColor = 0xFF5698d5;

	private BufferedImage image;

	private static final int IMAGE_MAX_WIDTH = 800;

	public MainComponent(){
		setBackground(new Color(maskColor, true));
		changeImage(-1);
	}

	public MainComponent(int select){
		setBackground(new Color(maskColor, true));
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
		this.index = index;

		int result;

		addMouseListener(this);
		setOpaque(false);
		//setBounds()

		ImageManager manager = ImageManager.getInstance();

//		image = null;
//		mask = null;

		/*
		 *	画像を透明にして読み込み
		 *	画像指定されていたらそれを
		 *	されていなければランダム
		 */

		if (index < 0){
			new Random().nextInt(manager.getSize());
		}

		this.index = index;
		result = index;

		BufferedImage data = ImageManager.getInstance().getImageFromList(index).getImage();

		image = new BufferedImage(data.getWidth(), data.getHeight(), BufferedImage.TYPE_INT_ARGB);

		int[] array = manager.getImageFromList(index).getRGBArray();
		for (int i = 0; i < array.length; i++){
			array[i] = array[i] & 0xFFFFFF | 0x22000000;
		}

		image.setRGB(0, 0, data.getWidth(), data.getHeight(), array, 0, data.getWidth());

		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		return result;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

	    Iterator<Circle> it = ListCircle.getInstance().getList().iterator();
	    if (it.hasNext()){
	    	while(it.hasNext()){
				Circle circle = it.next();
				Color c2 = new Color(maskColor, true);

				float a = circle.getAlphaFloat();

//				System.out.printf("%x : %d : %f\n", c2.getRGB(), circle.getAlpha(), circle.getAlphaFloat());
//				System.out.printf("%x\n", c2.getRGB());

				//描画の開始位置(高さ)
				int start = circle.y - circle.r;
				int end = circle.y + circle.r;

				render(circle.x - circle.r, circle.y - circle.r, circle.r, circle);

		    }
	    	flag = true;
	    }

		//g2.drawImage(mask, 0, 0, this);
		g2.drawImage(image, 0, 0, this);
	}


	private int[] render(int startX, int startY, int r, Circle circle){

		final int l = r * 2;
		int alpha = circle.getAlpha();

		BufferedImage data = ImageManager.getInstance().getImageFromList(index).getImage();
		int[] array = data.getRGB(startX, startY, l, l, null, 0, l);

		for (int x = 0; x < l; x++){
			for (int y = x; y < l; y++){
				int i = x + y * l;
				int j = array.length - i - 1;

				if((p2(x - r) + p2(y - r)) < p2(r)){
					if (0 <= i &&i < array.length){
						array[i] = array[i] & 0xFFFFFF | alpha << 24;
					}

					if (0 <= j && j < array.length){
						array[j] = array[j] & 0xFFFFFF | alpha << 24;
					}
				}
			}
		}

		image.setRGB(startX, startY, l, l, array, 0, l);
		return array;
	}

	private int p2(int value){
		return value * value;
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
		if(e.getButton() == 1){		//左クリック

		}

		ListCircle.getInstance().clicked(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
