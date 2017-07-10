package net.BITF;

import net.BITF.util.Fps;



public class Main{

	public static boolean isDebugMode = false;

	public static Fps fps;

	public static String[] args;
	private static Control control;

	public static void main(String[] args) {
		Main.args = args;

		for (int i = 0; i < args.length; i++){
			System.out.println(args[i]);
			if (args[i].equals("-d")){
				isDebugMode = true;


//				try {
//					Runtime.getRuntime().exec(new String[]{ "cmd.exe","/C","start" } );
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

				System.out.println("DebugMode:true");
			}
		}

		control = new Control();
		fps = new Fps(30, control);
	}
}
