package net.BITF.panel.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JPanel;

import net.BITF.Circle.Circle;
import net.BITF.Circle.ListCircle;

public class MaskComponent extends JPanel implements MouseListener
{

	public MaskComponent(){
		addMouseListener(this);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(0xEE000000, true));

		g2.fillRect(50, 20, getWidth(), getHeight());

		Iterator<Circle> it = ListCircle.getInstance().getList().iterator();
		while(it.hasNext()){
			Circle c = it.next();

			int a = (c.getAlpha()) << 56;
			int color_code = 0xffffff | a;
			//color_code = 0x55ffffff;
			//System.out.printf("%x\n", a);

			AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c.getAlpha() / 255);
			g2.setComposite(composite);


			g2.setColor(new Color(color_code, true));
			g2.fillOval(c.x - c.r, c.y - c.r, c.r * 2, c.r * 2);
		}
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
		//System.out.println("MouseEvent_MousePressed");
		//System.out.println("X:" + e.getX() + ", Y:" + e.getY());
		if(e.getButton() == 1){
			ListCircle.getInstance().clicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
