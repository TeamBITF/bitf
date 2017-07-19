package net.BITF.util;

import java.net.URL;

public class ResourceLoader {
	public static final ResourceLoader instance = new ResourceLoader();

	private ResourceLoader(){

	}

	public URL getResource(String path){
		return getClass().getClassLoader().getResource(path);
	}
}
