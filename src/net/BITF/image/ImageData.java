﻿package net.BITF.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import net.BITF.util.ResourceLoader;

public class ImageData {

	private String name;
	private BufferedImage image;
	private String path;

	public ImageData(String fileName, String name){
		this.setName(name);

		path = "data/game/img/" + fileName;
		try {
			URL url = ResourceLoader.instance.getResource(path);
//			System.out.println(getClass().getClassLoader().getResource(path));

			BufferedImage raw = ImageIO.read(url);
			int w = raw.getWidth();
			int h = raw.getHeight();

			int[] array = raw.getRGB(0, 0, w, h, null, 0, w);

			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			image.setRGB(0, 0, w, h, array, 0, w);

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getImageWidth(){
		return image.getWidth();
	}

	public int getImageHeight(){
		return image.getHeight();
	}

	public int[] getRGBArray(){
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	}

	public int[] getRGBArray(int startX, int startY, int w, int h){
		return image.getRGB(startX, startY, w, h, null, 0, w);

	}
}
