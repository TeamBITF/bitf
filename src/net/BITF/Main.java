package net.BITF;


//Test
public class Main {

	protected static int fps = 30;
	public static String[] args;

	@SuppressWarnings("unused")
	private static Control control;

	public static void main(String[] args) {

		Main.args = args;

		control = Control.getInstance();
	}
}
