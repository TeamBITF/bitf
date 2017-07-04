package net.BITF.util;

import java.util.ArrayList;
import java.util.List;

import net.BITF.image.ImageData;

public class ImageManager {
	private static final ImageManager instance = new ImageManager();

	public static ImageManager getInstance(){
		return instance;
	}

	private List<ImageData> images;

	private ImageManager(){
		images = new ArrayList<ImageData>();

		images.add(new ImageData("tower.jpg", "東京タワ-"));
		images.add(new ImageData("dog.jpg", "犬", "いぬ"));
		images.add(new ImageData("animals/elephant.jpg", "象", "ぞう"));
	}

	public int getSize(){
		return images.size();
	}

	public ImageData getImageFromList(int index){
		if (0 <= index && index < images.size()){
			return images.get(index);
		}

		return null;
	}


}
