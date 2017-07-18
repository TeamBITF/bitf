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
				new ImageData("tower.jpg", "東京タワー"),
				new ImageData("animals/elephant.jpg", "ゾウ"),
				new ImageData("amusement/Biliards.jpg","ビリヤード"),
				new ImageData("amusement/chess.jpg","チェス"),
				new ImageData("amusement/fireworks.jpg","花火"),
				new ImageData("amusement/kanransha.jpg","観覧車"),
				new ImageData("amusement/Merry-go-round.jpg","メリーゴーラウンド"),
				new ImageData("animals/bear.jpg","クマ"),
				new ImageData("animals/camel.jpg","ラクダ"),
				new ImageData("animals/chameleon.jpg","カメレオン"),
				new ImageData("animals/cobra.jpg","コブラ"),
				new ImageData("animals/cow.jpg","ウシ"),
				new ImageData("animals/croco.jpg","ワニ"),
				new ImageData("animals/dolphin.jpg","イルカ"),
				new ImageData("animals/flamingo.jpg","フラミンゴ"),
				new ImageData("animals/fox.jpg","キツネ"),
				new ImageData("animals/frog.jpg","カエル"),
				new ImageData("animals/giraffe.jpg","キリン"),
				new ImageData("animals/gorilla.jpg","ゴリラ"),
				new ImageData("animals/hippo.jpg","カバ"),
				new ImageData("animals/jellyfish.jpg","クラゲ"),
				new ImageData("animals/lion.jpg","ライオン"),
				new ImageData("animals/mole.jpg","モグラ"),
				new ImageData("animals/owl.jpg","フクロウ"),
				new ImageData("animals/peacock.jpg","クジャク"),
				new ImageData("animals/piglet.jpg","ブタ"),
				new ImageData("animals/rabbit.jpg","ウサギ"),
				new ImageData("animals/rhino.jpg","サイ"),
				new ImageData("animals/tiger.jpg","トラ"),
				new ImageData("animals/turtle.jpg","ウミガメ"),
				new ImageData("animals/whale.jpg","クジラ"),
				new ImageData("food/banana.jpg","バナナ"),
				new ImageData("food/burger.jpg","ハンバーガー"),
				new ImageData("food/cake.jpg","ケーキ"),
				new ImageData("food/carrots.jpg","にんじん"),
				new ImageData("food/kiwi.jpg","キウイ"),
				new ImageData("food/onion.jpg","たまねぎ"),
				new ImageData("food/orange.jpg","オレンジ"),
				new ImageData("food/pine.jpg","パイナップル"),
				new ImageData("food/potato.jpg","ポテトチップス"),
				new ImageData("food/ra-men.jpg","ラーメン"),
				new ImageData("food/salad.jpg","サラダ"),
				new ImageData("food/soba.jpg","そば"),
				new ImageData("food/strawberry.jpg","いちご"),
				new ImageData("food/suika.jpg","すいか"),
				new ImageData("food/sushi.jpg","お寿司"),
				new ImageData("food/yakizakana.jpg","焼き魚"),
				new ImageData("norimono/aircraft.jpg","飛行機"),
				new ImageData("norimono/bike.jpg","バイク"),
				new ImageData("norimono/heli.jpg","ヘリコプター"),
				new ImageData("norimono/pato.jpg","パトカー"),
				new ImageData("norimono/ship.jpg","船"),
				new ImageData("norimono/shobel.jpg","ショベルカー"),
				new ImageData("norimono/tank.jpg","戦車"),
				new ImageData("norimono/tricycle.jpg","三輪車"),

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
