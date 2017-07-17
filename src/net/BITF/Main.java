package net.BITF;

import net.BITF.util.Fps;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;

/*
 * TODO
 *
 * 円が大きくなっていくバグ修正
 */

public class Main{

	public static boolean isDebugMode = false;
	public static boolean isConnectingMyo = false;

	public static Fps fps;
	public static int stage = 0;

	public static String[] args;

	public static Myo myo;
	public static Hub hub;

	private static Control control;

	public static void main(String[] args) {
		Main.args = args;

		myo = null;
		hub = null;

		for (int i = 0; i < args.length; i++){
			if (args[i].equals("-d")){
				isDebugMode = true;
			}
			else if(args[i].equals("-s")){
				stage = Integer.valueOf(args[++i]);
			}
			else if(args[i].equals("-m")){
				hub = new Hub("com.example.hello-myo");

				System.out.println("Attempting to find a Myo...");
				myo = hub.waitForMyo(10000);

				if (myo == null) {
					throw new RuntimeException("Unable to find a Myo!");
				}
				else {
					System.out.println("Connected to Myo");
					isConnectingMyo = true;
				}
			}
		}

		control = new Control();
		fps = new Fps(30, control);
	}
}
