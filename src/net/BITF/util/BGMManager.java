package net.BITF.util;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class BGMManager implements Runnable{

	private AudioFormat audioFormat;
	private SourceDataLine line;
	private byte[] data;

	public BGMManager(){



		try{
			AudioInputStream sta =
					AudioSystem.getAudioInputStream(new File("Z:/0楠祭　鍵班/BGM/famipop3.wav"));
			data = new byte [sta.available()];
			sta.read(data);
			sta.close();
			// ファイルのフォーマットを調べる
			audioFormat = sta.getFormat();


			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

			line = (SourceDataLine)AudioSystem.getLine(info);


		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void BGMStart(){
		try {
			line.open(audioFormat);
			line.start();
			line.write(data, 0, data.length);
		} catch (LineUnavailableException e) {
			System.err.println("そんな曲ねぇぞ");
		}


	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ

	}
}