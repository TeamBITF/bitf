package net.BITF.myo;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

/*
 * JVM Arguments to help debug.
 -Xcheck:jni
 -XX:+ShowMessageBoxOnError
 -XX:+UseOSErrorReporting
 */
public class HelloMyo implements Runnable{

	private Thread thread;

	public HelloMyo() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		
		try {
			Hub hub = new Hub("com.example.hello-myo");

			System.out.println("Attempting to find a Myo...");
			Myo myo = hub.waitForMyo(10000);

			if (myo == null) {
				throw new RuntimeException("Unable to find a Myo!");
			}

			System.out.println("Connected to a Myo armband!");
			DeviceListener dataCollector = new DataCollector();
			hub.addListener(dataCollector);

			while (thread != null) {
				hub.run(1000 / 20);
				System.out.print(dataCollector);
			}
		} catch (Exception e) {
			System.err.println("Error: ");
			e.printStackTrace();
			thread = null;
		}
	}
}