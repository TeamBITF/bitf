package net.BITF.util;

import java.net.URL;

public class ResourceLoader {
	public static final ResourceLoader instance = new ResourceLoader();

	private ResourceLoader(){

	}

	public URL getResource(String path){
		String p = path.toLowerCase();
		final URL result = getClass().getClassLoader().getResource(p);
		System.out.println(p);
		return result;
	}
}
