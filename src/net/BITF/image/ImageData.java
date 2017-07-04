package net.BITF.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageData {

	private String[] name;
	private BufferedImage image;

	public ImageData(String fileName, String...names){
		this.setName(names);

		System.out.println("Lead for \"" + fileName + "\"");
		try {
			setImage(ImageIO.read(new File("data/img/background/" + fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName(int index) {
		if (0 <= index && index < name.length){
			return name[index];
		}
		return null;
	}

	public String[] getNameList(){
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
