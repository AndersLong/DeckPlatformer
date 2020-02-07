package file;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	Clip clip;

	public void setFile(String path) {
		
	
		try {
			File file = new File(path);
			clip=AudioSystem.getClip();
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip.open(sound);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
