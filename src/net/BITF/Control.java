package net.BITF;

import net.BITF.frame.MainFrame;

public class Control implements Runnable{

	private static final Control instance = new Control(Main.fps);

	public static Control getInstance(){
		return instance;
	}

	int fps;

	public Thread thread;

	private MainFrame frame;

	private Control(int fps){
		this.fps = fps;
		frame = new MainFrame();
		frame.setVisible(true);

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run(){
		while(thread != null){
			long error = 0;
			long idealSleep = (1000 << 16) / fps;
			long oldTime;
			long newTime = System.currentTimeMillis() << 16;
			while (true) {
				oldTime = newTime;
				update(); // メイン処理
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
		}
	}

	private void update(){
		frame.update();
	}
}
