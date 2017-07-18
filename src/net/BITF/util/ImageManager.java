package net.BITF.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.BITF.image.ImageData;

public class ImageManager {
	private static final ImageManager instance = new ImageManager();

	public static ImageManager getInstance(){
		return instance;
	}

	private static int MAX_CHOICE = 5;
	private List<ImageData> images;

	private ImageManager(){

		/*
		 * TODO 画像データの追加
		 *
		 *	画像の配置場所
		 * 		resource/data/game/img
		 *
		 *	インスタンス生成
		 * 		new ImageData([ディレクトリ]/画像名.形式, "解答");
		 */
		ImageData[] data = {
				new ImageData("tower.jpg", "東京タワ-"),
				new ImageData("animals/elephant.jpg", "象")
		};

		images = new ArrayList<ImageData>(Arrays.asList(data));

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

	public String[] generateRandomList(int correct){
		/*
		 * 初期化
		 */

		//あえて乱数を固定させている
		Random random = new Random();

		//生成する選択肢の数
		int num = (MAX_CHOICE < getSize()) ? MAX_CHOICE : getSize();

		//返すリスト
		String[] result = new String[num];
		for (int i = 0; i < result.length; result[i++] = "");

		//既に選択されていないか
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

		/*
		 * 生成
		 */

		int res_index = random.nextInt(num);
		result[res_index] = images.get(correct).getName();

		//不正解を突っ込む
		for (int i = 0; i < num; i++){
			while(i != res_index){
				int j;

				//乱数を生成
				j = random.nextInt(getSize());

				//まだリストに追加されていない 且つ 正解でないとき
				if(!map.containsKey(j) && j != correct){

					//不正解を格納
					result[i] = images.get(j).getName();

					//使用済み(意味深)
					map.put(j, true);
					break;
				}
			}
		}

		return result;
	}
}
