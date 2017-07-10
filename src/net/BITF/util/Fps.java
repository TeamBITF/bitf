package net.BITF.util;

import java.util.Arrays;
import java.util.List;

public class Fps implements Runnable{
	public int fps;

	public Thread thread;
	private List<IFps> objects;
	private IFps o;

	public Fps(int maxFps, IFps...object){
		objects = Arrays.asList(object);
		o = object[0];

		fps = maxFps;

		thread = new Thread(this);
		thread.start();
	}

	public void add(IFps object){
		objects.add(object);
	}

	@Override
	public void run(){

		long error = 0;
		long idealSleep = (1000 << 16) / fps;
		long oldTime;
		long newTime = System.currentTimeMillis() << 16;

		while (thread != null) {
			oldTime = newTime;

			for (int i = 0; i < objects.size(); i++){
				objects.get(i).update();
			}

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
}
