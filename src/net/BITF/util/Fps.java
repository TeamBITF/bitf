package net.BITF.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.BITF.Control;

public class Fps implements Runnable, KeyListener{

	public int fps;

	public Thread thread;
	public Control control;

	public Fps(int maxFps){

		fps = maxFps;

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run(){

		control = new Control();

		long error = 0;
		long idealSleep = (1000 << 16) / fps;
		long oldTime;
		long newTime = System.currentTimeMillis() << 16;

		while (thread != null) {

			oldTime = newTime;

			control.update();
//			for (int i = 0; i < objects.size(); i++){
//				objects.get(i).update();
//			}

			newTime = System.currentTimeMillis() << 16;
			long sleepTime = idealSleep - (newTime - oldTime) - error; // 休止できる時間
			if (sleepTime < 0x20000) sleepTime = 0x20000; // 最低でも2msは休止
			oldTime = newTime;

			try {
				Thread.sleep(sleepTime >> 16);	 // 休止
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			newTime = System.currentTimeMillis() << 16;
			error = newTime - oldTime - sleepTime; // 休止時間の誤差
		}

		System.out.println("thread has revoked");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
