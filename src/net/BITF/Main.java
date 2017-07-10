package net.BITF;

import net.BITF.util.Fps;



public class Main{

	public static boolean isDebugMode = false;

	public static Fps fps;
	public static int stage = 0;

	public static String[] args;
	private static Control control;

	public static void main(String[] args) {
		Main.args = args;

		for (int i = 0; i < args.length; i++){
			System.out.println(args[i]);
			if (args[i].equals("-d")){
				isDebugMode = true;
				System.out.println("DebugMode:true");
			}
			else if(args[i].equals("-s")){
				stage = Integer.valueOf(args[++i]);
			}
		}

		control = new Control();
		fps = new Fps(30, control);
	}
}
