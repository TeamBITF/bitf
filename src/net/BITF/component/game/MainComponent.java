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

	private String path;

	private int maskColor;

	private BufferedImage image;
	private BufferedImage mask;

	private static final int IMAGE_MAX_WIDTH = 800;

	public MainComponent(){
		changeImage(-1);
	}

	public MainComponent(int select){
		init(select);
	}

	private void resetMask(){
		for (int i = 0; i < mask.getWidth(); i++){
			for (int j = 0; j < mask.getHeight(); j++){
				mask.setRGB(i, j, maskColor);
			}
		}
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

		addMouseListener(this);
		setOpaque(false);
		//setBounds()

		ImageManager manager = ImageManager.getInstance();
		maskColor = 0xFF5698d5;

//		image = null;
//		mask = null;

		/*
		 *	画像の読み込み
		 *	画像指定されていたらそれを
		 *	されていなければランダム
		 */
		if (index >= 0){
			result = index;
			image = manager.getImageFromList(index).getImage();
		}
		else{
			result = new Random().nextInt(manager.getSize());
			image = manager.getImageFromList(result).getImage();
		}

		//nullを返されたときの保険
		if (image == null){
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

			for (int i = 0; i < getWidth(); i++){
				for (int j = 0; j < getHeight(); j++){
					image.setRGB(i, j, 0xFF000000);
				}
			}
		}

		/*
		 * TODO
		 * 画像の横幅が大きかった時に縦横比を維持して縮小する処理
		 * 参考(?) : http://dotnsf.blog.jp/archives/1062006362.html
		 */
		if (image.getWidth() > IMAGE_MAX_WIDTH){
			int newWidth = IMAGE_MAX_WIDTH;
			int newHeight = IMAGE_MAX_WIDTH * image.getHeight() / image.getWidth();
		}
		setSize(image.getWidth(), image.getHeight());

		mask = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		resetMask();

		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

		return result;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;


		//使うパレットの選択
		Graphics2D offset = mask.createGraphics();

	    Iterator<Circle> it = ListCircle.getInstance().getList().iterator();
	    if (it.hasNext()){
	    	while(it.hasNext()){
				Circle circle = it.next();
				Color c2 = new Color(maskColor, true);

				float a = circle.getAlphaFloat();

//				System.out.printf("%x : %d : %f\n", c2.getRGB(), circle.getAlpha(), circle.getAlphaFloat());
//				System.out.printf("%x\n", c2.getRGB());

				//描画の開始位置
				int start = circle.y - circle.r;
				int end = circle.y + circle.r;

//				System.out.printf("start:%d\nend:%d\n", start, end);
				for (int y = start; y < end; y++){
					double x = Math.sqrt(Math.abs(p2(circle.r) - p2(y - circle.y)));

					double ox = (double)circle.x;


					for (int i = (int) (ox - x); i < (int) (ox + x); i++){
//						System.out.println(i);
						if(0 <= i && 0 <= y){
							if (i < image.getWidth() && y < image.getHeight()){
								/*=============================================
								 * TODO
								 *
								 * 高速化
								 * ファイルの圧縮の技術を応用したらできそう?
								 * lan-length
								 =============================================*/

								int rgb = 0xff000000;
								Color c1 = new Color(image.getRGB(i, y) & 0xFFFFFF);
								float temp;

								//00 XX 00 00
								temp = c1.getRed() + (c2.getRed() - c1.getRed()) * a;
								rgb |= (int) temp << 16;

								//00 00 XX 00
								temp = c1.getGreen() + (c2.getGreen() - c1.getGreen()) * a;
								rgb |= (int) temp << 8;

								//00 00 00 XX
								temp = c1.getBlue() + (c2.getBlue() - c1.getBlue()) * a;
								rgb |= (int) temp;

//								System.out.printf("rgb : %x\n", rgb);
								mask.setRGB(i, y, rgb);
							}
						}
					}
				}
		    }
	    	flag = true;
	    }
	    else {
	    	if (flag) {
	    		resetMask();
	    	}
	    }

		g2.drawImage(mask, 0, 0, this);
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
